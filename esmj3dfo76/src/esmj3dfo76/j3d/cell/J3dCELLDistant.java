package esmj3dfo76.j3d.cell;

import java.util.Iterator;
import java.util.List;

import org.jogamp.java3d.Node;

import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.j3d.cell.J3dCELLGeneral;
import utils.source.MediaSources;

/**
 * Distant after are fake made of lod data from stats, but not overlapping nif file loads
 */
public class J3dCELLDistant extends J3dCELL
{	
	public J3dCELLDistant(IRecordStore master, Record cellRecord, int worldId, List<Record> children, boolean makePhys, MediaSources mediaSources)
	{
		super(master, cellRecord, worldId, makePhys, mediaSources);
		indexRecords(children);
	}

	private void indexRecords(List<Record> children)
	{
		for (Iterator<Record> i = children.iterator(); i.hasNext();)
		{
			while(J3dCELLGeneral.PAUSE_CELL_LOADING) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {					
				}
			}
			Record record = i.next();

			// a lot of  stats are part of distant (if they have the lod flag on)
			if (isVisibleDistant(record))
			{
				Node n = makeJ3dRECOFar(record);
				addChild(n);
			}
		}
	}
}
