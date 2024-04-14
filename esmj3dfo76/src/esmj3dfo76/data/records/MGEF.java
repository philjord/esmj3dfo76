package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Magic_Effect
 * 
 * also sub data of
 * https://falloutck.uesp.net/wiki/Effect_Item
 */
public class MGEF extends RECO
{

	public ZString EDID = null;

	public MGEF(Record recordData)
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
			else if (sr.getSubrecordType().equals("FULL"))
			{
			}
			else if (sr.getSubrecordType().equals("MDOB"))
			{
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
			}
			else if (sr.getSubrecordType().equals("SNDD"))
			{
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{
			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{
			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
