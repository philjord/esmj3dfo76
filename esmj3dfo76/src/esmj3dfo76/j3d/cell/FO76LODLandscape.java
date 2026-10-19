package esmj3dfo76.j3d.cell;

import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.vecmath.Quat4f;
import org.jogamp.vecmath.Vector3f;

import esfilemanager.btd.BtdReaderFile;
import esfilemanager.common.data.record.IRecordStore;

import esmj3d.j3d.j3drecords.inst.J3dLAND;
import esmj3dfo76.j3d.j3drecords.inst.J3dLANDBtdb;
import esmj3dfo76.j3d.j3drecords.inst.J3dLANDBtdbFar;
import utils.source.MeshSource;
import utils.source.TextureSource;

public class FO76LODLandscape extends BranchGroup {
	public FO76LODLandscape(int lodX, int lodY, int lod, String worldFormName, MeshSource meshSource,
							TextureSource textureSource, IRecordStore master) {
		setCapability(BranchGroup.ALLOW_DETACH);

		synchronized (BtdReaderFile.class) {
			if (J3dLANDBtdb.btdReaderFile == null) {
				try {
					J3dLANDBtdb.btdReaderFile = new BtdReaderFile(
							"D:\\game_media\\Fallout76\\Data\\Terrain\\Appalachia.btd");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (J3dLANDBtdb.btdReaderFile != null) {
			//lod 4 uses a faster rougher system
			if (lod == 4) {
				J3dLANDBtdbFar j3dLAND = new J3dLANDBtdbFar(J3dLANDBtdb.btdReaderFile.getBtdbReader(), lodX, lodY, lod,
						master, textureSource);

				// apparently we need a half grid offset this time
				Vector3f cellLoc = new Vector3f((lodX * J3dLAND.LAND_SIZE), 0, (-lodY * J3dLAND.LAND_SIZE));
				// my terrain J3dLANDBtdb centralize itself so
				cellLoc.x += J3dLANDBtdbFar.LAND_SIZE / 2;
				cellLoc.z -= J3dLANDBtdbFar.LAND_SIZE / 2;
				
				TransformGroup tg = new TransformGroup();
				Transform3D t = new Transform3D(new Quat4f(0, 0, 0, 1), cellLoc, 1);
				tg.setTransform(t);
				tg.addChild(j3dLAND);

				/*float wl = getWaterLevel(cell.XCLW);
				if (wl > j3dLAND.getLowestHeight()) {
					addChild(makeWater(wl, J3dCELLPersistent.waterApp));
				}*/
				addChild(tg);

			} else {
				J3dLANDBtdb j3dLAND = new J3dLANDBtdb(J3dLANDBtdb.btdReaderFile.getBtdbReader(), -1, lodX, lodY, lod,
						master, textureSource);

				// apparently we don't need a half grid offset this time
				Vector3f cellLoc = new Vector3f((lodX * J3dLAND.LAND_SIZE), 0, (-lodY * J3dLAND.LAND_SIZE));
				//cellLoc.x -= J3dLAND.LAND_SIZE / 2.0f;
				//cellLoc.z += J3dLAND.LAND_SIZE / 2.0f; // a minus minus

				TransformGroup tg = new TransformGroup();
				Transform3D t = new Transform3D(new Quat4f(0, 0, 0, 1), cellLoc, 1);
				tg.setTransform(t);
				tg.addChild(j3dLAND);

				/*float wl = getWaterLevel(cell.XCLW);
				if (wl > j3dLAND.getLowestHeight()) {
					addChild(makeWater(wl, J3dCELLPersistent.waterApp));
				}*/
				addChild(tg);
			}
		}
	}

}
