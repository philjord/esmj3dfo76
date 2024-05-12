package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/HeadPart
 */
public class HDPT extends RECO
{
	

	public FormID FULL;

	public MODL MODL;

	public HDPT(Record recordData)
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
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}

			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("HNAM"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
