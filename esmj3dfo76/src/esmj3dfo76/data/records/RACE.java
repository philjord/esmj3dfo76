package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.LString;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.subrecords.BODT;
import tools.io.ESMByteConvert;
/**
 * https://falloutck.uesp.net/wiki/Race
 * 
 *  ID: The ID, also referred to as Editor ID, is used by the Creation Kit to uniquely identify this record within a Data File.
    Base Actor Values
        Actor Value: The Actor Value to use.
        Amount:
    Movement
    Specials: A list of Spells to use.
    Flags
    XP Value:
    Size:
    Flight:
    Orientation Limits:
    Copied Data:
    IdleChatterMinTime:
    IdleChatterMaxTime:
    Keywords: A list of Keywords that signal information to other Game Systems.
 */
public class RACE extends RECO
{
	public ZString EDID = null;

	public FormID FULL = null;
	
	public LString DESC = null;

	public FormID WNAM = null;

	public FormID BOD2 = null;

	public ZString maleSkeleton = null;

	public ZString femaleSkeleton = null;

	public FormID GNAM = null;

	public RACE(Record recordData)
	{
		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();

		Subrecord sr = next(subrecords);
		EDID = new ZString(sr.getSubrecordData());
		sr = next(subrecords);

		if (sr.getSubrecordType().equals("FULL"))
		{
			FULL = new FormID(sr.getSubrecordData());
			sr = next(subrecords);
		}

		// not an LString, lloks like one byte maybe
		//DESC = new LString(sr.getSubrecordData());
		sr = next(subrecords);

		if (sr.getSubrecordType().equals("SPCT"))
		{
			int sploCount = ESMByteConvert.extractInt(sr.getSubrecordData(), 0);
			sr = next(subrecords);
			for (int i = 0; i < sploCount; i++)
			{
				//SPLO
				sr = next(subrecords);
			}
		}

		if (sr.getSubrecordType().equals("WNAM"))
		{
			WNAM = new FormID(sr.getSubrecordData());
			sr = next(subrecords);
		}

		BOD2 = new FormID(sr.getSubrecordData());
		sr = next(subrecords);

		if (sr.getSubrecordType().equals("KSIZ"))
		{
			int kwdaCount = ESMByteConvert.extractInt(sr.getSubrecordData(), 0);
			
			//KWDA FormID and PRPS != kwdaCount  
			sr = next(subrecords);
			sr = next(subrecords);
			//PRPS
			sr = next(subrecords);
		}

		//APPR
		sr = next(subrecords);
		
		//DATA
		sr = next(subrecords);

		//MNAM
		sr = next(subrecords);

		//ANAM
		maleSkeleton = new ZString(sr.getSubrecordData());
		sr = next(subrecords);

		//MODT
		sr = next(subrecords);

		//FNAM
		sr = next(subrecords);

		femaleSkeleton = new ZString(sr.getSubrecordData());
		sr = next(subrecords);

		//MODT
		sr = next(subrecords);


		while (sr.getSubrecordType().equals("MTNM"))
		{
			//MTNM
			sr = next(subrecords);
		}

		//VTCK
		sr = next(subrecords);

		if (sr.getSubrecordType().equals("DNAM"))
		{
			//DNAM 
			sr = next(subrecords);
		}

		if (sr.getSubrecordType().equals("HCLF"))
		{
			//HCLF 
			sr = next(subrecords);
		}

		if (sr.getSubrecordType().equals("TINL"))
		{
			//TINL 
			sr = next(subrecords);
		}

		//PNAM
		sr = next(subrecords);

		//UNAM		
		sr = next(subrecords);

		while (sr.getSubrecordType().equals("ATKD"))
		{
			//ATKD
			sr = next(subrecords);

			//ATKE
			sr = next(subrecords);
		}

		//NAM1
		sr = next(subrecords);
		//MNAM
		sr = next(subrecords);
		//INDX
		sr = next(subrecords);
		//MODL
/*		sr = next(subrecords);
		//MODT
		sr = next(subrecords);
		//FNAM
		sr = next(subrecords);
		//INDX
		sr = next(subrecords);
		//MODL
		sr = next(subrecords);
		//MODT
		sr = next(subrecords);
		
		if (sr.getSubrecordType().equals("GNAM"))
		{
			GNAM = new FormID(sr.getSubrecordData());
			sr = next(subrecords);
		}

		//NAM2 not in fo4? or optional?
		//sr = next(subrecords);
		//NAM3
		sr = next(subrecords);
		//MNAM
		sr = next(subrecords);
		//MODL
		sr = next(subrecords);
		//MODT
		sr = next(subrecords);
		//FNAM
		sr = next(subrecords);
		//MODL
		sr = next(subrecords);
		//MODT
		sr = next(subrecords);*/

		 // many NAME subs

	}
}
