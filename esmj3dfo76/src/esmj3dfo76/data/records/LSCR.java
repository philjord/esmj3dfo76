package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/LoadScreen
 */
public class LSCR extends RECO
{
	public ZString EDID = null;

	public LSCR(Record recordData)
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
			else if (sr.getSubrecordType().equals("DESC"))
			{

			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{

			}
			else if (sr.getSubrecordType().equals("NNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{

			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("MOD2"))
			{

			}
			else if (sr.getSubrecordType().equals("CIS2"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
