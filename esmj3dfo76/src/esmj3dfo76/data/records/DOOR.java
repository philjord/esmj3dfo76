package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.GenericDOOR;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import tools.io.ESMByteConvert;
/**
 * https://falloutck.uesp.net/wiki/Door
 */
public class DOOR extends GenericDOOR
{
	public FormID FULL2;

	public int TNAM = -1; //random teleport destination

	public DOOR(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{

			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("FULL"))
			{		
				FULL = new LString(bs);
				FULL2 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
				TNAM = ESMByteConvert.extractInt(bs, 0);
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			else
			{
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
