package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * https://falloutck.uesp.net/wiki/Message
 */
public class MESG extends RECO
{

	

	public MESG(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID"))
			{
				setEDID(bs);
			}
			else if (sr.getSubrecordType().equals("DESC"))
			{

			}
			else if (sr.getSubrecordType().equals("FULL"))
			{

			}
			else if (sr.getSubrecordType().equals("INAM"))
			{

			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ITXT"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM3"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM4"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM5"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM6"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM7"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM8"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM9"))
			{

			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
