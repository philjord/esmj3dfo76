package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;
/**
 * https://falloutck.uesp.net/wiki/Activator
 */
public class ACTI extends RECO
{
	

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
				setEDID(bs);
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
			//FO4
			else if (sr.getSubrecordType().equals("PRPS")) {
			} else if (sr.getSubrecordType().equals("RADR")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("CTDA")) {
			} else if (sr.getSubrecordType().equals("CITC")) {
			} else if (sr.getSubrecordType().equals("STCP")) {
			} else if (sr.getSubrecordType().equals("ATTX")) {
			} else if (sr.getSubrecordType().equals("FTYP")) {
			} else if (sr.getSubrecordType().equals("NTRM")) {
			} else if (sr.getSubrecordType().equals("DAMC")) {
			} else if (sr.getSubrecordType().equals("DSTA")) {
			} else if (sr.getSubrecordType().equals("CIS1")) {
			} else if (sr.getSubrecordType().equals("CIS2")) {
			}
			
			//FO76
			else if (sr.getSubrecordType().equals("ENLM")) {
			} else if (sr.getSubrecordType().equals("ENLT")) {	
			} else if (sr.getSubrecordType().equals("ENLS")) {		
			} else if (sr.getSubrecordType().equals("AUUV")) {	
			} else if (sr.getSubrecordType().equals("PAHD")) {		
			} else if (sr.getSubrecordType().equals("CTRN")) {		
			} else if (sr.getSubrecordType().equals("NVNM")) {		
			} else if (sr.getSubrecordType().equals("NAM1")) {		
			} else if (sr.getSubrecordType().equals("LODP")) {		
			} else if (sr.getSubrecordType().equals("CNDC")) {		
			} else if (sr.getSubrecordType().equals("XALG")) {		
			} else if (sr.getSubrecordType().equals("QSTI")) {		
			} else if (sr.getSubrecordType().equals("SNTP")) {		
			} else if (sr.getSubrecordType().equals("HGLB")) {		
			} else if (sr.getSubrecordType().equals("MNAM")) {		
			} else if (sr.getSubrecordType().equals("DEFL")) {		
			} else if (sr.getSubrecordType().equals("XFLG")) {		
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

}
