package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.InstRECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.XESP;

import esmj3dfo76.data.subrecords.XRGD;
import tools.io.ESMByteConvert;
/**
 * just like a REFR but for NPC_ https://falloutck.uesp.net/wiki/Object_Class_List possibly delete
 * 
 */
public class ACHR extends InstRECO
{
	

	public FormID NAME = null;

	public FormID XESP = null;

	public FormID XHRS = null;

	public FormID XMRC = null;

	public XRGD XRGD = null;

	public ACHR(Record recordData)
	{
		super(recordData);
		
		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID")) {
				setEDID(bs);
			} else if (sr.getSubrecordType().equals("NAME")) {
				NAME = new FormID(bs);
			} else if (sr.getSubrecordType().equals("XESP")) {
				xesp = new XESP(bs);
			} else if (sr.getSubrecordType().equals("XHRS")) {
				XHRS = new FormID(bs);
			} else if (sr.getSubrecordType().equals("XMRC")) {
				XMRC = new FormID(bs);
			} else if (sr.getSubrecordType().equals("XRGD")) {
				XRGD = new XRGD(bs);
			} else if (sr.getSubrecordType().equals("XSCL")) {
				scale = ESMByteConvert.extractFloat(bs, 0);
			} else if (sr.getSubrecordType().equals("DATA")) {
				this.extractInstData(bs);
			} else if (sr.getSubrecordType().equals("XLCM")) {
			} else if (sr.getSubrecordType().equals("XLKR")) {

			} else if (sr.getSubrecordType().equals("XLCN")) {

			} else if (sr.getSubrecordType().equals("XLRT")) {

			} else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("XAPD")) {
			} else if (sr.getSubrecordType().equals("XAPR")) {
			} else if (sr.getSubrecordType().equals("XRGB")) {
			} else if (sr.getSubrecordType().equals("XEZN")) {
			} else if (sr.getSubrecordType().equals("XOWN")) {
			} else if (sr.getSubrecordType().equals("XHOR")) {
			} 
			//FO4
			else if (sr.getSubrecordType().equals("XLYR")) {
			} else if (sr.getSubrecordType().equals("XIS2")) {
			} else if (sr.getSubrecordType().equals("XLKT")) {
			}	
			//FO76
			else if (sr.getSubrecordType().equals("KWDA")) {	
			} else if (sr.getSubrecordType().equals("XMSP")) {	
			} else if (sr.getSubrecordType().equals("XALG")) {	
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

}
