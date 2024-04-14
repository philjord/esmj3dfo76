package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;

/**
 * https://falloutck.uesp.net/wiki/Acoustic_Space
 * 
 * names like ExtInteriorFakeInteriorBrickMedium, IntGenericB, ExtInteriorFakeInteriorMetalSmall
 */
public class ASPC extends RECO
{
	public ZString EDID = null;

	public ASPC(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{

			}else if (sr.getSubrecordType().equals("BNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("RDAT"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
