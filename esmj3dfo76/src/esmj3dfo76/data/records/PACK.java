package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;

/**
 * https://falloutck.uesp.net/wiki/Package
 */
public class PACK extends RECO
{

	

	public PACK(Record recordData)
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
			else if (sr.getSubrecordType().equals("PKDT"))
			{
			}
			else if (sr.getSubrecordType().equals("PSDT"))
			{
			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{
			}
			else if (sr.getSubrecordType().equals("PKCU"))
			{
			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("PLDT"))
			{
			}
			else if (sr.getSubrecordType().equals("UNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("POBA"))
			{
			}
			else if (sr.getSubrecordType().equals("INAM"))
			{
			}
			else if (sr.getSubrecordType().equals("PDTO"))
			{
			}
			else if (sr.getSubrecordType().equals("POEA"))
			{
			}
			else if (sr.getSubrecordType().equals("POCA"))
			{
			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CIS2"))
			{
			}
			else if (sr.getSubrecordType().equals("PTDA"))
			{
			}
			else if (sr.getSubrecordType().equals("CITC"))
			{
			}
			else if (sr.getSubrecordType().equals("PRCB"))
			{
			}
			else if (sr.getSubrecordType().equals("PNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("PKC2"))
			{
			}
			else if (sr.getSubrecordType().equals("BNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{
			}
			else if (sr.getSubrecordType().equals("IDLF"))
			{
			}
			else if (sr.getSubrecordType().equals("IDLC"))
			{
			}
			else if (sr.getSubrecordType().equals("IDLT"))
			{
			}
			else if (sr.getSubrecordType().equals("IDLA"))
			{
			}
			else if (sr.getSubrecordType().equals("CIS1"))
			{
			}
			else if (sr.getSubrecordType().equals("PFO2"))
			{
			}
			else if (sr.getSubrecordType().equals("SCHR"))
			{
			}
			else if (sr.getSubrecordType().equals("SCTX"))
			{
			}
			else if (sr.getSubrecordType().equals("PFOR"))
			{
			}
			else if (sr.getSubrecordType().equals("SCDA"))
			{
			}
			else if (sr.getSubrecordType().equals("TPIC"))
			{
			}
			else if (sr.getSubrecordType().equals("TNAM"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
