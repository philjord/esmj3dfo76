package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * Dialog View
 * not found in https://falloutck.uesp.net/wiki/Object_Class_List
 * But in the esm file
 */
public class DLVW extends RECO
{
	public ZString EDID;

	public DLVW(Record recordData)
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
			else if (sr.getSubrecordType().equals("QNAM")){}
			else if (sr.getSubrecordType().equals("BNAM")){}
			else if (sr.getSubrecordType().equals("ENAM")){}
			else if (sr.getSubrecordType().equals("DNAM")){}
			else if (sr.getSubrecordType().equals("TNAM")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
