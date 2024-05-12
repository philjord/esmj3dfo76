package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * https://falloutck.uesp.net/wiki/Sound_Descriptor
 */
public class SNDR extends RECO
{
	

	public SNDR(Record recordData)
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
			else if (sr.getSubrecordType().equals("CNAM")){}
			else if (sr.getSubrecordType().equals("GNAM")){}
			else if (sr.getSubrecordType().equals("ANAM")){}
			else if (sr.getSubrecordType().equals("ONAM")){}
			else if (sr.getSubrecordType().equals("LNAM")){}
			else if (sr.getSubrecordType().equals("BNAM")){}
			else if (sr.getSubrecordType().equals("SNAM")){}
			else if (sr.getSubrecordType().equals("CTDA")){}
			else if (sr.getSubrecordType().equals("FNAM")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
