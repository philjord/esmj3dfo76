package esmj3dfo76.data.records;

import java.util.ArrayList;
import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.MODL;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/BodyPartData
 */
public class BPTD extends RECO
{
	public ZString EDID = null;

	public MODL MODL = null;

	public ArrayList<BodyPart> bodyParts = new ArrayList<BodyPart>();

	public class BodyPart
	{
		public LString BPTN;

		public ZString BPNI;

		public ZString BPNN;

		public ZString BPNT;
	}

	public BPTD(Record recordData)
	{
		super(recordData);
		List<Subrecord> subrecords = recordData.getSubrecords();

		Subrecord sr = next(subrecords);
		EDID = new ZString(sr.getSubrecordData());
		sr = next(subrecords);

		if (sr.getSubrecordType().equals("MODL"))
		{
			MODL = new MODL(sr.getSubrecordData());
			sr = next(subrecords);
			if (sr.getSubrecordType().equals("MODT"))
			{
				MODL.addMODTSub(sr.getSubrecordData());
				sr = next(subrecords);
			}
		}

		while (sr != null && sr.getSubrecordType().equals("BPTN"))
		{
			BodyPart bodyPart = new BodyPart();
			bodyParts.add(bodyPart);

			bodyPart.BPTN = new LString(sr.getSubrecordData());
			sr = next(subrecords);
			bodyPart.BPNN = new ZString(sr.getSubrecordData());
			sr = next(subrecords);
			bodyPart.BPNT = new ZString(sr.getSubrecordData());
			sr = next(subrecords);
			bodyPart.BPNI = new ZString(sr.getSubrecordData());
			sr = next(subrecords);

			//BPND
			sr = next(subrecords);
			//NAM1
			sr = next(subrecords);
			//NAM4
			sr = next(subrecords);
			//NAM5
			sr = next(subrecords);

		}

		if (sr != null && sr.getSubrecordType().equals("RAGA"))
		{
			//RAGA
		}

	}
}
