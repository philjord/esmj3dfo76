package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Weather
 */
public class WTHR extends RECO
{
	public ZString EDID = null;

	public WTHR(Record recordData)
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
			else if (sr.getSubrecordType().endsWith("0TX"))
			{
				//Clouds dds strings from 00TX to L0TX by acsii number, odd 
			}
			else if (sr.getSubrecordType().equals("LNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("NNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("JNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("IMSP"))
			{
			}
			else if (sr.getSubrecordType().equals("DALC"))
			{
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM3"))
			{
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{
			}
			else if (sr.getSubrecordType().equals("BNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{
			}

			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
