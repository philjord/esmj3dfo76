package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.subrecords.BODT;

/**
 * https://falloutck.uesp.net/wiki/ArmorAddon
 */
public class ARMA extends RECO
{
	public ZString EDID;

	public FormID RNAM;

	public BODT BODT;

	public MODL MOD2;

	public MODL MOD3;

	public MODL MOD4;

	public MODL MOD5;

	public FormID MODL;

	public FormID NAM0;

	public FormID NAM1;

	public FormID NAM2;

	public FormID NAM3;

	public FormID SNDD;

	public FormID ONAM;

	public ARMA(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("BODT"))
			{
				BODT = new BODT(bs);
			}
			else if (sr.getSubrecordType().equals("BOD2"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
				RNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}

			else if (sr.getSubrecordType().equals("MOD2"))
			{
				MOD2 = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MO2T"))
			{
				MOD2.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("MO2S"))
			{
				MOD2.addMODSSub(bs);
			}
			else if (sr.getSubrecordType().equals("MOD3"))
			{
				MOD3 = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MO3T"))
			{
				MOD3.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("MO3S"))
			{
				MOD3.addMODSSub(bs);
			}
			else if (sr.getSubrecordType().equals("MOD4"))
			{
				MOD4 = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MO4T"))
			{
				MOD4.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("MO4S"))
			{
				MOD4.addMODSSub(bs);
			}
			else if (sr.getSubrecordType().equals("MOD5"))
			{
				MOD5 = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MO5T"))
			{
				MOD5.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("MO5S"))
			{
				MOD5.addMODSSub(bs);
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
				NAM0 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{
				NAM1 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{
				NAM2 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("NAM3"))
			{
				NAM3 = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("SNDD"))
			{
				SNDD = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{
				ONAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("MO2F"))
			{
				
			}
			else if (sr.getSubrecordType().equals("MO3F"))
			{

			}
			else if (sr.getSubrecordType().equals("BSMP"))
			{

			}			
			else if (sr.getSubrecordType().equals("BSMB"))
			{

			}
			else if (sr.getSubrecordType().equals("BSMS"))
			{

			}		
			
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
