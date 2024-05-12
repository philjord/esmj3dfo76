package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/Armor
 */
public class ARMO extends RECO
{
	

	public FormID FULL;

	public FormID MODL;

	public MODL MOD2; // male  

	public MODL MOD4; // female  

	public ARMO(Record recordData)
	{

		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID")) {
				setEDID(bs);
			} else if (sr.getSubrecordType().equals("OBND")) {
			} else if (sr.getSubrecordType().equals("FULL")) {
				FULL = new FormID(bs);
			} else if (sr.getSubrecordType().equals("MODL")) {
				MODL = new FormID(bs);
			} else if (sr.getSubrecordType().equals("MOD2")) {
				MOD2 = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MO2T")) {
				MOD2.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("MO2S")) {
				MOD2.addMODSSub(bs);
			} else if (sr.getSubrecordType().equals("MOD4")) {
				MOD4 = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MO4T")) {
				MOD4.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("MO4S")) {
				MOD4.addMODSSub(bs);
			} else if (sr.getSubrecordType().equals("BODT")) {
			} else if (sr.getSubrecordType().equals("YNAM")) {
			} else if (sr.getSubrecordType().equals("ZNAM")) {
			} else if (sr.getSubrecordType().equals("RNAM")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("DESC")) {
			} else if (sr.getSubrecordType().equals("DATA")) {
			} else if (sr.getSubrecordType().equals("DNAM")) {
			} else if (sr.getSubrecordType().equals("EITM")) {
			} else if (sr.getSubrecordType().equals("ETYP")) {
			} else if (sr.getSubrecordType().equals("BIDS")) {
			} else if (sr.getSubrecordType().equals("BAMT")) {
			} else if (sr.getSubrecordType().equals("TNAM")) {
			} else if (sr.getSubrecordType().equals("VMAD")) {
			}
			//FO4
			else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("OBTS")) {
			} else if (sr.getSubrecordType().equals("OBTE")) {
			} else if (sr.getSubrecordType().equals("INDX")) {
			} else if (sr.getSubrecordType().equals("APPR")) {
			} else if (sr.getSubrecordType().equals("STOP")) {
			} else if (sr.getSubrecordType().equals("BOD2")) {
			} else if (sr.getSubrecordType().equals("FNAM")) {
			} else if (sr.getSubrecordType().equals("INRD")) {
			} else if (sr.getSubrecordType().equals("OBTF")) {
			} else if (sr.getSubrecordType().equals("DAMA")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("VCRY")) {		
			} 
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

}
