package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * https://falloutck.uesp.net/wiki/Association_Type
 */
public class ASTP extends RECO
{
	

	public ASTP(Record recordData)
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
			else if (sr.getSubrecordType().equals("MPRT")){}
			else if (sr.getSubrecordType().equals("FPRT")){}
			else if (sr.getSubrecordType().equals("MCHT")){}
			else if (sr.getSubrecordType().equals("FCHT")){}
			else if (sr.getSubrecordType().equals("DATA")){}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
