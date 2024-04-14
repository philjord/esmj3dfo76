package esmj3dfo76.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonREFR;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.XMBO;
import esmj3d.data.shared.subrecords.XPRM;

public class REFR extends CommonREFR
{
	//OCCLUSION DATA
	public XPRM XPRM; // primitve data for bounds type objects, see occulsion in spacetrader

	//OCCLUSION DATA
	public XMBO XMBO; // multi bounds objects info 

	public byte[] XRMR; //XRMR 	reference marker 	struct 	ubyte - count of following XLRM, ubyte[3] - seems to be flags 0x10000 - seen in 0x000C6B47, 48 

	public ArrayList<FormID> XLMRs = null; //XLRM 	location room marker 	formid 	REFR usually to a STAT

	public REFR(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			//also in fallout3
			if (sr.getSubrecordType().equals("XPRM"))
			{
				XPRM = new XPRM(bs);
			}
			else if (sr.getSubrecordType().equals("XMBO"))
			{
				XMBO = new XMBO(bs);
			}
			else if (sr.getSubrecordType().equals("XRMR"))
			{
				XRMR = bs;
			}
			else if (sr.getSubrecordType().equals("XLRM"))
			{
				if(XLMRs == null)
					XLMRs = new ArrayList<FormID>();
				XLMRs.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("XLYR"))
			{
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{
			}
			else if (sr.getSubrecordType().equals("XLCM"))
			{
			}
			else if (sr.getSubrecordType().equals("XLKR"))
			{
			}
			else if (sr.getSubrecordType().equals("XLCN"))
			{
			}
			else if (sr.getSubrecordType().equals("XNDP"))
			{
			}
			else if (sr.getSubrecordType().equals("XPRD"))
			{
			}
			else if (sr.getSubrecordType().equals("XPPA"))
			{
			}
			else if (sr.getSubrecordType().equals("PDTO"))
			{
			}
			else if (sr.getSubrecordType().equals("INAM"))
			{
			}
			else if (sr.getSubrecordType().equals("XEMI"))
			{
			}
			else if (sr.getSubrecordType().equals("XLIB"))
			{
			}
			else if (sr.getSubrecordType().equals("XRDS"))
			{
			}
			else if (sr.getSubrecordType().equals("XLIG"))
			{
			}
			else if (sr.getSubrecordType().equals("XALP"))
			{
			}
			else if (sr.getSubrecordType().equals("XRGD"))
			{
			}
			else if (sr.getSubrecordType().equals("XPOD"))
			{
			}
			else if (sr.getSubrecordType().equals("XLRT"))
			{
			}
			else if (sr.getSubrecordType().equals("XMBR"))
			{
			}
			else if (sr.getSubrecordType().equals("XTRI"))
			{
			}
			else if (sr.getSubrecordType().equals("XAPD"))
			{
			}
			else if (sr.getSubrecordType().equals("XAPR"))
			{
			}
			else if (sr.getSubrecordType().equals("XTNM"))
			{
			}
			else if (sr.getSubrecordType().equals("XWCU"))
			{
			}
			else if (sr.getSubrecordType().equals("XWCN"))
			{
			}
			else if (sr.getSubrecordType().equals("XIS2"))
			{
			}
			else if (sr.getSubrecordType().equals("XPWR"))
			{
			}
			else if (sr.getSubrecordType().equals("XEZN"))
			{
			}
			else if (sr.getSubrecordType().equals("XOCP"))
			{
			}
			else
			{
				//CommonREFR makes this no use now
				//	System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}

	}
}
