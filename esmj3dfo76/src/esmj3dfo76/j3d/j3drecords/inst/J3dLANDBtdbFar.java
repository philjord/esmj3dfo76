package esmj3dfo76.j3d.j3drecords.inst;

import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.GLSLShaderProgram;
import org.jogamp.java3d.GeometryArray;
import org.jogamp.java3d.Group;
import org.jogamp.java3d.IndexedGeometryArray;
import org.jogamp.java3d.IndexedTriangleArray;
import org.jogamp.java3d.J3DBuffer;
import org.jogamp.java3d.Material;
import org.jogamp.java3d.RenderingAttributes;
import org.jogamp.java3d.Shader;
import org.jogamp.java3d.ShaderAppearance;
import org.jogamp.java3d.ShaderAttributeObject;
import org.jogamp.java3d.ShaderAttributeSet;
import org.jogamp.java3d.ShaderAttributeValue;
import org.jogamp.java3d.ShaderProgram;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.SourceCodeShader;
import org.jogamp.java3d.geom.GeometryData;
import org.jogamp.vecmath.Color4f;
import org.jogamp.vecmath.Vector2f;

import esfilemanager.btd.BtdbReader;
import esfilemanager.common.data.record.IRecordStore;
import esmj3d.j3d.TESLANDGen;
import esmj3d.j3d.cell.BtdbLodManager;
import nif.shader.ShaderSourceIO;
import tools3d.utils.Utils3D;
import utils.convert.ConvertFromNif;
import utils.source.TextureSource;

// https://ghoul-earth.com/ tells me what it should look like

public class J3dLANDBtdbFar extends BranchGroup {

	//LOD0 is 128 grid all others are 64
	//where LOD1 is the normal land size, 
	// LOD2 is 
	private int						GRID_COUNT			= 64;						//NOTE the 32 of the other one is a 33 wide grid for 32 "flats"

	//public static final float		TERRIAN_SQUARE_SIZE	= ConvertFromNif.toJ3d(128);	// = 1.6256m

	//	NOTE nif x,y,z to j3d x,z,-y
	//-700.0, mimimum height  38209.996, maximum height, I see rocks placed at 470m so maths
	//using a value of 1 here I get the correct looking world min=-700.0 max=38209.996 min=-8.89 max=485.26694
	public static float				HEIGHT_TO_J3D_SCALE	= ConvertFromNif.toJ3d(1f);	//half inch now 0.0127m

	public static float				BASE_LAND_SIZE		= 52.0192f;					// make it fixed 4096 nif unit per land = this many meters, regardless of grid size
	public static float				LAND_SIZE			= BASE_LAND_SIZE * 8;

	private static ShaderProgram	shaderProgram		= null;

	private static final Object		shaderLock			= new Object();

	public J3dLANDBtdbFar(	BtdbReader btdbReader, int x, int y, int lod, IRecordStore master,
							TextureSource textureSource) {
		if (lod < 1 || lod > 4)
			System.err.println("J3dLANDBtdbFar Bad bad lod! " + lod);

		if (btdbReader != null) {

			if (lod == 4) {
				//ok new new news
				//lod 4 only needs the heights and colors now it's a mesh with a rought texture
				// the opacities are there for the lower lod numbers to use cos heights there 
				//already so let's be consistent, yay
				// lod 4 want 2 shorts as attributes and ZERO vertex floats!
				// I wonder, wendy. If I don't give vertex format GeometryArray.COORDINATES will it try and render
				// with just the attributes? of course I'd need to manually set the bounds for clipping

				Group baseGroup = new Group();
				addChild(baseGroup);
				//ensure shader ready
				createShaderProgram();
				// extract the heights, unless this is supposed to be the global min and max heights of course?
				float[] hMinMax = new float[2];

				hMinMax[0] = btdbReader.getMinHeight();
				hMinMax[1] = btdbReader.getMaxHeight();

				short[] cellHeightMap = new short[64 * 64];
				short[] cellTerrainColor = new short[64 * 64];
				btdbReader.getLOD4Data(cellHeightMap, cellTerrainColor, x, y);

				ShaderAppearance app = new ShaderAppearance();
				app.setMaterial(createMat());
				app.setShaderProgram(shaderProgram);
				app.setShaderAttributeSet(BtdbLodManager.shaderAttributeSet);

				Shape3D baseQuadShape = new Shape3D();
				baseQuadShape.setAppearance(app);

				int gridSquareCount = 64;
				float[][] quadrantHeights = new float[gridSquareCount][gridSquareCount];
				Color4f[][] quadrantColors = new Color4f[gridSquareCount][gridSquareCount];

				// N = 128 >> lod, buffer size = N * N, so lod0=128x128 shorts
				// 0 to 65535 maps to minimum height to maximum height
				// height map in pixelFormatGRAY16 format
				float minHeight = hMinMax[0] * HEIGHT_TO_J3D_SCALE;
				float maxHeight = hMinMax[1] * HEIGHT_TO_J3D_SCALE;

				//https://github.com/fo76utils/fo76utils/blob/main/src/terrmesh.cpp
				float span = maxHeight - minHeight;

				for (int row = 0; row < gridSquareCount; row++) {
					for (int col = 0; col < gridSquareCount; col++) {
						//I am inside a  a quadrant 0-3 so I'm 64x64 of the full 128x128
						//so quad 0 is 0-64,0-64 quad 1 = 65-128,0-64, quad 2 = 0-64,65-128, quad 3 = 65-128,65-128 
						int sourceCol = col;
						int sourceRow = (gridSquareCount - 1) - row; // flipped probably the xyz to x-zy (0to63 becomes 63to0)

						int sourceIdx = sourceCol + (sourceRow * GRID_COUNT); //row span is the full 128	 
						float height = (((cellHeightMap[sourceIdx] & 0xFFFF) / 65535f) * span) + minHeight;
						quadrantHeights[row][col] = height;

						short pixel = cellTerrainColor[sourceIdx];

						// useful 565
						//int r = ((pixel & 0xF800) >> 11) << 3;
						//int g = ((pixel & 0x7E0) >> 5) << 2;
						//int b = ((pixel & 0x1F) >> 0) << 3;					

						// less common, wasteful 555
						int r = ((pixel & 0x7C00) >> 10) << 3;
						int g = ((pixel & 0x3E0) >> 5) << 3;
						int b = ((pixel & 0x1F) >> 0) << 3;

						Color4f c = new Color4f(r / 255.0f, g / 255.0f, b / 255.0f, 1.0f);//note hard coded opaque
						quadrantColors[row][col] = c;

					}
				}

				//Note that 33 by 33 sets of point equals 32 by 32 set of triangles between them
				TESLANDGen gridGenerator = new TESLANDGen(LAND_SIZE, LAND_SIZE, gridSquareCount, gridSquareCount,
						quadrantHeights, null, quadrantColors, null);

				GeometryData terrainData = new GeometryData();
				gridGenerator.generateIndexedTriangles(terrainData);

				//GeometryArray ga = createGA(terrainData, texCoordCount, vertexAttrCount, vertexAttrSizes);

				int basicFormat = GeometryArray.COORDINATES | GeometryArray.COLOR_4 //						
									| GeometryArray.USE_COORD_INDEX_ONLY //
									| (GeometryArray.BY_REFERENCE_INDICES | GeometryArray.BY_REFERENCE)//
									| GeometryArray.USE_NIO_BUFFER;

				IndexedGeometryArray iga = new IndexedTriangleArray(terrainData.vertexCount, basicFormat,
						terrainData.indexesCount);

				iga.setCoordRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.coordinates)));
				iga.setCoordIndicesRef(terrainData.indexes);
				//iga.setNormalRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.normals)));
				iga.setColorRefBuffer(new J3DBuffer(Utils3D.makeFloatBuffer(terrainData.colors)));

				iga.setName("LAND Lod4 " + " " + x + " " + y);
				baseQuadShape.setGeometry(iga);				
				baseGroup.addChild(baseQuadShape);

			} else {
				throw new UnsupportedOperationException("No only Lod4 here please");
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	private static void createShaderProgram() {// in case 2 threads come in trying to lazy create
		synchronized (shaderLock) {
			if (shaderProgram == null) {
				String vertexProgram = ShaderSourceIO.getTextFileAsString("shaders/landlod_btdb.vert");
				String fragmentProgram = ShaderSourceIO.getTextFileAsString("shaders/landlod_btdb.frag");

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
				shaderProgram.setShaderAttrNames(new String[] { "minXYRemoval", "maxXYRemoval" });			 
				
				
				// this set is in the BtdbLodManager so it can have values updated easily
				BtdbLodManager.shaderAttributeSet.setCapability(ShaderAttributeSet.ALLOW_ATTRIBUTES_READ);
								
				// this moves the uodateVisibility code into the shader
				Vector2f minXYRemoval = new Vector2f(10,0);
				Vector2f maxXYRemoval = new Vector2f(0,0);
				ShaderAttributeValue sav0 = new ShaderAttributeValue("minXYRemoval", minXYRemoval);
				ShaderAttributeValue sav1 = new ShaderAttributeValue("maxXYRemoval", maxXYRemoval);
				sav0.setCapability(ShaderAttributeObject.ALLOW_VALUE_WRITE);
				sav1.setCapability(ShaderAttributeObject.ALLOW_VALUE_WRITE);
				BtdbLodManager.shaderAttributeSet.put(sav0);
				BtdbLodManager.shaderAttributeSet.put(sav1);
			}
		}
	}

}
