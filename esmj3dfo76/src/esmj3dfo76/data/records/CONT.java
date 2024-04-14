package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.GenericCONT;
import esmj3d.data.shared.subrecords.FormID;
/**
 * https://falloutck.uesp.net/wiki/Container
 */
public class CONT extends GenericCONT
{
	public FormID FULL2;

	public byte[] MNAM = null;

	public CONT(Record recordData)
	{

		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL2 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
				MNAM = bs;
			}
			else if (sr.getSubrecordType().equals("COCT"))
			{

			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			else if (sr.getSubrecordType().equals("COED"))
			{

			}
			else
			{
				//System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
