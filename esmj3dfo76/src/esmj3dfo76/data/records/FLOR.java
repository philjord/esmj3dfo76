package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.subrecords.PFPC;
/**
 * https://falloutck.uesp.net/wiki/Flora
 */
public class FLOR extends RECO
{
	public ZString EDID;

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
			else if (sr.getSubrecordType().equals("PFIG"))
			{
				produceFormId = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				soundId = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("PFPC"))
			{
				PFPC = new PFPC(bs);
			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			else if (sr.getSubrecordType().equals("MODS"))
			{

			}
			else if (sr.getSubrecordType().equals("PTRN"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DEST"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DSTD"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DMDL"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DMDT"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("DSTF"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("PRPS"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ATTX"))
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
