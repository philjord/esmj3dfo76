package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

 
public class CNCY extends RECO {

	public FormID	FULL;

	public MODL		MODL;

	/*CNCY Currency count=17

C________	Subrecord	Type______	Info
1	EDID	ZString	0-0. Count=17 After Head(17), Followed by DURL(10), OBND(7), . Editor ID, used only by consturction kit, not loaded at runtime
0-1	DURL	ZString	0-1opt. Count=10 After EDID(10), Followed by OBND(10), .
1	OBND	Vec3	1-0. Count=17 After DURL(10), EDID(7), Followed by FULL(5), PTRN(9), ENLT(3), . Object Bounds
0-1	PTRN	byte[] 4	1-1opt. Count=9 After OBND(9), Followed by MODL(3), FULL(6), .
0-1	FULL	FormId	2-opt. Count=11 After OBND(5), PTRN(6), Followed by ENLT(5), MODL(6), . Often the Object in game name, not not always
0-1	MODL	BsaFileName	3-0opt. Count=9 After PTRN(3), FULL(6), Followed by MODT(9), . Model Name of Nif file
0-1	MODT	byte[] 80, 20	3-1. Count=9 After MODL(9), Followed by MODS(4), ENLT(5), . Model Translation (not sure of the contents)
0-1	MODS	byte[] 4	3-2opt. Count=4 After MODT(4), Followed by ENLT(4), .
1	ENLT	Int	4-0. Count=17 After FULL(5), MODS(4), MODT(5), OBND(3), Followed by ENLS(17), .
1	ENLS	byte[] 4	4-1. Count=17 After ENLT(17), Followed by AUUV(17), .
1	AUUV	byte[] 28	4-2. Count=17 After ENLS(17), Followed by VCRY(12), YNAM(5), .
0-1	YNAM	FormId	4-3opt. Count=5 After AUUV(5), Followed by ZNAM(5), . Sound Reference Pointer.
0-1	ZNAM	FormId	4-4. Count=5 After YNAM(5), Followed by VCRY(4), KSIZ(1), . Sound Reference Pointer.
0-1	KSIZ	Int	4-5opt. Count=1 After ZNAM(1), Followed by KWDA(1), .
0-1	KWDA	byte[] 4	4-6. Count=1 After KSIZ(1), Followed by VCRY(1), .
1	VCRY	Int	5-0. Count=17 After AUUV(12), ZNAM(4), KWDA(1), Followed by DATA(17), .
1	DATA	byte[] 8	5-1. Count=17 After VCRY(17), Followed by AQIC(17), .
1	AQIC	byte[] 8	5-2. Count=17 After DATA(17), Followed by MXCT(17), .
1	MXCT	FormId	5-3. Count=17 After AQIC(17), Followed by SNAM(10), CRTY(7), . Pointers to REFR. Pointers to CELL.
0-1	SNAM	FormId	5-4opt. Count=10 After MXCT(10), Followed by CRTY(10), . Object Ref Pointer.
1	CRTY	byte[] 2	6-0. Count=17 After SNAM(10), MXCT(7), Followed by FNAM(17), .
1	FNAM	Int	6-1. Count=17 After CRTY(17), Followed by Last(17), .
	*/
	public CNCY(Record recordData) {

		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++) {
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID")) {
				setEDID(bs);
			} else if (sr.getSubrecordType().equals("DURL")) {
			} else if (sr.getSubrecordType().equals("OBND")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("FULL")) {
				FULL = new FormID(bs);
			} else if (sr.getSubrecordType().equals("MODL")) {
				MODL = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MODT")) {
				MODL.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("MODS")) {
			} else if (sr.getSubrecordType().equals("ENLT")) {
			} else if (sr.getSubrecordType().equals("ENLS")) {
			} else if (sr.getSubrecordType().equals("AUUV")) {
			} else if (sr.getSubrecordType().equals("YNAM")) {
			} else if (sr.getSubrecordType().equals("ZNAM")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {				
			} else if (sr.getSubrecordType().equals("VCRY")) {
			} else if (sr.getSubrecordType().equals("DATA")) {
			} else if (sr.getSubrecordType().equals("AQIC")) {
			} else if (sr.getSubrecordType().equals("MXCT")) {
			} else if (sr.getSubrecordType().equals("SNAM")) {
			} else if (sr.getSubrecordType().equals("CRTY")) {
			} else if (sr.getSubrecordType().equals("FNAM")) {
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
