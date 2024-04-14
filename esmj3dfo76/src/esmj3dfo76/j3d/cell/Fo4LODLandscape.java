package esmj3dfo76.j3d.cell;

import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.IndexedGeometryArray;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Quat4f;
import org.jogamp.vecmath.Vector3f;

import esmj3d.j3d.cell.MorphingLandscape;
import esmj3d.j3d.j3drecords.inst.J3dLAND;
import nif.NiObjectList;
import nif.NifFile;
import nif.NifToJ3d;
import nif.basic.NifRef;
import nif.j3d.J3dBSTriShape;
import nif.niobject.NiAVObject;
import nif.niobject.NiObject;
import nif.niobject.bs.BSLightingShaderProperty;
import nif.niobject.bs.BSMultiBoundNode;
import nif.niobject.bs.BSShaderTextureSet;
import nif.niobject.bs.BSTriShape;
import utils.source.MeshSource;
import utils.source.TextureSource;

public class Fo4LODLandscape extends MorphingLandscape
{
	public Fo4LODLandscape(int lodX, int lodY, int scale, String worldFormName, MeshSource meshSource, TextureSource textureSource)
	{
		super(lodX, lodY, scale);
		String meshName = "terrain\\" + worldFormName + "\\" + worldFormName + "." + scale + "." + lodX + "." + lodY + ".btr";

		setCapability(BranchGroup.ALLOW_DETACH);
		if (meshSource.nifFileExists(meshName))
		{
			NifFile nf = NifToJ3d.loadNiObjects(meshName, meshSource);
			if (nf != null)
			{
				NiObjectList blocks = nf.blocks;
				BSMultiBoundNode root = (BSMultiBoundNode) blocks.root();

				TransformGroup tg = new TransformGroup();
				Transform3D t = new Transform3D(new Quat4f(0, 0, 0, 1),
						new Vector3f((lodX * J3dLAND.LAND_SIZE), 0, (-lodY * J3dLAND.LAND_SIZE)), scale);
				tg.setTransform(t);

				for (NifRef cnr : root.children)
				{
					NiAVObject child = (NiAVObject) nf.blocks.get(cnr);

					if (child instanceof BSTriShape)
					{
						// regular lod terrains
						BSTriShape bsTriShape = (BSTriShape) child;

						//scale 4 will get morph treatment later
						boolean morphable = (scale == 4);
						IndexedGeometryArray baseItsa = J3dBSTriShape.createGeometry(bsTriShape, morphable);

						//TODO: currently trouble with a morphable that has no skin
						if (baseItsa != null)
						{
							if (morphable)
							{
								this.addGeometryArray(baseItsa);
							}

							Shape3D shape = new Shape3D();
							shape.setGeometry(baseItsa);

							BSLightingShaderProperty lp = getLightingProperty(bsTriShape, blocks);
							if (lp != null)
							{
								BSShaderTextureSet ts = (BSShaderTextureSet) blocks.get(lp.TextureSet);
								shape.setAppearance(createAppearance(textureSource.getTexture(ts.textures[0])));
								tg.addChild(shape);
							}
							else
							{
								System.out.println("unpropertied bsTriShape in lod " + bsTriShape + " in " + meshName);
							}
						}
					}
					else if (child instanceof BSMultiBoundNode)
					{
						//has water under it's own bounds node
						BSMultiBoundNode waterRoot = (BSMultiBoundNode) child;

						for (NifRef wcnr : waterRoot.children)
						{
							NiAVObject waterChild = (NiAVObject) nf.blocks.get(wcnr);

							if (waterChild instanceof BSTriShape)
							{
								// regular lod terrains
								BSTriShape bsTriShape = (BSTriShape) waterChild;

								//scale 4 will get morph treatment later
								boolean morphable = (scale == 4);
								IndexedGeometryArray baseItsa = J3dBSTriShape.createGeometry(bsTriShape, morphable);
								//TODO: currently trouble with a morphable that has no skin
								if (baseItsa != null)
								{
									if (morphable)
									{
										this.addGeometryArray(baseItsa);
									}

									Shape3D shape = new Shape3D();
									shape.setGeometry(baseItsa);

									BSLightingShaderProperty lp = getLightingProperty(bsTriShape, blocks);
									if (lp != null)
									{
										System.out.println("FO4 water child has properties! " + waterChild + " in " + meshName);
									}
									else
									{
										shape.setAppearance(createBasicWaterApp());
										tg.addChild(shape);
									}
								}
							}
							else
							{
								System.out.println("Lod file water child odd " + waterChild + " in " + meshName);
							}
						}
					}
					else
					{
						System.out.println("Lod file child odd " + child + " in " + meshName);
					}
				}

				addChild(tg);
			}
			else
			{
				System.out.println("Bad landscape NifFile " + meshName);
			}
		}
		else
		{
			//fine the systems just asking for empty space which is fine
			//System.out.println("Bad landscape name " + meshName);
		}
	}

	private static BSLightingShaderProperty getLightingProperty(BSTriShape bsTriShape, NiObjectList blocks)
	{
		for (NifRef pnr : bsTriShape.properties)
		{
			NiObject p = blocks.get(pnr);
			if (p instanceof BSLightingShaderProperty)
				return (BSLightingShaderProperty) p;
		}
		return null;
	}
}
