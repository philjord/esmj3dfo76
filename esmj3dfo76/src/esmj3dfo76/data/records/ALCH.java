package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/Potion
 */
public class ALCH extends RECO
{
	

	public FormID FULL;

	public MODL MODL;

	public ALCH(Record recordData)
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
				MODL = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MODT")) {
				MODL.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("MODS")) {
				MODL.addMODSSub(bs);
			} else if (sr.getSubrecordType().equals("DATA")) {
			} else if (sr.getSubrecordType().equals("ENIT")) {
			} else if (sr.getSubrecordType().equals("EFID")) {
			} else if (sr.getSubrecordType().equals("EFIT")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
				// keywords
			} else if (sr.getSubrecordType().equals("YNAM")) {
			} else if (sr.getSubrecordType().equals("ZNAM")) {
			} else if (sr.getSubrecordType().equals("CTDA")) {
			}
			//FO4
			else if (sr.getSubrecordType().equals("DESC")) {
			} else if (sr.getSubrecordType().equals("DNAM")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("CUSD")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("SNTP")) {	
			} else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("CODV")) {
			} else if (sr.getSubrecordType().equals("MAGF")) {	
			} else if (sr.getSubrecordType().equals("DURG")) {		
			} else if (sr.getSubrecordType().equals("MAGG")) {	
			} else if (sr.getSubrecordType().equals("CODV")) {		
			} else if (sr.getSubrecordType().equals("MIID")) {		
			} 
			else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
