package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.FormID;
import esmj3d.data.shared.subrecords.MODL;

/**
 * https://falloutck.uesp.net/wiki/Holotape
 */
public class NOTE extends RECO {

	public FormID	FULL;

	public MODL		MODL;

	/*NOTE Note count=572
	
			C________	Subrecord	Type______	Info
			1	EDID	ZString	0-0. Count=572 After Head(572), Followed by OBND(508), VMAD(64), . Editor ID, used only by consturction kit, not loaded at runtime
			0-1	VMAD	byte[]	0-1opt. Count=64 After EDID(64), Followed by OBND(64), .
			1	OBND	byte[] 12	1-0. Count=572 After EDID(508), VMAD(64), Followed by PTRN(571), FULL(1), . Object Bounds
			0-1	PTRN	FormId	1-1opt. Count=571 After OBND(571), Followed by FULL(41), SNTP(484), XALG(46), . Related to models? Pointer.
			0-1	SNTP	ZString	1-2opt. Count=484 After PTRN(484), Followed by FULL(240), XALG(244), .
			0-1	XALG	byte[] 8	2-opt. Count=290 After PTRN(46), SNTP(244), Followed by FULL(286), MODL(4), .
			0-1	FULL	FormId?	3-opt. Count=568 After PTRN(41), SNTP(240), XALG(286), OBND(1), Followed by MODL(566), KSIZ(1), VCRY(1), . Often the Object in game name, not not always
			0-1	MODL	BsaFileName	4-0opt. Count=570 After FULL(566), XALG(4), Followed by MODT(570), . Model Name of Nif file
			0-1	MODT	byte[] 96, 80, 20, 84	4-1. Count=570 After MODL(570), Followed by KSIZ(508), YNAM(62), . Model Translation (not sure of the contents)
			0-1	YNAM	FormId	4-2opt. Count=62 After MODT(62), Followed by ZNAM(61), KSIZ(1), . Sound Reference Pointer.
			0-1	ZNAM	FormId	4-3opt. Count=61 After YNAM(61), Followed by KSIZ(61), . Sound Reference Pointer.
			0-1	KSIZ	Int	5-0opt. Count=571 After MODT(508), FULL(1), ZNAM(61), YNAM(1), Followed by KWDA(571), .
			0-1	KWDA	ZString	5-1. Count=571 After KSIZ(571), Followed by VCRY(571), .
			1	VCRY	Int	6-0. Count=572 After KWDA(571), FULL(1), Followed by DNAM(572), .
			1	DNAM	byte[] 1	6-1. Count=572 After VCRY(572), Followed by DATA(572), .
			1	DATA	byte[] 8	6-2. Count=572 After DNAM(572), Followed by SNAM(544), Last(19), DIQO(1), PNAM(8), .
			0-1	SNAM	FormId?	6-3opt. Count=544 After DATA(544), Followed by Last(488), DIQO(56), . Pointers to SCEN. Pointers to TERM. Pointers to SNDR.
			0-1	DIQO	FormId	7-opt. Count=57 After SNAM(56), DATA(1), Followed by Last(57), . Quest Pointer.
			0-1	PNAM	ZString	8-opt. Count=8 After DATA(8), Followed by Last(8), .
	*/
	public NOTE(Record recordData) {

		super(recordData);

		List<Subrecord> subrecords = recordData.getSubrecords();
		for (int i = 0; i < subrecords.size(); i++) {
			Subrecord sr = subrecords.get(i);
			byte[] bs = sr.getSubrecordData();

			if (sr.getSubrecordType().equals("EDID")) {
				setEDID(bs);
			} else if (sr.getSubrecordType().equals("VMAD")) {
			} else if (sr.getSubrecordType().equals("OBND")) {
			} else if (sr.getSubrecordType().equals("PTRN")) {
			} else if (sr.getSubrecordType().equals("SNTP")) {
			} else if (sr.getSubrecordType().equals("XALG")) {
			} else if (sr.getSubrecordType().equals("FULL")) {
				FULL = new FormID(bs);
			} else if (sr.getSubrecordType().equals("MODL")) {
				MODL = new MODL(bs);
			} else if (sr.getSubrecordType().equals("MODT")) {
				MODL.addMODTSub(bs);
			} else if (sr.getSubrecordType().equals("YNAM")) {
			} else if (sr.getSubrecordType().equals("ZNAM")) {
			} else if (sr.getSubrecordType().equals("KSIZ")) {
			} else if (sr.getSubrecordType().equals("KWDA")) {
			} else if (sr.getSubrecordType().equals("VCRY")) {
			} else if (sr.getSubrecordType().equals("DNAM")) {
			} else if (sr.getSubrecordType().equals("DATA")) {				
			} else if (sr.getSubrecordType().equals("SNAM")) {
			} else if (sr.getSubrecordType().equals("DIQO")) {
			} else if (sr.getSubrecordType().equals("PNAM")) {
			} else {
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}
		}
	}
}
