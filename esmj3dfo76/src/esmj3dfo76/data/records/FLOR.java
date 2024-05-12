package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

import esmj3dfo76.data.subrecords.PFPC;
/**
 * https://falloutck.uesp.net/wiki/Flora
 */
public class FLOR extends RECO
{
	

	public FormID FULL;

	public MODL MODL;;

	public FormID produceFormId = null;// Formid of INGR/ALCH

	public FormID soundId = null;// Formid of SNDR when activated

	public PFPC PFPC;

	public FLOR(Record recordData)
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
			} else if (sr.getSubrecordType().equals("PFIG")) {
				produceFormId = new FormID(bs);
			} else if (sr.getSubrecordType().equals("SNAM")) {
				soundId = new FormID(bs);
			} else if (sr.getSubrecordType().equals("PFPC")) {
				PFPC = new PFPC(bs);
			} else if (sr.getSubrecordType().equals("PNAM")) {
			} else if (sr.getSubrecordType().equals("RNAM")) {
			} else if (sr.getSubrecordType().equals("FNAM")) {
			} else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("MODS")) {
			}
			//FO4
			else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("DEST")) {
			} else if (sr.getSubrecordType().equals("DSTD")) {
			} else if (sr.getSubrecordType().equals("DMDL")) {
			} else if (sr.getSubrecordType().equals("DMDT")) {
			} else if (sr.getSubrecordType().equals("DSTF")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("PRPS")) {
			} else if (sr.getSubrecordType().equals("ATTX")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("DEFL")) {	
			} else if (sr.getSubrecordType().equals("PAHD")) {		
			} else if (sr.getSubrecordType().equals("CTRN")) {		
			} else if (sr.getSubrecordType().equals("CITC")) {		
			} else if (sr.getSubrecordType().equals("FLFG")) {		
			} else if (sr.getSubrecordType().equals("FMAH")) {		
			} else if (sr.getSubrecordType().equals("FMIH")) {		
			} else if (sr.getSubrecordType().equals("FMAG")) {		
			} else if (sr.getSubrecordType().equals("FMIG")) {		
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
