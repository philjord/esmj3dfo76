package esmj3dfo76.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.CNTO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.OBND;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.shared.subrecords.KeyWords;
import esmj3dfo76.data.subrecords.ACBS;
import esmj3dfo76.data.subrecords.AIDT;
import esmj3dfo76.data.subrecords.SNAM;
/**
 * https://falloutck.uesp.net/wiki/Actor
 */
public class NPC_ extends RECO
{
	public ZString EDID = null;

	public OBND OBND;

	public FormID INAM = null;

	public FormID RNAM = null;

	public FormID VTCK;

	public FormID TPLT;

	public FormID FULL = null;

	public ACBS ACBS = null;

	public ArrayList<SNAM> SNAMs = new ArrayList<SNAM>();

	public ArrayList<FormID> SPLOs = new ArrayList<FormID>();

	public ArrayList<CNTO> CNTOs = new ArrayList<CNTO>();

	public AIDT AIDT = null;

	public ArrayList<FormID> PKIDs = new ArrayList<FormID>();

	public FormID CNAM = null;

	public DATA DATA = null;

	public FormID ATKR;

	public FormID SPOR;

	public FormID OCOR;

	public FormID GWOR;

	public FormID ECOR;

	public KeyWords keyWords = new KeyWords();

	public FormID SHRT;

	public FormID PNAM;

	public FormID HCLF;

	public FormID ZNAM;

	public FormID GNAM;

	public FormID CSCR;

	public FormID DOFT;

	public FormID SOFT;

	public FormID DLPT;

	public FormID CRIF;

	public FormID FTST;

	public FormID WNAM;

	public NPC_(Record recordData)
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
			else if (sr.getSubrecordType().equals("VMAD"))
			{

			}
			else if (sr.getSubrecordType().equals("OBND"))
			{
				OBND = new OBND(bs);
			}
			else if (sr.getSubrecordType().equals("ACBS"))
			{
				ACBS = new ACBS(bs);
			}
			else if (sr.getSubrecordType().equals("SNAM"))
			{
				SNAMs.add(new SNAM(bs));
			}
			else if (sr.getSubrecordType().equals("INAM"))
			{
				INAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("VTCK"))
			{
				VTCK = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("TPLT"))
			{
				TPLT = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("RNAM"))
			{
				RNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("WNAM"))
			{
				WNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{

			}
			else if (sr.getSubrecordType().equals("ATKR"))
			{
				ATKR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("ATKD"))
			{

			}
			else if (sr.getSubrecordType().equals("ATKE"))
			{

			}
			else if (sr.getSubrecordType().equals("SPOR"))
			{
				SPOR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("OCOR"))
			{
				OCOR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("GWOR"))
			{
				GWOR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("ECOR"))
			{
				ECOR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("COCT"))
			{

			}
			else if (sr.getSubrecordType().equals("CNTO"))
			{
				CNTOs.add(new CNTO(bs));

			}
			else if (sr.getSubrecordType().equals("COED"))
			{

			}
			else if (sr.getSubrecordType().equals("AIDT"))
			{

			}
			else if (sr.getSubrecordType().equals("PKID"))
			{
				PKIDs.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{
				keyWords.setKSIZ(bs);
			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
				keyWords.setKWDA(bs);
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
				CNAM = new FormID(bs);
			}

			else if (sr.getSubrecordType().equals("SPCT"))
			{

			}
			else if (sr.getSubrecordType().equals("SPLO"))
			{
				SPLOs.add(new FormID(bs));
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				FULL = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("SHRT"))
			{
				SHRT = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{
				PNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("HCLF"))
			{
				HCLF = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("ZNAM"))
			{
				ZNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("GNAM"))
			{
				GNAM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("NAM5"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM6"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM7"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM8"))
			{

			}
			else if (sr.getSubrecordType().equals("CSDT"))
			{

			}
			else if (sr.getSubrecordType().equals("CSDI"))
			{

			}
			else if (sr.getSubrecordType().equals("CSDC"))
			{

			}
			else if (sr.getSubrecordType().equals("CSCR"))
			{
				CSCR = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DOFT"))
			{
				DOFT = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("SOFT"))
			{
				SOFT = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("DLPT"))
			{
				DLPT = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("CRIF"))
			{
				CRIF = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("FTST"))
			{
				FTST = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM9"))
			{

			}
			else if (sr.getSubrecordType().equals("NAMA"))
			{

			}
			else if (sr.getSubrecordType().equals("TINI"))
			{

			}
			else if (sr.getSubrecordType().equals("TINC"))
			{

			}
			else if (sr.getSubrecordType().equals("TINV"))
			{

			}
			else if (sr.getSubrecordType().equals("TIAS"))
			{

			}

			else if (sr.getSubrecordType().equals("DPLT"))
			{

			}
			else if (sr.getSubrecordType().equals("PRKR"))
			{

			}
			else if (sr.getSubrecordType().equals("PRKZ"))
			{

			}
			else if (sr.getSubrecordType().equals("DEST"))
			{

			}
			else if (sr.getSubrecordType().equals("DSTD"))
			{

			}
			else if (sr.getSubrecordType().equals("DSTF"))
			{

			}
			
			else if (sr.getSubrecordType().equals("FMRI"))
			{

			}
			else if (sr.getSubrecordType().equals("FMRS"))
			{

			}
			else if (sr.getSubrecordType().equals("TETI"))
			{

			}
			else if (sr.getSubrecordType().equals("TEND"))
			{

			}
			else if (sr.getSubrecordType().equals("MRSV"))
			{

			}
			else if (sr.getSubrecordType().equals("FMIN"))
			{

			}
			else if (sr.getSubrecordType().equals("TPTA"))
			{

			}
			else if (sr.getSubrecordType().equals("PRPS"))
			{

			}
			else if (sr.getSubrecordType().equals("NAM4"))
			{

			}
			else if (sr.getSubrecordType().equals("MWGT"))
			{

			}
			else if (sr.getSubrecordType().equals("MSDK"))
			{

			}
			else if (sr.getSubrecordType().equals("MSDV"))
			{

			}
			else if (sr.getSubrecordType().equals("APPR"))
			{

			}
			else if (sr.getSubrecordType().equals("LTPT"))
			{

			}
			else if (sr.getSubrecordType().equals("LTPC"))
			{

			}
			else if (sr.getSubrecordType().equals("OBTE"))
			{

			}
			else if (sr.getSubrecordType().equals("OBTF"))
			{

			}
			else if (sr.getSubrecordType().equals("OBTS"))
			{

			}else if (sr.getSubrecordType().equals("STOP"))
			{

			}
			else
			{
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
