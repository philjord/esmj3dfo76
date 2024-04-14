package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/CombatStyle
 */
public class CSTY extends RECO
{

	public ZString EDID = null;

	public CSTY(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("CSGD"))
			{
			}
			else if (sr.getSubrecordType().equals("CSME"))
			{
			}
			else if (sr.getSubrecordType().equals("CSCR"))
			{
			}
			else if (sr.getSubrecordType().equals("CSLR"))
			{
			}
			else if (sr.getSubrecordType().equals("CSFL"))
			{
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
			}
			else if (sr.getSubrecordType().equals("CSMD"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
