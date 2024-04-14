package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.MODS;
import esmj3d.data.shared.subrecords.MODT;
import esmj3d.data.shared.subrecords.OBND;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Static
 */
public class STAT extends RECO
{
	public ZString EDID;

	public OBND OBND;

	public MODL MODL;

	public MODT MODT;

	public MODS MODS;

	public String lodModel1 = null;// high detail

	public String lodModel2 = null;

	public String lodModel3 = null;

	public String lodModel4 = null;// low detail

	public STAT(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND"))
			{
				OBND = new OBND(bs);
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("MODS"))
			{
				MODL.addMODSSub(bs);
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
				if (bs.length >= 1040)
				{
					// Has Distance LOD = RECO.VisibleWhenDistant_Flag from flags1
					lodModel1 = new String(bs, 0, 260).split("\0", 2)[0];
					lodModel2 = new String(bs, 260, 260).split("\0", 2)[0];
					lodModel3 = new String(bs, 520, 260).split("\0", 2)[0];
					lodModel4 = new String(bs, 780, 260).split("\0", 2)[0];
				}
				else
				{
					System.out.println("short MNAM in STAT ? " + new String(bs));
				}
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PRPS"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("FTYP"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("MODC"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("NVNM"))
			{
				// new
			}

			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	@Override
	public String showDetails()
	{
		return "STAT : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str + " : " + MODL.model.str;
	}

}
