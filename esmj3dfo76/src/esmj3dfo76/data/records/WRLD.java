package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonWRLD;
/**
 * https://falloutck.uesp.net/wiki/WorldSpace
 */
public class WRLD extends CommonWRLD
{

	public WRLD(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			//byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("NAM0"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("NAM9"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("OFST"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("NAM3"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("NAM4"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("NAMA"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
				//
			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{
				//
			}
			else
			{
				//System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	@Override
	public String showDetails()
	{
		return "WRLD : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str;
	}

}
