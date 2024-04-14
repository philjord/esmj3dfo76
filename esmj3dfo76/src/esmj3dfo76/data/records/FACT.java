package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Faction
 */
public class FACT extends RECO
{

	public ZString EDID = null;

	public FACT(Record recordData)
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
			else if (sr.getSubrecordType().equals("XNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
			}
			else if (sr.getSubrecordType().equals("CRVA"))
			{
			}
			else if (sr.getSubrecordType().equals("VENV"))
			{
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
			}
			else if (sr.getSubrecordType().equals("VEND"))
			{
			}
			else if (sr.getSubrecordType().equals("VENC"))
			{
			}
			else if (sr.getSubrecordType().equals("PLVD"))
			{
			}
			else if (sr.getSubrecordType().equals("WAIT"))
			{
			}
			else if (sr.getSubrecordType().equals("STOL"))
			{
			}
			else if (sr.getSubrecordType().equals("PLCN"))
			{
			}
			else if (sr.getSubrecordType().equals("CRGR"))
			{
			}
			else if (sr.getSubrecordType().equals("JOUT"))
			{
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("JAIL"))
			{
			}
			else if (sr.getSubrecordType().equals("CITC"))
			{
			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{
			}
			else if (sr.getSubrecordType().equals("CIS2"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
