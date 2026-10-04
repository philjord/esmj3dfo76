package esmj3dfo76.j3d.j3drecords.inst;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.jogamp.java3d.GLSLShaderProgram;
import org.jogamp.java3d.GeometryArray;
import org.jogamp.java3d.Group;
import org.jogamp.java3d.IndexedGeometryArray;
import org.jogamp.java3d.IndexedTriangleArray;
import org.jogamp.java3d.IndexedTriangleStripArray;
import org.jogamp.java3d.J3DBuffer;
import org.jogamp.java3d.Material;
import org.jogamp.java3d.RenderingAttributes;
import org.jogamp.java3d.Shader;
import org.jogamp.java3d.ShaderAppearance;
import org.jogamp.java3d.ShaderAttributeSet;
import org.jogamp.java3d.ShaderAttributeValue;
import org.jogamp.java3d.ShaderProgram;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.SourceCodeShader;
import org.jogamp.java3d.TextureUnitState;
import org.jogamp.java3d.geom.GeometryData;
import org.jogamp.vecmath.Color4f;
import org.jogamp.vecmath.TexCoord2f;
import org.jogamp.vecmath.Vector3f;

import esfilemanager.btd.BtdbReader;
import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.data.shared.records.LTEX;
import esmj3d.data.shared.records.TXST;
import esmj3d.j3d.TESLANDGen;
import esmj3d.j3d.j3drecords.inst.J3dLAND;
import esmj3dfo76.data.records.LANDBtdb;
import nif.j3d.J3dNiTriBasedGeom;
import nif.niobject.bgsm.BSMaterialDataBGSM;
import nif.shader.ShaderSourceIO;
import tools3d.utils.PhysAppearance;
import tools3d.utils.Utils3D;
import utils.convert.ConvertFromNif;
import utils.source.MaterialsSource;
import utils.source.TextureSource;

// https://ghoul-earth.com/ tells me what it should look like
public class J3dLANDBtdb extends J3dLAND {
	//TODO: something something lods get less
	public static int	GRID_COUNT			= 128;						//NOTE the 32 of the other one is a 33 wide grid for 32 "flats"

	//public static final float		TERRIAN_SQUARE_SIZE	= ConvertFromNif.toJ3d(128);	// = 1.6256m

	//	NOTE nif x,y,z to j3d x,z,-y
	//-700.0, mimimum height  38209.996, maximum height, I see rocks placed at 470m so maths
	//using a value of 1 here I get the correct looking world min=-700.0 max=38209.996 min=-8.89 max=485.26694
	public static float	HEIGHT_TO_J3D_SCALE	= ConvertFromNif.toJ3d(1f);	//half inch now 0.0127m

	public static float	LAND_SIZE			= 52.0192f;					// make it fixed 4096 nif unit per land = this many meters, regardless of grid size

	private BtdbReader	btdbReader;
	private int			x;
	private int			y;

	/**
	 * Makes the physics version of land
	 */
	public J3dLANDBtdb(BtdbReader btdbReader, int fakeFormId, int x, int y) {
		super(new LANDBtdb(fakeFormId), false, true);
		this.x = x;
		this.y = y;
		this.btdbReader = btdbReader;
		if (btdbReader != null) {
			float[] hMinMax = new float[2];
			hMinMax[0] = btdbReader.getMinHeight();
			hMinMax[1] = btdbReader.getMaxHeight();

			// extract the heights
			short[] cellHeightMap = new short[16512];
			btdbReader.getCellHeightMap(cellHeightMap, x, y, 0);
			float[][] heights = extractHeights(hMinMax, cellHeightMap);

			//now translate the heights into a nice mesh, 82 has been confirmed empirically			
			//Note that 33 by 33 sets of point equals 32 by 32 sets of triangles between them
			TESLANDGen gridGenerator = new TESLANDGen(J3dLANDBtdb.LAND_SIZE, J3dLANDBtdb.LAND_SIZE, GRID_COUNT,
					GRID_COUNT, heights, null, null, null);
			GeometryData terrainData = new GeometryData();
			gridGenerator.generateIndexedTriangleStrips(terrainData);

			Shape3D shape = new Shape3D();
			IndexedTriangleStripArray physicsTriStripArray = new IndexedTriangleStripArray(
					terrainData.vertexCount,
					GeometryArray.COORDINATES	| GeometryArray.USE_NIO_BUFFER | GeometryArray.BY_REFERENCE
												| GeometryArray.BY_REFERENCE_INDICES
												| GeometryArray.USE_COORD_INDEX_ONLY,
					terrainData.indexesCount, terrainData.stripCounts);
			physicsTriStripArray.setCoordRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.coordinates)));
			physicsTriStripArray.setCoordIndicesRef(terrainData.indexes);

			//apply them
			physicsTriStripArray.setName("LAND phys geo");
			shape.setGeometry(physicsTriStripArray);
			shape.setAppearance(PhysAppearance.makeAppearance());
			addNodeChild(shape);

			//	gi = new GeometryInfo(GeometryInfo.TRIANGLE_STRIP_ARRAY);
			//	gi.setStripCounts(terrainData.stripCounts);
			//	gi.setCoordinates(terrainData.coordinates);
			//	gi.setCoordinateIndices(terrainData.indexes);
		}
	}

	@Override
	public float[][] getHeights() {
		if (btdbReader != null) {
			float[] hMinMax = new float[2];
			hMinMax[0] = btdbReader.getMinHeight();
			hMinMax[1] = btdbReader.getMaxHeight();
			// extract the heights
			short[] cellHeightMap = new short[16512];
			btdbReader.getCellHeightMap(cellHeightMap, x, y, 0);
			return extractHeights(hMinMax, cellHeightMap);
		}
		return null;
	}

	/**
	 * makes the visual version of land
	 * @param land
	 * @param master
	 */

	private float					lowestHeight	= Float.MAX_VALUE;
	private float					highestHeight	= Float.MIN_VALUE;

	private static ShaderProgram	shaderProgram	= null;

	private static final Object		shaderLock		= new Object();

	public J3dLANDBtdb(	BtdbReader btdbReader, int fakeFormId, int x, int y, IRecordStore master,
						TextureSource textureSource) {
		super(new LANDBtdb(fakeFormId), false, false);
		LAND(btdbReader, x, y, master, textureSource);
	}

	private void LAND(BtdbReader btdbReader, int x, int y, IRecordStore master, TextureSource textureSource) {

		this.x = x;
		this.y = y;
		this.btdbReader = btdbReader;
		int quadrantsPerSide = 2;//sw,se,nw,ne
		int totalQuadrants = quadrantsPerSide * quadrantsPerSide;
		int quadrantSquareCount = ((GRID_COUNT - 1) / quadrantsPerSide) + 1;

		Group baseGroup = new Group();
		addNodeChild(baseGroup);

		//ensure shader ready
		createShaderProgram();

		if (btdbReader != null) {
			// extract the heights, unless this is supposed to be the global min and max heights of course?
			float[] hMinMax = new float[2];

			hMinMax[0] = btdbReader.getMinHeight();
			hMinMax[1] = btdbReader.getMaxHeight();

			short[] cellHeightMap = new short[16512];
			btdbReader.getCellHeightMap(cellHeightMap, x, y, 0);

			// extract the colors
			short[] cellTerrainColor = new short[16512];
			btdbReader.getCellTerrainColor(cellTerrainColor, x, y, 0);
			//terrain color block format (LOD3 and LOD2 only):
			Color4f[][] colors = extractColors(cellTerrainColor);

			short[] cellLandTexture = new short[16512];
			btdbReader.getCellLandTexture(cellLandTexture, x, y, 0);
			byte[] cellGroundCover = new byte[16512];
			btdbReader.getCellGroundCover(cellGroundCover, x, y, 0);
			byte[] cellTextureSet = new byte[64];
			btdbReader.getCellTextureSet(cellTextureSet, x, y);

			// get the atxts equivalents
			for (int quadrant = 0; quadrant < totalQuadrants; quadrant++) {
				ShaderAppearance app = new ShaderAppearance();
				app.setMaterial(createMat());
				//app.setRenderingAttributes(createRA());

				Shape3D baseQuadShape = new Shape3D();
				baseQuadShape.setAppearance(app);

				int[] attributeSizes = new int[] {4, 4};

				try {
					//TODO: current testing on Lod=0 only!
					GeometryArray ga = makeQuadrantBaseSubGeom(colors, quadrantsPerSide, 0, quadrant, 1, 2,
							attributeSizes, hMinMax, cellHeightMap);

					ga.setName("LAND " + quadrant + " " + x + " " + y);

					baseQuadShape.setGeometry(ga);

					ArrayList<ShaderAttributeValue> allShaderAttributeValues = new ArrayList<ShaderAttributeValue>();
					ArrayList<TextureUnitState> allTextureUnitStates = new ArrayList<TextureUnitState>();

					TextureUnitState tus = null;

					// texture come from cellTextureSet
					//bits[N * 3 to N * 3 + 2] = texture N alpha (0 to 7),
					//texture 6 is the base texture		
					/* 8x16 bytes SWTexID,SWGCID,SETexID,SEGCID,NWTexID,NWGCID,NETexID,NEGCID ,  each is 6 skip 2 then 8 bytes
					 buf[0..5]   = SW quadrant land texture IDs (0xFF: no texture), getLandTexture() returns the corresponding form IDs
					 buf[8..15]  = SW quadrant ground cover IDs (0xFF: none), getGroundCover() returns the corresponding form IDs
					 buf[16..31] = SE quadrant land texture and ground cover IDs
					 buf[32..63] = NW and NE quadrants	
					 */

					//so Quandrants  are 0=sw,1=se,2=nw,3=ne
					int quadOff = quadrant * 16;
					int texBase = cellTextureSet[quadOff + 5] & 0xFF; //oddly described as texture 6 (not idx 5)	 			

					if (texBase != 0xFF) {
						tus = getTexture(btdbReader.getLandTexture(texBase), master, textureSource);
					} else {
						tus = getDefaultTexture(textureSource);
					}

					if (tus == null)
						System.err.println("tus == null, things are gonna break!");

					allTextureUnitStates.add(tus);
					allShaderAttributeValues.add(new ShaderAttributeValue("baseMap", Integer.valueOf(0)));

					ByteBuffer bb = ByteBuffer.allocateDirect((quadrantSquareCount * quadrantSquareCount) * 4 * 4);
					bb.order(ByteOrder.nativeOrder());
					FloatBuffer alphas03 = bb.asFloatBuffer();
					bb = ByteBuffer.allocateDirect((quadrantSquareCount * quadrantSquareCount) * 4 * 4);
					bb.order(ByteOrder.nativeOrder());
					FloatBuffer alphas47 = bb.asFloatBuffer();

					for (int atxtLayer = 0; atxtLayer < 5; atxtLayer++) {
						//cellLandTexture is vtxt now

						int atxt = cellTextureSet[quadOff + atxtLayer] & 0xFF;
						if (atxt != 0xFF) {
							tus = getTexture(btdbReader.getLandTexture(atxt), master, textureSource);

							if (tus == null)
								System.err.println("tus == null, things are gonna break!");

							allTextureUnitStates.add(tus);

							allShaderAttributeValues.add(
									new ShaderAttributeValue("layerMap" + atxtLayer, Integer.valueOf(atxtLayer + 1)));

							// now build up the vertex attribute float arrays to hand to the geometry	
							//cellLandTexture is 128x128 x short of these opacities						

							//TODO: lower lods less grid count,( like a far for tes3) but also cover more cells worths like obliv
							for (int row = 0; row < quadrantSquareCount; row++) {
								for (int col = 0; col < quadrantSquareCount; col++) {

									//I am inside a  a quadrant 0-3 so I'm 64x64 of the full 128x128
									//so quad 0 is 0-64,0-64 quad 1 = 65-128,0-64, quad 2 = 0-64,65-128, quad 3 = 65-128,65-128 
									int sourceCol = col;
									if (quadrant == 1 || quadrant == 3)
										sourceCol += quadrantSquareCount;

									int sourceRow = (quadrantSquareCount - 1) - row; // flipped probably the xyz to x-zy (0to63 becomes 63to0)
									if (quadrant == 2 || quadrant == 3)
										sourceRow += quadrantSquareCount;

									int sourceIdx = sourceCol + (sourceRow * GRID_COUNT); //row span is the full 128
									int vtxtopacity = (cellLandTexture[sourceIdx] & 0xFFFF);

									//int16 land textures,  bits[N * 3 to N * 3 + 2] = texture N alpha (0 to 7),
									float a0 = ((vtxtopacity >> 0) & 0x07) / 7f;
									float a1 = ((vtxtopacity >> 3) & 0x07) / 7f;
									float a2 = ((vtxtopacity >> 6) & 0x07) / 7f;
									float a3 = ((vtxtopacity >> 9) & 0x07) / 7f;
									float a4 = ((vtxtopacity >> 12) & 0x07) / 7f;

									int idx = ((row * quadrantSquareCount) + col) * 4;

									alphas03.put(idx + 0, a0);
									alphas03.put(idx + 1, a1);
									alphas03.put(idx + 2, a2);
									alphas03.put(idx + 3, a3);

									alphas47.put(idx + 0, a4);

								}

							}
						}
					}

					//TODO: the 8 ground covers
					//int gc0 = cellTextureSet[quadOff + 8]&0xFF; etc

					ga.setVertexAttrRefBuffer(0, new J3DBuffer(alphas03));
					ga.setVertexAttrRefBuffer(1, new J3DBuffer(alphas47));

					TextureUnitState[] tusa = new TextureUnitState[allTextureUnitStates.size()];
					for (int i = 0; i < allTextureUnitStates.size(); i++) {
						tusa[i] = allTextureUnitStates.get(i);
						//TODO: I notice the same texture repeats in the layers a lot sometimes
						if (OUTPUT_BINDINGS)
							System.out.println("LAND Tus " + i + " " + tusa[i]);
					}
					app.setTextureUnitState(tusa);

					app.setShaderProgram(shaderProgram);

					ShaderAttributeSet shaderAttributeSet = new ShaderAttributeSet();
					for (ShaderAttributeValue sav : allShaderAttributeValues) {
						if (OUTPUT_BINDINGS)
							System.out.println(sav.getAttributeName() + " " + sav.getValue());

						shaderAttributeSet.put(sav);
					}
					app.setShaderAttributeSet(shaderAttributeSet);

					baseGroup.addChild(baseQuadShape);

				} catch (Exception e) {
					System.out.println("exception in makiing LANDbtdb ");
					e.printStackTrace();
				}

			}

		}
	}

	private static Material mat;

	public static Material createMat() {
		if (mat == null) {
			mat = new Material();
			mat.setColorTarget(Material.AMBIENT_AND_DIFFUSE);
			mat.setShininess(1.0f);
			mat.setDiffuseColor(1f, 1f, 1f);
			mat.setSpecularColor(1f, 1f, 1f);
		}
		return mat;
	}

	private static RenderingAttributes ra;

	public static RenderingAttributes createRA() {
		if (ra == null) {
			ra = new RenderingAttributes();
		}
		return ra;
	}

	protected static Vector3f quadOffSet(int quadrantsPerSide, int quadrant) {
		//Yes it's mad, but get a pen and paper and this is where a quadrant is

		float quadSize = LAND_SIZE / quadrantsPerSide;
		float halfQuadSize = quadSize / 2f;

		int qx = quadrant % quadrantsPerSide;
		int qy = quadrant / quadrantsPerSide;

		//-1 handles odd sizes
		float x = ((qx - (quadrantsPerSide / 2f)) * quadSize) + halfQuadSize;
		float y = ((qy - (quadrantsPerSide / 2f)) * quadSize) + halfQuadSize;
		return new Vector3f(x, 0, -y);
	}

	protected static GeometryArray makeQuadrantBaseSubGeom(	Color4f[][] colors, int quadrantsPerSide, int lod,
															int quadrant, int texCoordCount, int vertexAttrCount,
															int[] vertexAttrSizes, float[] hMinMax,
															short[] cellHeightMap) {
		int quadrantSquareCount = 64;
		float[][] quadrantHeights = new float[quadrantSquareCount][quadrantSquareCount];
		Color4f[][] quadrantColors = null;
		if (lod == 2 || lod == 3 || lod == 4)
			quadrantColors = new Color4f[quadrantSquareCount][quadrantSquareCount];
		TexCoord2f[][] quadrantTexCoords = new TexCoord2f[quadrantSquareCount][quadrantSquareCount];

		makeQuadrantData(lod, quadrant, colors, quadrantHeights, quadrantColors, quadrantTexCoords, hMinMax,
				cellHeightMap);

		//Note that 33 by 33 sets of point equals 32 by 32 set of triangles between them
		TESLANDGen gridGenerator = new TESLANDGen(LAND_SIZE / quadrantsPerSide, LAND_SIZE / quadrantsPerSide,
				quadrantSquareCount, quadrantSquareCount, quadrantHeights, null, quadrantColors, quadrantTexCoords);

		GeometryData terrainData = new GeometryData();

		//generator generates madness
		//if (STRIPIFY)
		//	gridGenerator.generateIndexedTriangleStrips(terrainData);
		//else
		gridGenerator.generateIndexedTriangles(terrainData);

		//offset for quadrant and location
		Vector3f offset = quadOffSet(quadrantsPerSide, quadrant);
		for (int i = 0; i < terrainData.coordinates.length; i += 3) {
			terrainData.coordinates[i + 0] += offset.x;
			terrainData.coordinates[i + 1] += offset.y;
			terrainData.coordinates[i + 2] += offset.z;
		}

		return createGA(terrainData, texCoordCount, vertexAttrCount, vertexAttrSizes);

	}

	/**
	 * 
	 * @param quadrant Specifies the quadrant this BTXT record applies to. 0 = bottom left. 1 = bottom right. 2 =
	 *            upper-left. 3 = upper-right.
	 * @param quadrant2
	 * @param baseHeights 128x128 array of all 4 quads
	 * @param baseColors 128x128 array of all 4 quads
	 * @param quadrantHeights 64x64 array to be filled
	 * @param quadrantNormals 64x64 array to be filled
	 * @param quadrantColors 64x64 array to be filled
	 */
	private static void makeQuadrantData(	int lod, int quadrant, Color4f[][] baseColors, float[][] quadrantHeights,
											Color4f[][] quadrantColors, TexCoord2f[][] quadrantTexCoords,
											float[] hMinMax, short[] cellHeightMap) {

		// N = 128 >> lod, buffer size = N * N, so lod0=128x128 shorts
		// 0 to 65535 maps to minimum height to maximum height
		// height map in pixelFormatGRAY16 format
		float minHeight = hMinMax[0] * HEIGHT_TO_J3D_SCALE;
		float maxHeight = hMinMax[1] * HEIGHT_TO_J3D_SCALE;

		//https://github.com/fo76utils/fo76utils/blob/main/src/terrmesh.cpp
		float span = maxHeight - minHeight;

		int quadrantSquareCount = 64;

		for (int row = 0; row < quadrantSquareCount; row++) {
			for (int col = 0; col < quadrantSquareCount; col++) {
				//I am inside a  a quadrant 0-3 so I'm 64x64 of the full 128x128
				//so quad 0 is 0-64,0-64 quad 1 = 65-128,0-64, quad 2 = 0-64,65-128, quad 3 = 65-128,65-128 
				int sourceCol = col;
				if (quadrant == 1 || quadrant == 3)
					sourceCol += quadrantSquareCount;

				int sourceRow = (quadrantSquareCount - 1) - row; // flipped probably the xyz to x-zy (0to63 becomes 63to0)
				if (quadrant == 2 || quadrant == 3)
					sourceRow += quadrantSquareCount;

				//quadrantHeights[row][col] = baseHeights[sourceRow][sourceCol];
				//terrain color block format (LOD3 and LOD2 only):
				if (lod == 2 || lod == 3)
					quadrantColors[row][col] = baseColors[sourceRow][sourceCol];
				quadrantTexCoords[row][col] = new TexCoord2f((row * TEX_REPEAT), (col * TEX_REPEAT));

				int sourceIdx = sourceCol + (sourceRow * GRID_COUNT); //row span is the full 128	 
				float height = (((cellHeightMap[sourceIdx] & 0xFFFF) / 65535f) * span) + minHeight;

				quadrantHeights[row][col] = height;

			}
		}
	}

	public static TextureUnitState getTexture(int textureFormID, IRecordStore master, TextureSource textureSource) {
		if (textureFormID > 0) {
			Record ltexRec = master.getRecord(textureFormID);
			if (ltexRec.getRecordType().equals("LTEX")) {
				TextureUnitState tus = null;
				LTEX ltex = new LTEX(ltexRec);
				int texSetId = ltex.textureSetId;

				if (texSetId != -1) {
					Record texSetRec = master.getRecord(texSetId);
					TXST textureSet = new TXST(texSetRec);
					if (textureSet.TX00 != null) {
						tus = textureSource.getTextureUnitState(textureSet.TX00);
					} else if (textureSet.MNAM != null) {
						// new fallout 4 texture system

						BSMaterialDataBGSM material = MaterialsSource.bgsmSource
								.getShaderMaterial("Materials\\" + textureSet.MNAM);
						if (material != null) {
							tus = textureSource.getTextureUnitState(material.DiffuseTexture);
						}

					}
				} else if (ltex.ICON != null) {
					//obliv uses simpler system					
					tus = textureSource.getTextureUnitState("Landscape\\" + ltex.ICON);
				} else if (ltex.materialName != null) {
					//Record matTypeRec = master.getRecord(ltex.materialTypeId);	
					//MATT matt = new MATT(matTypeRec);
					//tus = textureSource.getTextureUnitState(ltex.materialName);					
					BSMaterialDataBGSM material = MaterialsSource.bgsmSource.getShaderMaterial(ltex.materialName);

					//TODO: at this point I have a full shaderable set of normals and smooth etc etc					
					tus = textureSource.getTextureUnitState(material.DiffuseTexture);

				}
				return tus;
			} else {
				System.out.println(
						"Bad textureFormID " + textureFormID + " type is not LTEX but " + ltexRec.getRecordType());
			}

		}
		return null;

	}

	private float[][] extractHeights(float[] hMinMax, short[] cellHeightMap) {
		// extract the heights
		float[][] heights = new float[GRID_COUNT][GRID_COUNT];

		// N = 128 >> lod, buffer size = N * N, so lod0=128x128 shorts
		// 0 to 65535 maps to minimum height to maximum height
		// height map in pixelFormatGRAY16 format
		float minHeight = hMinMax[0] * HEIGHT_TO_J3D_SCALE;
		float maxHeight = hMinMax[1] * HEIGHT_TO_J3D_SCALE;

		float span = maxHeight - minHeight;

		//TODO: lower lods less grid count, like a far for tes3
		for (int row = 0; row < GRID_COUNT; row++) {
			for (int col = 0; col < GRID_COUNT; col++) {
				int idx = col + (row * GRID_COUNT);
				float h = (((cellHeightMap[idx] & 0xFFFF) / 65535f) * span) + minHeight;
				// note reverse order, due to x,y,z => x,z,-y
				heights[(GRID_COUNT - 1) - row][col] = h;

				//update lowest highest
				lowestHeight = h < lowestHeight ? h : lowestHeight;
				highestHeight = h > highestHeight ? h : highestHeight;
			}
		}

		return heights;
	}

	protected static Color4f[][] extractColors(short[] cellTerrainColor) {

		//type in packed 16-bit format, 5 bits per channel
		//https://learn.microsoft.com/en-us/windows/win32/directshow/working-with-16-bit-rgb

		Color4f[][] colors = new Color4f[GRID_COUNT][GRID_COUNT];

		for (int row = 0; row < GRID_COUNT; row++) {
			for (int col = 0; col < GRID_COUNT; col++) {
				if (cellTerrainColor != null) {
					int idx = col + (row * GRID_COUNT);
					short pixel = cellTerrainColor[idx];

					// useful 565
					//int r = ((pixel & 0xF800) >> 11) << 3;
					//int g = ((pixel & 0x7E0) >> 5) << 2;
					//int b = ((pixel & 0x1F) >> 0) << 3;					

					// less common, wasteful 555
					int r = ((pixel & 0x7C00) >> 10) << 3;
					int g = ((pixel & 0x3E0) >> 5) << 3;
					int b = ((pixel & 0x1F) >> 0) << 3;

					Color4f c = new Color4f(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f);//note hard coded opaque

					// note reverse order, due to x,y,z => x,z,-y
					colors[(GRID_COUNT - 1) - row][col] = c;
				} else {
					// no colors let's try white
					colors[(GRID_COUNT - 1) - row][col] = new Color4f(1.0f, 1.0f, 1.0f, 1.0f);
				}

			}
		}

		return colors;
	}

	/**
	 * texCoordCount is overrriden to 1
	 * @param terrainData
	 * @param texCoordCount
	 * @param vertexAttrCount
	 * @param vertexAttrSizes
	 * @return
	 */
	public static GeometryArray createGA(	GeometryData terrainData, int texCoordCount, int vertexAttrCount,
											int[] vertexAttrSizes) {

		int basicFormat = GeometryArray.COORDINATES | GeometryArray.NORMALS | GeometryArray.COLOR_4 //
							| GeometryArray.TEXTURE_COORDINATE_2 //
							| GeometryArray.USE_COORD_INDEX_ONLY //
							| (BY_REF
								|| STRIPIFY ? (GeometryArray.BY_REFERENCE_INDICES | GeometryArray.BY_REFERENCE) : 0)//
							| (BUFFERS ? GeometryArray.USE_NIO_BUFFER : 0) //
							| (vertexAttrCount > 0 ? GeometryArray.VERTEX_ATTRIBUTES : 0);

		texCoordCount = 1;
		int[] texMap = new int[texCoordCount];
		for (int i = 0; i < texCoordCount; i++)
			texMap[i] = i;

		IndexedGeometryArray iga;
		if (INTERLEAVE) {
			if (STRIPIFY) {
				iga = new IndexedTriangleStripArray(terrainData.vertexCount, basicFormat | GeometryArray.INTERLEAVED, //
						texCoordCount, texMap, vertexAttrCount, vertexAttrSizes, terrainData.indexesCount,
						terrainData.stripCounts);
			} else {
				iga = new IndexedTriangleArray(terrainData.vertexCount, basicFormat | GeometryArray.INTERLEAVED, //
						texCoordCount, texMap, vertexAttrCount, vertexAttrSizes, terrainData.indexesCount);
			}
			iga.setCoordIndicesRef(terrainData.indexes);

			float[] vertexData = J3dNiTriBasedGeom.interleave(2, new float[][] {terrainData.textureCoordinates}, null,
					terrainData.colors, terrainData.normals, terrainData.coordinates);

			if (!BUFFERS) {
				iga.setInterleavedVertices(vertexData);
			} else {
				iga.setInterleavedVertexBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(vertexData)));
			}

		} else {
			if (STRIPIFY) {
				//	iga=null;
				iga = new IndexedTriangleStripArray(terrainData.vertexCount, basicFormat, texCoordCount, texMap,
						vertexAttrCount, vertexAttrSizes, //
						terrainData.indexesCount, terrainData.stripCounts);
			} else {
				iga = new IndexedTriangleArray(terrainData.vertexCount, basicFormat, texCoordCount, texMap,
						vertexAttrCount, vertexAttrSizes, //
						terrainData.indexesCount);
			}

			if (!BY_REF) {
				iga.setCoordinates(0, terrainData.coordinates);
				iga.setCoordinateIndices(0, terrainData.indexes);
				iga.setNormals(0, terrainData.normals);
				iga.setColors(0, terrainData.colors);
				iga.setTextureCoordinates(0, 0, terrainData.textureCoordinates);
			} else {
				if (!BUFFERS) {
					iga.setCoordRefFloat(terrainData.coordinates);
					iga.setCoordIndicesRef(terrainData.indexes);
					iga.setNormalRefFloat(terrainData.normals);
					iga.setColorRefFloat(terrainData.colors);
					iga.setTexCoordRefFloat(0, terrainData.textureCoordinates);
				} else {

					/*			GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);
								gi.setCoordinateIndices(terrainData.indexes);
								gi.setUseCoordIndexOnly(true);
								gi.setCoordinates(terrainData.coordinates);
								gi.setColors4(terrainData.colors);
								gi.setNormals(terrainData.normals);
								gi.setTextureCoordinateParams(1, 2);
								gi.setTextureCoordinates(0, terrainData.textureCoordinates);
					
							
								Stripifier stripifer = new Stripifier();
								stripifer.stripify(gi);
								iga = gi.getIndexedGeometryArray(true, true, false, true, true);
					*/

					iga.setCoordRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.coordinates)));
					iga.setCoordIndicesRef(terrainData.indexes);
					iga.setNormalRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.normals)));
					iga.setColorRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.colors)));
					iga.setTexCoordRefBuffer(0, new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.textureCoordinates)));

				}
			}

		}

		return iga;

	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	private static void createShaderProgram() {// in case 2 threads come in trying to lazy create
		synchronized (shaderLock) {
			if (shaderProgram == null) {
				String vertexProgram = ShaderSourceIO.getTextFileAsString("shaders/land_btdb.vert");
				String fragmentProgram = ShaderSourceIO.getTextFileAsString("shaders/land_btdb.frag");

				Shader[] shaders = new Shader[2];
				shaders[0] = new SourceCodeShader(Shader.SHADING_LANGUAGE_GLSL, Shader.SHADER_TYPE_VERTEX,
						vertexProgram) {
					@Override
					public String toString() {
						return "vertexProgram";
					}
				};
				shaders[1] = new SourceCodeShader(Shader.SHADING_LANGUAGE_GLSL, Shader.SHADER_TYPE_FRAGMENT,
						fragmentProgram) {
					@Override
					public String toString() {
						return "fragmentProgram";
					}
				};

				shaderProgram = new GLSLShaderProgram() {
					@Override
					public String toString() {
						return "Land Shader Program";
					}
				};
				shaderProgram.setShaders(shaders);

				String[] shaderAttrNames = new String[6];

				shaderAttrNames[0] = "baseMap";
				for (int i = 0; i < 5; i++) {
					shaderAttrNames[i + 1] = "layerMap" + i;
					if (OUTPUT_BINDINGS)
						System.out.println("shaderAttrNames " + shaderAttrNames[i]);
				}

				shaderProgram.setShaderAttrNames(shaderAttrNames);

				String[] vertexAttrNames = new String[] {"alphas03", "alphas47"};
				shaderProgram.setVertexAttrNames(vertexAttrNames);

			}
		}
	}

	@Override
	public float getLowestHeight() {
		return lowestHeight;
	}

	@Override
	public float getHighestHeight() {
		return highestHeight;
	}

	// So the btdb sub class can override
	@Override
	public float getTERRIAN_SQUARE_SIZE() {
		return LAND_SIZE / GRID_COUNT;
	}

	/*
	 void vertexNormals(DDSOutputFile& outFile, BTDFile& btdFile,
	               unsigned char l, int xMin, int yMin, int xMax, int yMax)
	{
	btdFile.setTileCacheSize(size_t((((xMax + 1 - xMin) + 7) >> 3) + 1));
	int     cellResolution = 128 >> l;
	unsigned char m = 7 - l;
	int     w = (xMax + 1 - xMin) << m;
	int     h = (yMax + 1 - yMin) << m;
	std::vector< std::uint16_t >  buf((size_t(w) * size_t(h)) << 1, 0);
	std::vector< std::uint16_t >  cellBuf(size_t(16384 >> (l + l)), 0);
	float   zMin = btdFile.getMinHeight();
	float   xyScale = 4096.0f / float(cellResolution);
	float   xyScale2 = xyScale * 65535.0f / (btdFile.getMaxHeight() - zMin);
	for (int y = 0; y < h; y++)
	{
	if (!y || !((y + (cellResolution >> 1)) & (cellResolution - 1)))
	{
	  int     cellY = yMax - ((y >> m) + (!y ? 0 : 1));
	  if (cellY >= yMin)
	  {
	    for (int cellX = xMin; cellX <= xMax; cellX++)
	    {
	      std::uint16_t *srcPtr = cellBuf.data();
	      btdFile.getCellHeightMap(srcPtr, cellX, cellY, l);
	      for (int yc = 0; yc < cellResolution; yc++)
	      {
	        std::uint16_t *dstPtr = buf.data();
	        dstPtr = dstPtr + (size_t((cellResolution << ((yMax - cellY) & 1))
	                                  - (yc + 1)) * size_t(w));
	        dstPtr = dstPtr + (size_t(cellX - xMin) << m);
	        for (int xc = 0; xc < cellResolution; xc++, srcPtr++, dstPtr++)
	          *dstPtr = *srcPtr;
	      }
	    }
	  }
	}
	FloatVector4  prvNormalSE(0.0f);
	FloatVector4  prvNormalNE(0.0f);
	for (int x = 0; x < w; x++)
	{
	  // 0, N, S, E, NW, SW, SE, NE, W
	  static const int  xOffsTable[9] = { 0, 0, 0, 1, -1, -1, 1, 1, -1 };
	  static const int  yOffsTable[9] = { 0, -1, 1, 0, -1, 1, 1, -1, 0 };
	  float   z[9];
	  for (int i = 0; i < 9; i++)
	  {
	    int     xc = x + xOffsTable[i];
	    int     yc = y + yOffsTable[i];
	    xc = (xc > 0 ? (xc < (w - 1) ? xc : (w - 1)) : 0);
	    yc = (yc > 0 ? (yc < (h - 1) ? yc : (h - 1)) : 0);
	    yc = yc & ((cellResolution << 1) - 1);
	    z[i] = float(int(buf[size_t(yc) * size_t(w) + size_t(xc)]));
	  }
	  FloatVector4  v_n(0.0f, xyScale2, z[1] - z[0], 0.0f);
	  FloatVector4  v_s(0.0f, -xyScale2, z[2] - z[0], 0.0f);
	  FloatVector4  v_e(xyScale2, 0.0f, z[3] - z[0], 0.0f);
	  FloatVector4  normal;
	  if ((x ^ y) & 1)
	  {
	    //    0 1 2
	    // -1 +-+-+
	    //    |\|/|
	    //  0 +-+-+
	    //    |/|\|
	    //  1 +-+-+
	    FloatVector4  v_nw(-xyScale2, xyScale2, z[4] - z[0], 0.0f);
	    FloatVector4  v_sw(-xyScale2, -xyScale2, z[5] - z[0], 0.0f);
	    FloatVector4  v_se(xyScale2, -xyScale2, z[6] - z[0], 0.0f);
	    FloatVector4  v_ne(xyScale2, xyScale2, z[7] - z[0], 0.0f);
	    normal = calculateNormal(v_ne, v_n);
	    normal += calculateNormal(v_n, v_nw);
	    if (!x)
	    {
	      FloatVector4  v_w(-xyScale2, 0.0f, z[8] - z[0], 0.0f);
	      prvNormalNE = calculateNormal(v_nw, v_w);
	      prvNormalSE = calculateNormal(v_w, v_sw);
	    }
	    normal += prvNormalNE;
	    normal += prvNormalSE;
	    normal += calculateNormal(v_sw, v_s);
	    normal += calculateNormal(v_s, v_se);
	    prvNormalSE = calculateNormal(v_se, v_e);
	    normal += prvNormalSE;
	    prvNormalNE = calculateNormal(v_e, v_ne);
	    normal += prvNormalNE;
	  }
	  else
	  {
	    if (!x)
	    {
	      FloatVector4  v_w(-xyScale2, 0.0f, z[8] - z[0], 0.0f);
	      prvNormalNE = calculateNormal(v_n, v_w);
	      prvNormalSE = calculateNormal(v_w, v_s);
	    }
	    normal = prvNormalNE;
	    normal += prvNormalSE;
	    prvNormalSE = calculateNormal(v_s, v_e);
	    normal += prvNormalSE;
	    prvNormalNE = calculateNormal(v_e, v_n);
	    normal += prvNormalNE;
	  }
	  normal.normalize();
	  normal += 1.0f;
	  normal *= 127.5f;
	  unsigned int  c = (unsigned int) normal;
	  outFile.writeByte((unsigned char) ((c >> 16) & 0xFFU));   // B
	  outFile.writeByte((unsigned char) ((c >> 8) & 0xFFU));    // G
	  outFile.writeByte((unsigned char) (c & 0xFFU));           // R
	}
	}
	}
	 */

	///////////////////////
	//These 2 taken from NiGeometryAppearanceShader

	/*private String fileName(BSLightingShaderProperty bslsp, int textureSlot) {
		if (bslsp != null) {
			// FO4 has material files pointed at by name
			BSMaterialDataBGSM material = getMaterial(bslsp);
			if (material != null) {
				switch (textureSlot) {
					case 0:
						return material.DiffuseTexture;
					case 1:
						return material.NormalTexture;
					case 2:
						return material.SmoothSpecTexture;
					case 3:
						return material.GreyscaleTexture;
					case 4:
						return material.EnvmapTexture;
					case 5:
						return material.GlowTexture;
					case 6:
						return material.InnerLayerTexture;
					case 7:
						return material.WrinklesTexture;
					case 8:
						return material.DisplacementTexture;
					default:
						System.out.println(
								"fileName(BSLightingShaderProperty bslsp, int textureSlot) bad slot " + textureSlot);
						break;
				}
			} else if (bslsp.TextureSet != null && bslsp.TextureSet.ref != -1) {
				BSShaderTextureSet texSet = (BSShaderTextureSet)niToJ3dData.get(bslsp.TextureSet);
				return texSet.textures[textureSlot];
			} else if (bslsp.TextureArrays != null && bslsp.TextureArrays.length > textureSlot) {
				// these can be up to 175 "wide" I'm gonna use the first one for fun
				return bslsp.TextureArrays[textureSlot].TextureArray[0];
			}
	
		}
		return "";
	
	}*/
}
