package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/MovableStatic
 */
public class MSTT extends RECO
{
	public ZString EDID;

	public MODL MODL;;

	public MSTT(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND")) {
			} else if (sr.getSubrecordType().equals("DATA")) {
			} else if (sr.getSubrecordType().equals("SNAM")) {
			} else if (sr.getSubrecordType().equals("DSTD")) {
			} else if (sr.getSubrecordType().equals("DSTF")) {
			} else if (sr.getSubrecordType().equals("DMDL")) {
			} else if (sr.getSubrecordType().equals("DMDT")) {
			} else if (sr.getSubrecordType().equals("DEST")) {
			} else if (sr.getSubrecordType().equals("FULL")) {
			} else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("DSTA")) {
			} else if (sr.getSubrecordType().equals("DMDS")) {
			} else if (sr.getSubrecordType().equals("MODC")) {
			} else if (sr.getSubrecordType().equals("PRPS")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("PHST")) {	
			} else if (sr.getSubrecordType().equals("XALG")) {		
			} else if (sr.getSubrecordType().equals("DEFL")) {		
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
