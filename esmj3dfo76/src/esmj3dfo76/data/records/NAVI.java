package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
/**
 * Navigation
 * not found in https://falloutck.uesp.net/wiki/Object_Class_List
 * But in the esm file
 */
public class NAVI extends RECO
{

	public NAVI(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			//byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("NVER"))
			{

			}
			else if (sr.getSubrecordType().equals("NVMI"))
			{

			}
			else if (sr.getSubrecordType().equals("NVPP"))
			{

			}

			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
