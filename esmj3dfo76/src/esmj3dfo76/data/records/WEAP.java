package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
/**
 * https://falloutck.uesp.net/wiki/Weapon
 */
public class WEAP extends RECO
{
	

	public FormID FULL;

	public MODL MODL;
	
	public MODL MOD4; // female  

	public WEAP(Record recordData)
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
			
			else if (sr.getSubrecordType().equals("EITM"))
			{

			}
			else if (sr.getSubrecordType().equals("EAMT"))
			{

			}
			else if (sr.getSubrecordType().equals("ETYP"))
			{

			}
			else if (sr.getSubrecordType().equals("BIDS"))
			{

			}
			else if (sr.getSubrecordType().equals("BAMT"))
			{

			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{

			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{

			}
			else if (sr.getSubrecordType().equals("DESC"))
			{

			}
			else if (sr.getSubrecordType().equals("INAM"))
			{

			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM9"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM8"))
			{

			}
			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("CRDT"))
			{

			}
			else if (sr.getSubrecordType().equals("VNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}else if (sr.getSubrecordType().equals("OBTS"))
			{
				// new
			}else if (sr.getSubrecordType().equals("OBTE"))
			{
				// new
			}else if (sr.getSubrecordType().equals("OBTF"))
			{
				// new
			}else if (sr.getSubrecordType().equals("YNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("INRD"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("APPR"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("STOP"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("LNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("WAMD"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("WZMD"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DAMA"))
			{
				// new
			} 
			// FO4
			else if (sr.getSubrecordType().equals("MASE")) {
				
			}	
			//FO76
			else if (sr.getSubrecordType().equals("XFLG")) {
			} else if (sr.getSubrecordType().equals("ENLT")) {	
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {	
			} else if (sr.getSubrecordType().equals("MODD")) {	
			} else if (sr.getSubrecordType().equals("EILV")) {	
			} else if (sr.getSubrecordType().equals("IBSD")) {	
			} else if (sr.getSubrecordType().equals("VCRY")) {	
			} else if (sr.getSubrecordType().equals("CVT0")) {	
			} else if (sr.getSubrecordType().equals("CVT1")) {	
			} else if (sr.getSubrecordType().equals("CVT2")) {	
			} else if (sr.getSubrecordType().equals("CVT3")) {	
			} else if (sr.getSubrecordType().equals("CVT4")) {	
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

	public class DATA
	{
		public byte[] data;

		private DATA(byte[] bytes)
		{
			data = bytes;
		}
	}
}
