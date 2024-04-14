package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Location
 */
public class LCTN extends RECO
{
	public ZString EDID;

	public LCTN(Record recordData)
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
			else if (sr.getSubrecordType().equals("LCSR")){}
			else if (sr.getSubrecordType().equals("FULL")){}
			else if (sr.getSubrecordType().equals("KSIZ")){}
			else if (sr.getSubrecordType().equals("KWDA")){}
			else if (sr.getSubrecordType().equals("PNAM")){}
			else if (sr.getSubrecordType().equals("CNAM")){}
			else if (sr.getSubrecordType().equals("MNAM")){}
			else if (sr.getSubrecordType().equals("RNAM")){}
			else if (sr.getSubrecordType().equals("LCEC")){}
			else if (sr.getSubrecordType().equals("LCID")){}
			else if (sr.getSubrecordType().equals("LCPR")){}
			else if (sr.getSubrecordType().equals("NAM1")){}
			else if (sr.getSubrecordType().equals("LCEP")){}
			else if (sr.getSubrecordType().equals("LCUN")){}
			else if (sr.getSubrecordType().equals("NAM0")){}
			else if (sr.getSubrecordType().equals("FNAM")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
