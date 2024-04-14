package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
/**
 * https://falloutck.uesp.net/wiki/Quest
 */
public class QUST extends RECO
{

	public ZString EDID = null;

	public QUST(Record recordData)
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
			}
			else if (sr.getSubrecordType().equals("DNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CTDA"))
			{
			}
			else if (sr.getSubrecordType().equals("NEXT"))
			{
			}
			else if (sr.getSubrecordType().equals("ANAM"))
			{
			}
			else if (sr.getSubrecordType().equals("VMAD"))
			{
			}
			else if (sr.getSubrecordType().equals("ENAM"))
			{
			}
			else if (sr.getSubrecordType().equals("FLTR"))
			{
			}
			else if (sr.getSubrecordType().equals("INDX"))
			{
			}
			else if (sr.getSubrecordType().equals("QSDT"))
			{
			}
			else if (sr.getSubrecordType().equals("ALST"))
			{
			}
			else if (sr.getSubrecordType().equals("ALID"))
			{
			}
			else if (sr.getSubrecordType().equals("FNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFE"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFD"))
			{
			}
			else if (sr.getSubrecordType().equals("VTCK"))
			{
			}
			else if (sr.getSubrecordType().equals("ALED"))
			{
			}
			else if (sr.getSubrecordType().equals("ALLS"))
			{
			}
			else if (sr.getSubrecordType().equals("ALUA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFR"))
			{
			}
			else if (sr.getSubrecordType().equals("ALPC"))
			{
			}
			else if (sr.getSubrecordType().equals("ALCO"))
			{
			}
			else if (sr.getSubrecordType().equals("ALCA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALCL"))
			{
			}
			else if (sr.getSubrecordType().equals("QOBJ"))
			{
			}
			else if (sr.getSubrecordType().equals("NNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("QSTA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALRT"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFI"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFC"))
			{
			}
			else if (sr.getSubrecordType().equals("ALNA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALNT"))
			{
			}
			else if (sr.getSubrecordType().equals("CIS2"))
			{
			}
			else if (sr.getSubrecordType().equals("QTGL"))
			{
			}
			else if (sr.getSubrecordType().equals("ECOR"))
			{
			}
			else if (sr.getSubrecordType().equals("ALEQ"))
			{
			}
			else if (sr.getSubrecordType().equals("ALEA"))
			{
			}
			else if (sr.getSubrecordType().equals("ALDN"))
			{
			}
			else if (sr.getSubrecordType().equals("KNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("CNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("ALSP"))
			{
			}
			else if (sr.getSubrecordType().equals("ALFL"))
			{
			}
			else if (sr.getSubrecordType().equals("COCT"))
			{
			}
			else if (sr.getSubrecordType().equals("CNTO"))
			{
			}
			else if (sr.getSubrecordType().equals("KSIZ"))
			{
			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{
			}
			else if (sr.getSubrecordType().equals("NAM0"))
			{
			}
			else if (sr.getSubrecordType().equals("SCHR"))
			{
			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("SPOR"))
			{
			}
			else if (sr.getSubrecordType().equals("SCTX"))
			{
			}
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
