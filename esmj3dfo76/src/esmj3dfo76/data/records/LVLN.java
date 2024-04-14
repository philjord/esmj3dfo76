package esmj3dfo76.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.subrecords.LVLD;
import esmj3dfo76.data.subrecords.LVLF;
import esmj3dfo76.data.subrecords.LVLO;
/**
 * https://falloutck.uesp.net/wiki/LeveledCharacter
 * 
 * A Leveled Character is a LeveledItem List that will generate an NPC based on the current level of the player. 
 * They act as templates for another NPC and should not be placed in the world. 
 * The template NPC can be placed in the world and will use the data from the list to define itself. 
 * You will see a generic marker in the Editor when placing the reference since the Creation Kit has no way of knowing what model to place. 
 * This reference can have data on it as well. 
 */
public class LVLN extends RECO
{
	public ZString EDID = null;

	public LVLD LVLD = null;

	public LVLF LVLF = null;

	public LVLO[] LVLOs = null;

	public LVLN(Record recordData)
	{
		super(recordData);

		ArrayList<LVLO> LVLOsl = new ArrayList<LVLO>();

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID"))
			{
				EDID = new ZString(bs);
			}
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("LVLD"))
			{
				LVLD = new LVLD(bs);
			}
			else if (sr.getSubrecordType().equals("LVLF"))
			{
				LVLF = new LVLF(bs);
			}
			else if (sr.getSubrecordType().equals("LLCT"))
			{

			}
			else if (sr.getSubrecordType().equals("LVLO"))
			{
				LVLOsl.add(new LVLO(bs));
			}
			else if (sr.getSubrecordType().equals("COED"))
			{

			}
			else if (sr.getSubrecordType().equals("MODL"))
			{

			}else if (sr.getSubrecordType().equals("MODT"))
			{

			}else if (sr.getSubrecordType().equals("LVLM"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

			// transfer to arrays
			LVLOs = new LVLO[LVLOsl.size()];
			LVLOsl.toArray(LVLOs);
		}
	}
}
