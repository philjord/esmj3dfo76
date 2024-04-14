package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Actor_Value
 */
public class AVIF extends RECO
{

	public ZString EDID = null;

	public AVIF(Record recordData)
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
			else if (sr.getSubrecordType().equals("CNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
			}
			else if (sr.getSubrecordType().equals("AVSK"))
			{
			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("YNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("HNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("VNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("INAM"))
			{
			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
