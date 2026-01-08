package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
/**
 * https://falloutck.uesp.net/wiki/Furniture
 */
public class FURN extends RECO
{
	

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
				setEDID(bs);
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

			else if (sr.getSubrecordType().equals("PNAM")) {
			} else if (sr.getSubrecordType().equals("FNAM")) {
			} else if (sr.getSubrecordType().equals("KNAM")) {
			} else if (sr.getSubrecordType().equals("MNAM")) {
			} else if (sr.getSubrecordType().equals("WBDT")) {
			} else if (sr.getSubrecordType().equals("ENAM")) {
			} else if (sr.getSubrecordType().equals("NAM0")) {
			} else if (sr.getSubrecordType().equals("FNPR")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("XMRK")) {
			} else if (sr.getSubrecordType().equals("FNMK")) {
			} else if (sr.getSubrecordType().equals("DEST")) {
			} else if (sr.getSubrecordType().equals("DSTD")) {
			} else if (sr.getSubrecordType().equals("DSTF")) {
			}
			//FO4
			else if (sr.getSubrecordType().equals("SNAM")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("PRPS")) {
			} else if (sr.getSubrecordType().equals("ATTX")) {
			} else if (sr.getSubrecordType().equals("OBTE")) {
			} else if (sr.getSubrecordType().equals("OBTS")) {
			} else if (sr.getSubrecordType().equals("STOP")) {
			} else if (sr.getSubrecordType().equals("COCT")) {
			} else if (sr.getSubrecordType().equals("DMDL")) {
			}
			
			//FO76
			else if (sr.getSubrecordType().equals("ENLM")) {	
			} else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("PAHD")) {		
			} else if (sr.getSubrecordType().equals("CTRN")) {		
			} else if (sr.getSubrecordType().equals("ZNAM")) {		
			} else if (sr.getSubrecordType().equals("FFEF")) {		
			} else if (sr.getSubrecordType().equals("NVNM")) {		
			} else if (sr.getSubrecordType().equals("FTYP")) {		
			} else if (sr.getSubrecordType().equals("HGLB")) {		
			} else if (sr.getSubrecordType().equals("DEFL")) {		
			} else if (sr.getSubrecordType().equals("SNTP")) {		
			} else if (sr.getSubrecordType().equals("XFLG")) {		
			} else if (sr.getSubrecordType().equals("XALG")) {		
			} else if (sr.getSubrecordType().equals("RADR")) {		
			} else if (sr.getSubrecordType().equals("NAM1")) {		
			} else if (sr.getSubrecordType().equals("LODP")) {		
			} else if (sr.getSubrecordType().equals("FNMU")) {		
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	@Override
	public String showDetails()
	{
		return super.showDetails() + " : " + MODL.model;
	}

}
