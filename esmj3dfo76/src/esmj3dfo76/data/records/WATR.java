package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/WaterType
 */
public class WATR extends RECO
{

	public ZString EDID = null;

	public WATR(Record recordData)
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
			else if (sr.getSubrecordType().equals("NNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{

			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("GNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("FULL"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
