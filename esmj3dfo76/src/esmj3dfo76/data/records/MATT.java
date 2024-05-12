package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * MATT 	Material Type
 */
public class MATT extends RECO
{
	

	public MATT(Record recordData)
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
			else if (sr.getSubrecordType().equals("MNAM")){}
			else if (sr.getSubrecordType().equals("CNAM")){}
			else if (sr.getSubrecordType().equals("BNAM")){}
			else if (sr.getSubrecordType().equals("FNAM")){}
			else if (sr.getSubrecordType().equals("HNAM")){}
			else if (sr.getSubrecordType().equals("PNAM")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
