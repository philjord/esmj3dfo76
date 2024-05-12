package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/Terminal
 */
public class TERM extends RECO
{

	

	public LString FULL = null;

	public MODL MODL = null;

	public TERM(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID")) {
				setEDID(bs);
			} else if (sr.getSubrecordType().equals("FULL")) {
				FULL = new LString(bs);
			} else if (sr.getSubrecordType().equals("MODL")) {
				MODL = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MODT")) {
				MODL.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("SNAM")) {
			} else if (sr.getSubrecordType().equals("RNAM")) {
			} else if (sr.getSubrecordType().equals("OBND")) {
			} else if (sr.getSubrecordType().equals("DNAM")) {
			} else if (sr.getSubrecordType().equals("PNAM")) {
			} else if (sr.getSubrecordType().equals("ANAM")) {
			} else if (sr.getSubrecordType().equals("DESC")) {
			} else if (sr.getSubrecordType().equals("SCHR")) {
			} else if (sr.getSubrecordType().equals("ITXT")) {
			} else if (sr.getSubrecordType().equals("INAM")) {
			} else if (sr.getSubrecordType().equals("CTDA")) {
			} else if (sr.getSubrecordType().equals("TNAM")) {
			} else if (sr.getSubrecordType().equals("SCDA")) {
			} else if (sr.getSubrecordType().equals("SCTX")) {
			} else if (sr.getSubrecordType().equals("SCRO")) {
			} else if (sr.getSubrecordType().equals("SCRI")) {
			} else if (sr.getSubrecordType().equals("SCRV")) {
			} else if (sr.getSubrecordType().equals("SCVR")) {
			} else if (sr.getSubrecordType().equals("SLSD")) {
			}
			//FALLOUTNV only ////////////////////////////
			else if (sr.getSubrecordType().equals("MODS")) {

			} else if (sr.getSubrecordType().equals("WNAM")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("FNAM")) {
			} else if (sr.getSubrecordType().equals("COCT")) {
			} else if (sr.getSubrecordType().equals("CNTO")) {
			} else if (sr.getSubrecordType().equals("MNAM")) {
			} else if (sr.getSubrecordType().equals("WNAM")) {
			} else if (sr.getSubrecordType().equals("WBDT")) {
			} else if (sr.getSubrecordType().equals("XMRK")) {
			} else if (sr.getSubrecordType().equals("BSIZ")) {
			} else if (sr.getSubrecordType().equals("ISIZ")) {
			} else if (sr.getSubrecordType().equals("ITID")) {
			} else if (sr.getSubrecordType().equals("UNAM")) {
			} else if (sr.getSubrecordType().equals("BTXT")) {			
			} 
			// FO4
			else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("NAM0")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("PAHD")) {		
			} else if (sr.getSubrecordType().equals("CTRN")) {		
			} else if (sr.getSubrecordType().equals("ZNAM")) {		
			} else if (sr.getSubrecordType().equals("FFEF")) {		
			} else if (sr.getSubrecordType().equals("TDAT")) {		
			} else if (sr.getSubrecordType().equals("QNAM")) {		
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
