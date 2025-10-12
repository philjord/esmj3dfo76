package esmj3dfo76.data.records;

 
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;


public class SCOL extends RECO
{
	

	public MODL MODL = null;

	// pointers to forms of STAT that this collection was made up of, only useful for the GECK, not me.
	//public ArrayList<DistantSTAT> distantSTATs = new ArrayList<DistantSTAT>();

	public SCOL(Record recordData)
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
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}
			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}

			else if (sr.getSubrecordType().equals("ONAM"))
			{
				//FO76 seems not correct definately not a formid anymore
				//distantSTATs.add(new DistantSTAT(bs));
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				//distantSTATs.get(distantSTATs.size() - 1).setData(bs);
			}
			//FO4
			else if (sr.getSubrecordType().equals("FLTR")) {
			} else if (sr.getSubrecordType().equals("FULL")) {
			}
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {	
			} else if (sr.getSubrecordType().equals("AUUV")) {		
			} else if (sr.getSubrecordType().equals("ENLM")) {	
			} else if (sr.getSubrecordType().equals("NAM1")) {		
			} else if (sr.getSubrecordType().equals("LODP")) {		
			} else if (sr.getSubrecordType().equals("XFLG")) {		
			} else if (sr.getSubrecordType().equals("XALG")) {		
			} else if (sr.getSubrecordType().equals("PRPS")) {		
			} else if (sr.getSubrecordType().equals("MNAM")) {		
			} else if (sr.getSubrecordType().equals("OPDS")) {		
			} else if (sr.getSubrecordType().equals("DEFL")) {		
			} else if (sr.getSubrecordType().equals("MODS")) {		
			} else if (sr.getSubrecordType().equals("SNTP")) {		
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

	public class DistantSTAT
	{
		public FormID statFormId = null;

		// position data for each original piece
		public byte[] data = null;

		public DistantSTAT(byte[] bs)
		{
			statFormId = new FormID(bs);
		}

		public void setData(byte[] bs)
		{
			data = bs;
		}
	}
}
