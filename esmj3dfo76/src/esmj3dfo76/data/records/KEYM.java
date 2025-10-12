package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/Key
 */
public class KEYM extends RECO
{
	

	public FormID FULL;

	public MODL MODL;;

	public KEYM(Record recordData)
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
			else if (sr.getSubrecordType().equals("YNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{

			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
				//keywords
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			// FO4
			else if (sr.getSubrecordType().equals("PTRN")) {				
			}
			//FO76
			else if (sr.getSubrecordType().equals("XALG")) {
			} else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {
			} else if (sr.getSubrecordType().equals("AUUV")) {
			} else if (sr.getSubrecordType().equals("VCRY")) {
			} else if (sr.getSubrecordType().equals("AQIC")) {
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

	public class DATA
	{
		public byte[] data;

		private DATA(byte[] bytes)
		{
			data = bytes;
		}
	}
}
