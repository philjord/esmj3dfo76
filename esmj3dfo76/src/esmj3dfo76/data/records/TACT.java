package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/TalkingActivator
 */
public class TACT extends RECO
{

	

	public MODL MODL;

	public TACT(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("FULL")) {

			} else if (sr.getSubrecordType().equals("DSTF")) {

			} else if (sr.getSubrecordType().equals("DEST")) {

			} else if (sr.getSubrecordType().equals("VNAM")) {

			} else if (sr.getSubrecordType().equals("DSTD")) {

			} else if (sr.getSubrecordType().equals("PNAM")) {

			} else if (sr.getSubrecordType().equals("FNAM")) {

			} 
			//FO76
			else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {
			} else if (sr.getSubrecordType().equals("AUUV")) {
			} else if (sr.getSubrecordType().equals("QSTI")) {
			} else if (sr.getSubrecordType().equals("CNDC")) {
			} else if (sr.getSubrecordType().equals("CITC")) {
			} else if (sr.getSubrecordType().equals("CTDA")) {
			} else if (sr.getSubrecordType().equals("PAHD")) {
			} else if (sr.getSubrecordType().equals("CTRN")) {
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
