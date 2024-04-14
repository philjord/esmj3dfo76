package esmj3dfo76.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;

public class SCOL extends RECO
{
	public ZString EDID = null;

	public MODL MODL = null;

	// pointers to forms of STAT that this collection was made up of, only useful for the GECK, not me.
	public ArrayList<DistantSTAT> distantSTATs = new ArrayList<DistantSTAT>();

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
				EDID = new ZString(bs);
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
				distantSTATs.add(new DistantSTAT(bs));
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				distantSTATs.get(distantSTATs.size() - 1).setData(bs);
			}
			else if (sr.getSubrecordType().equals("FLTR"))
			{
				// new
			}else if (sr.getSubrecordType().equals("FULL"))
			{
				// new
			}
			else
			{
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
