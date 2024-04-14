package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Projectile
 */
public class PROJ extends RECO
{
	public ZString EDID;

	public PROJ(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND")){}
			else if (sr.getSubrecordType().equals("MODL")){}
			else if (sr.getSubrecordType().equals("MODT")){}
			else if (sr.getSubrecordType().equals("DATA")){}
			else if (sr.getSubrecordType().equals("NAM1")){}
			else if (sr.getSubrecordType().equals("VNAM")){}
			else if (sr.getSubrecordType().equals("FULL")){}
			else if (sr.getSubrecordType().equals("DEST")){}
			else if (sr.getSubrecordType().equals("DSTD")){}
			else if (sr.getSubrecordType().equals("DSTF")){}
			else if (sr.getSubrecordType().equals("NAM2")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
