package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * IMAD 	ImageSpace Modifier
 */
public class IMAD extends RECO
{
	

	public IMAD(Record recordData)
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
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("BNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("VNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM3"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{

			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("YNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM4"))
			{

			}
			else if (sr.getSubrecordType().endsWith("IAD"))
			{
				//many records here
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
