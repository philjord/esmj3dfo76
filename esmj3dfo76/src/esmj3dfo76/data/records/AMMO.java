package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ANAM;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Ammo
 */
public class AMMO extends RECO
{
	public ZString EDID;

	public FormID FULL;

	public MODL MODL;;

	public ANAM ANAM;

	public FormID ENAM;

	public AMMO(Record recordData)
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

			else if (sr.getSubrecordType().equals("ANAM"))
			{
				ANAM = new ANAM(bs);
			}
			else if (sr.getSubrecordType().equals("ENAM"))
			{
				ENAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{

			}
			else if (sr.getSubrecordType().equals("DESC"))
			{

			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{

			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{

			}
			else if (sr.getSubrecordType().equals("YNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ONAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("NAM1"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("NAM2"))
			{
				// new
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
