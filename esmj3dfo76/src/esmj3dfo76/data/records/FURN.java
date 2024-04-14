package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Furniture
 */
public class FURN extends RECO
{
	public ZString EDID;

	public FormID FULL;

	public MODL MODL;;

	public byte[] MNAM = null;

	public FURN(Record recordData)
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
				FULL = new FormID(bs);
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
			else if (sr.getSubrecordType().equals("KNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("WBDT"))
			{
			}
			else if (sr.getSubrecordType().equals("ENAM"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
			}
			else if (sr.getSubrecordType().equals("FNPR"))
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
			else if (sr.getSubrecordType().equals("XMRK"))
			{
			}

			else if (sr.getSubrecordType().equals("FNMK"))
			{
			}
			else if (sr.getSubrecordType().equals("DEST"))
			{
			}
			else if (sr.getSubrecordType().equals("DSTD"))
			{
			}
			else if (sr.getSubrecordType().equals("DSTF"))
			{
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PRPS"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ATTX"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("OBTE"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("OBTS"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("STOP"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("COCT"))
			{
				// new
			}else if (sr.getSubrecordType().equals("DMDL"))
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
		return "FURN : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str + " : " + MODL.model.str;
	}

}
