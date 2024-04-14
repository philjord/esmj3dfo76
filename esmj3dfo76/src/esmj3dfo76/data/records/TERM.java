package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Terminal
 */
public class TERM extends RECO
{

	public ZString EDID = null;

	public LString FULL = null;

	public MODL MODL = null;

	public TERM(Record recordData)
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
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new LString(bs);
			}
			else if (sr.getSubrecordType().equals("MODL"))
			{
				MODL = new MODL(bs);
			}

			else if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{

			}
			else if (sr.getSubrecordType().equals("DESC"))
			{

			}
			else if (sr.getSubrecordType().equals("SCHR"))
			{

			}
			else if (sr.getSubrecordType().equals("ITXT"))
			{

			}
			else if (sr.getSubrecordType().equals("INAM"))
			{

			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{

			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("SCDA"))
			{

			}
			else if (sr.getSubrecordType().equals("SCTX"))
			{

			}
			else if (sr.getSubrecordType().equals("SCRO"))
			{

			}
			else if (sr.getSubrecordType().equals("SCRI"))
			{

			}
			else if (sr.getSubrecordType().equals("SCRV"))
			{

			}
			else if (sr.getSubrecordType().equals("SCVR"))
			{

			}
			else if (sr.getSubrecordType().equals("SLSD"))
			{

			}
			//FALLOUTNV only ////////////////////////////
			else if (sr.getSubrecordType().equals("MODS"))
			{

			}
			else if (sr.getSubrecordType().equals("WNAM"))
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
			else if (sr.getSubrecordType().equals("FNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("COCT"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("CNTO"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("MNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("WBDT"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("XMRK"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("BSIZ"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ISIZ"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("ITID"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{
				// new
			}
			else if (sr.getSubrecordType().equals("BTXT"))
			{
				// FO4
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}else if (sr.getSubrecordType().equals("NAM0"))
			{

			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
