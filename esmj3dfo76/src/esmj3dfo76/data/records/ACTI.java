package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Activator
 */
public class ACTI extends RECO
{
	public ZString EDID;

	public MODL MODL;

	public ACTI(Record recordData)
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

			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
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

			else if (sr.getSubrecordType().equals("PNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{
			}
			else if (sr.getSubrecordType().equals("KNAM"))
			{
			}

			else if (sr.getSubrecordType().equals("DEST"))
			{
			}
			else if (sr.getSubrecordType().equals("DSTD"))
			{
			}
			else if (sr.getSubrecordType().equals("DMDL"))
			{
			}
			else if (sr.getSubrecordType().equals("DMDT"))
			{
			}
			else if (sr.getSubrecordType().equals("DMDS"))
			{
			}
			else if (sr.getSubrecordType().equals("DSTF"))
			{
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("VNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{
			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("PRPS"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("RADR"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("CITC"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("STCP"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("ATTX"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("FTYP"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("NTRM"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("DAMC"))
			{
				//new
			}
			else if (sr.getSubrecordType().equals("DSTA"))
			{
				//new
			}else if (sr.getSubrecordType().equals("CIS1"))
			{
				//new
			}else if (sr.getSubrecordType().equals("CIS2"))
			{
				//new
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

}
