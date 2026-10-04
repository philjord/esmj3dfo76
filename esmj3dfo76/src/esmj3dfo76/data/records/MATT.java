package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import tools.io.ESMByteConvert;

/**
 * MATT 	Material Type
 */
public class MATT extends RECO
{
	/**
	MATT Material Type count=178
	
	C________	Subrecord	Type______	Info
	1	EDID	ZString	0-0. Count=178 After Head(178), Followed by PNAM(176), MNAM(2), . Editor ID, used only by consturction kit, not loaded at runtime
	0-1	PNAM	FormId	0-1opt. Count=176 After EDID(176), Followed by MNAM(176), . Material Type Pointer.
	1	MNAM	ZString	1-0. Count=178 After PNAM(176), EDID(2), Followed by CNAM(178), .
	1	CNAM	byte[] 12	1-1. Count=178 After MNAM(178), Followed by BNAM(178), .
	1	BNAM	byte[] 4	1-2. Count=178 After CNAM(178), Followed by FNAM(178), .
	1	FNAM	Int	1-3. Count=178 After BNAM(178), Followed by HNAM(155), Last(22), ANAM(1), .
	0-1	HNAM	FormId	1-4opt. Count=155 After FNAM(155), Followed by Last(155), . Impact Data Set Pointer.
	0-1	ANAM	BsaFileName	2-0opt. Count=1 After FNAM(1), Followed by MODT(1), . Including names of nif files.
	0-1	MODT	byte[] 16	2-1. Count=1 After ANAM(1), Followed by Last(1), . Model Translation (not sure of the contents)
	 * @param recordData
	 */
	
	public int parentMaterialId;
	public String materialName;

	public MATT(Record recordData)
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
			}else if (sr.getSubrecordType().equals("PNAM")){
				//parentMaterial I'd say
				parentMaterialId = ESMByteConvert.extractInt(bs, 0); 				
			}
			else if (sr.getSubrecordType().equals("MNAM")){
				//string of? material bgsm?
				materialName = ZString.toString(bs);
			}
			else if (sr.getSubrecordType().equals("CNAM")){}
			else if (sr.getSubrecordType().equals("BNAM")){}
			else if (sr.getSubrecordType().equals("FNAM")){}
			else if (sr.getSubrecordType().equals("HNAM")){}
			else if (sr.getSubrecordType().equals("ANAM")){}
			else if (sr.getSubrecordType().equals("MODT")){}
			
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}

}
