package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * Story Manager Quest Node
 * not found in https://falloutck.uesp.net/wiki/Object_Class_List
 * But in the esm file
 */
public class SMQN extends RECO
{
	public ZString EDID;

	public SMQN(Record recordData)
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
			else if (sr.getSubrecordType().equals("PNAM")){}
			else if (sr.getSubrecordType().equals("SNAM")){}
			else if (sr.getSubrecordType().equals("CITC")){}
			else if (sr.getSubrecordType().equals("CTDA")){}
			else if (sr.getSubrecordType().equals("DNAM")){}
			else if (sr.getSubrecordType().equals("XNAM")){}
			else if (sr.getSubrecordType().equals("QNAM")){}
			else if (sr.getSubrecordType().equals("NNAM")){}
			else if (sr.getSubrecordType().equals("CIS2")){}
			else if (sr.getSubrecordType().equals("RNAM")){}
			else if (sr.getSubrecordType().equals("MNAM")){}
			else if (sr.getSubrecordType().equals("FNAM")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
