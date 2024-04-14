package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.CommonCELL;
import esmj3d.data.shared.subrecords.FormID;
/**
 * https://falloutck.uesp.net/wiki/Cell
 */
public class CELL extends CommonCELL
{

	public DATA DATA;

	public int XGLB = -1;

	public FormID LTMP = null;

	public FormID XCMO = null;

	public FormID XCAS = null;

	public FormID XEZN = null;

	public FormID XILL = null;

	public FormID XCIM;

	public CELL(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++)
		{
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("DATA"))
			{
				DATA = new DATA(bs);
			}
			else if (sr.getSubrecordType().equals("TVDT"))
			{
			}
			else if (sr.getSubrecordType().equals("MHDT"))
			{
			}
			else if (sr.getSubrecordType().equals("FULL"))
			{
				// TES5 FULL is a formId of some sort, blank out the lstring from super
				FULL = null;
			}
			else if (sr.getSubrecordType().equals("LTMP"))
			{
				LTMP = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XCMO"))
			{
				XCMO = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XCIM"))
			{
				XCIM = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XCAS"))
			{
				XCAS = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("XWEM"))
			{
			}
			else if (sr.getSubrecordType().equals("XILL"))
			{
				XILL = new FormID(bs);
			}
			else if (sr.getSubrecordType().equals("LNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("XNAM"))
			{
			}
			else if (sr.getSubrecordType().equals("XWCS"))
			{
			}
			else if (sr.getSubrecordType().equals("XWCU"))
			{
			}
			else if (sr.getSubrecordType().equals("XWCN"))
			{
			}
			else if (sr.getSubrecordType().equals("XLCN"))
			{

			}
			else if (sr.getSubrecordType().equals("XEZN"))
			{
				XEZN = new FormID(bs);
			}
			else
			{
				// no longer possible with CommonCELL
				//System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}

	@Override
	public String showDetails()
	{
		return "CELL : (" + formId + "|" + Integer.toHexString(formId) + ") " + EDID.str;
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
