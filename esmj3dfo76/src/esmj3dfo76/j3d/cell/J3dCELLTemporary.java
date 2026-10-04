package esmj3dfo76.j3d.cell;

import java.util.Iterator;
import java.util.List;

import org.jogamp.vecmath.Quat4f;
import org.jogamp.vecmath.Vector3f;

import esfilemanager.btd.BtdReaderFile;
import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.j3d.cell.J3dCELLGeneral;
import esmj3d.j3d.j3drecords.inst.J3dRECOInst;
import esmj3dfo76.j3d.j3drecords.inst.J3dLANDBtdb;
import utils.source.MediaSources;

public class J3dCELLTemporary extends J3dCELL {

	private static BtdReaderFile btdReaderFile;

	public J3dCELLTemporary(IRecordStore master, Record cellRecord, int worldId, List<Record> children,
							boolean makePhys, MediaSources mediaSources) {
		super(master, cellRecord, worldId, makePhys, mediaSources);
		indexRecords(children);

		if (btdReaderFile == null) {
			try {
				btdReaderFile = new BtdReaderFile("D:\\game_media\\Fallout76\\Data\\Terrain\\Appalachia.btd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// this would normally be done in the makeJ3dRECO(record); below but it isn't a record any more
		if (btdReaderFile != null ) {
			//we need to make up a record id, 1 land per cell so let's try this 
			int fakeFormId = -cellRecord.getFormID();
			
			Vector3f loc = getInstCell().getTrans();
			if (makePhys) {
				j3dLAND = new J3dLANDBtdb(btdReaderFile.getBtdbReader(), fakeFormId, (int)loc.x, (int)loc.y);
			} else {
				j3dLAND = new J3dLANDBtdb(btdReaderFile.getBtdbReader(), fakeFormId, (int)loc.x, (int)loc.y, master,
						mediaSources.getTextureSource());
			}
			
			// apparently we need a half grid offset this time
			Vector3f cellLoc = new Vector3f(cellLocation);
			cellLoc.x -= J3dLANDBtdb.LAND_SIZE / 2.0f;
			cellLoc.z += J3dLANDBtdb.LAND_SIZE/ 2.0f; // a minus minus
			
			j3dLAND.setLocation(cellLoc, new Quat4f(0, 0, 0, 1));

			float wl = getWaterLevel(cell.XCLW);
			if (wl > j3dLAND.getLowestHeight()) {
				addChild(makeWater(wl, J3dCELLPersistent.waterApp));
			}

			addJ3dRECOInst(j3dLAND);
						
			j3dRECOs.put(fakeFormId, j3dLAND);
		}

	}

	private void indexRecords(List<Record> children) {
		for (Iterator<Record> i = children.iterator(); i.hasNext();) {
			while (J3dCELLGeneral.PAUSE_CELL_LOADING) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}

			Record record = i.next();
			J3dRECOInst jri = makeJ3dRECO(record);
			addJ3dRECOInst(jri);

		}
	}
}
