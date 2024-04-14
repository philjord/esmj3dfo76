package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * Regions
 * not found in https://falloutck.uesp.net/wiki/Object_Class_List
 * But in the esm file 
 */
public class REGN extends RECO
{
	public ZString EDID;

	public REGN(Record recordData)
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
			else if (sr.getSubrecordType().equals("RCLR"))
			{
				//RCLR = new RCLR(bs);
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				//WNAM = new WNAM(bs);
			}
			else if (sr.getSubrecordType().equals("RPLI"))
			{
				//RPLI = new RPLI(bs);
			}
			else if (sr.getSubrecordType().equals("RPLD"))
			{
				//RPLD = new RPLD(bs);
			}
			else if (sr.getSubrecordType().equals("RDAT"))
			{
				//RDAT = new RDAT(bs);
			}
			else if (sr.getSubrecordType().equals("RDWT"))
			{
				//RDWT = new RDWT(bs);
			}
			else if (sr.getSubrecordType().equals("RDAT"))
			{
			}
			else if (sr.getSubrecordType().equals("RDOT"))
			{

			}
			else if (sr.getSubrecordType().equals("RDMD"))
			{

			}
			else if (sr.getSubrecordType().equals("RDMP"))
			{

			}
			else if (sr.getSubrecordType().equals("RDSD"))
			{

			}
			else if (sr.getSubrecordType().equals("RDSA"))
			{

			}
			else if (sr.getSubrecordType().equals("RDMO"))
			{

			}
			else if (sr.getSubrecordType().equals("ICON"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
