package esmj3dfo76.data;

import java.util.HashSet;
import java.util.List;

import esfilemanager.common.data.record.IRecordStore;
import esfilemanager.common.data.record.Record;
import esmj3d.data.shared.records.LTEX;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.records.TXST;
import esmj3dfo76.data.records.*;
import esmj3dfo76.data.subrecords.LVLO;

public class RecordToRECO
{
	/**
	 * TESTING ONLY
	 * @param master
	 * @param cellRecord
	 * @param children
	 */
	public static void makeRECOsForCELL(IRecordStore master, Record cellRecord, List<Record> children)
	{
		new esmj3dfo76.data.records.CELL(cellRecord);

		for (Record record : children)
		{
			try
			{
				if (record.getRecordType().equals("REFR"))
				{
					REFR refr = new REFR(record);
					makeREFR(refr, master);
				}
				else if (record.getRecordType().equals("ACHR"))
				{
					ACHR achr = new ACHR(record);
					makeACHR(achr, master);
				}
				else if (record.getRecordType().equals("LAND"))
				{
					//LAND land = new LAND(record);

				}
				else if (record.getRecordType().equals("PGRD"))
				{

				}
				else if (record.getRecordType().equals("PGRE"))
				{

				}
				else if (record.getRecordType().equals("NAVM"))
				{

				}
				else
				{
					System.out.println("  Record type not converted to RECO " + record.getRecordType());
				}
			}
			catch (NullPointerException e)
			{
				// probably just a bum pointer in the ESM
			}

		}
	}

	private static RECO makeREFR(REFR refr, IRecordStore master)
	{
		Record baseRecord = master.getRecord(refr.NAME.formId);

		RECO reco = makeRECO(baseRecord);
		if (reco instanceof LVLN)
		{
			reco = makeLVLN((LVLN) reco, master);
		}

		return reco;

	}

	public static RECO makeLVLI(LVLI lvli, IRecordStore master)
	{
		// TODO: randomly picked for now
		LVLO[] LVLOs = lvli.LVLOs;

		int idx = (int) (Math.random() * LVLOs.length);
		idx = idx == LVLOs.length ? 0 : idx;

		Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

		if (baseRecord.getRecordType().equals("NPC_"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			return new NPC_(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLI"))
		{
			// it is in fact a pointer across to another leveled character (LVLN)
			makeLVLI(new LVLI(baseRecord), master);
		}
		else
		{
			System.out.println("LVLI record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	private static RECO makeLVLN(LVLN lvln, IRecordStore master)
	{
		// TODO: randomly picked for now
		LVLO[] LVLOs = lvln.LVLOs;

		int idx = (int) (Math.random() * LVLOs.length);
		idx = idx == LVLOs.length ? 0 : idx;

		Record baseRecord = master.getRecord(LVLOs[idx].itemFormId);

		if (baseRecord.getRecordType().equals("NPC_"))
		{
			// it is in fact a pointer across to another leveled creature (LVLC)
			return new NPC_(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLN"))
		{
			// it is in fact a pointer across to another leveled character (LVLN)
			makeLVLN(new LVLN(baseRecord), master);
		}
		else
		{
			System.out.println("LVLN record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	private static RECO makeACHR(ACHR achr, IRecordStore master)
	{
		Record baseRecord = master.getRecord(achr.NAME.formId);
		if (baseRecord.getRecordType().equals("NPC_"))
		{
			return new NPC_(baseRecord);
		}
		else
		{
			System.out.println("ACHR record type not converted to j3d " + baseRecord.getRecordType());
		}

		return null;
	}

	public static RECO makeRECO(Record baseRecord)
	{
		if (baseRecord.getRecordType().equals("GMST"))
		{
			return new GMST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("KYWD"))
		{
			return new KYWD(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LCRT"))
		{
			return new LCRT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("AACT"))
		{
			return new AACT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("TXST"))
		{
			return new TXST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GLOB"))
		{
			return new GLOB(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLAS"))
		{
			return new CLAS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FACT"))
		{
			return new FACT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("HDPT"))
		{
			return new HDPT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("RACE"))
		{
			return new RACE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SOUN"))
		{
			return new SOUN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ASPC"))
		{
			return new ASPC(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MGEF"))
		{
			return new MGEF(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LTEX"))
		{
			return new LTEX(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ENCH"))
		{
			return new ENCH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SPEL"))
		{
			return new SPEL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ACTI"))
		{
			return new ACTI(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("TACT"))
		{
			return new TACT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ARMO"))
		{
			return new ARMO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("BOOK"))
		{
			return new BOOK(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CONT"))
		{
			return new CONT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DOOR"))
		{
			return new DOOR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("INGR"))
		{
			return new INGR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LIGH"))
		{
			return new LIGH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MISC"))
		{
			return new MISC(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("STAT"))
		{
			return new STAT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MSTT"))
		{
			return new MSTT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GRAS"))
		{
			return new GRAS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("TREE"))
		{
			return new TREE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FLOR"))
		{
			return new FLOR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FURN"))
		{
			return new FURN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("WEAP"))
		{
			return new WEAP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("AMMO"))
		{
			return new AMMO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("KEYM"))
		{
			return new KEYM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ALCH"))
		{
			return new ALCH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IDLM"))
		{
			return new IDLM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("COBJ"))
		{
			return new COBJ(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("PROJ"))
		{
			return new PROJ(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("HAZD"))
		{
			return new HAZD(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("WTHR"))
		{
			return new WTHR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLMT"))
		{
			return new CLMT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SPGD"))
		{
			return new SPGD(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("RFCT"))
		{
			return new RFCT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("REGN"))
		{
			return new REGN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("NAVI"))
		{
			return new NAVI(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("QUST"))
		{
			return new QUST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IDLE"))
		{
			return new IDLE(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("PACK"))
		{
			return new PACK(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CSTY"))
		{
			return new CSTY(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LSCR"))
		{
			return new LSCR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVSP"))
		{
			return new LVSP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ANIO"))
		{
			return new ANIO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("WATR"))
		{
			return new WATR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("EFSH"))
		{
			return new EFSH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("EXPL"))
		{
			return new EXPL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DEBR"))
		{
			return new DEBR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IMGS"))
		{
			return new IMGS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IMAD"))
		{
			return new IMAD(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FLST"))
		{
			return new FLST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("PERK"))
		{
			return new PERK(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("BPTD"))
		{
			return new BPTD(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ADDN"))
		{
			return new ADDN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("AVIF"))
		{
			return new AVIF(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CAMS"))
		{
			return new CAMS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CPTH"))
		{
			return new CPTH(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("VTYP"))
		{
			return new VTYP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MATT"))
		{
			return new MATT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IPCT"))
		{
			return new IPCT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("IPDS"))
		{
			return new IPDS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ARMA"))
		{
			return new ARMA(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ECZN"))
		{
			return new ECZN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LCTN"))
		{
			return new LCTN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MESG"))
		{
			return new MESG(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DOBJ"))
		{
			return new DOBJ(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LGTM"))
		{
			return new LGTM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MUSC"))
		{
			return new MUSC(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FSTP"))
		{
			return new FSTP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("FSTS"))
		{
			return new FSTS(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SMBN"))
		{
			return new SMBN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SMQN"))
		{
			return new SMQN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SMEN"))
		{
			return new SMEN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MUST"))
		{
			return new MUST(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DLVW"))
		{
			return new DLVW(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("EQUP"))
		{
			return new EQUP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("RELA"))
		{
			return new RELA(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ASTP"))
		{
			return new ASTP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("OTFT"))
		{
			return new OTFT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ARTO"))
		{
			return new ARTO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MATO"))
		{
			return new MATO(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("MOVT"))
		{
			return new MOVT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SNDR"))
		{
			return new SNDR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("DUAL"))
		{
			return new DUAL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("SNCT"))
		{
			return new SNCT(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("COLL"))
		{
			return new COLL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CLFM"))
		{
			return new CLFM(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("REVB"))
		{
			return new REVB(baseRecord);
		}

		//*********************specials below loaded for testing only
		else if (baseRecord.getRecordType().equals("NPC_"))
		{
			return new NPC_(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLI"))
		{
			return new LVLI(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("LVLN"))
		{
			return new LVLN(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("CELL"))
		{
			return new CELL(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("GRUP"))
		{
			//return new GRUP(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("REFR"))
		{
			return new REFR(baseRecord);
		}
		else if (baseRecord.getRecordType().equals("ACHR"))
		{
			return new ACHR(baseRecord);
		}
		else
		{
			if (!constructorsShowen.contains(baseRecord.getRecordType()))
			{
				System.out.println("else if (baseRecord.getRecordType().equals(\"" + baseRecord.getRecordType() + "\"))");
				System.out.println("{");
				System.out.println("return new " + baseRecord.getRecordType() + "(baseRecord);");
				System.out.println("}");
				constructorsShowen.add(baseRecord.getRecordType());
			}
		}
		return null;
	}

	private static HashSet<String> constructorsShowen = new HashSet<String>();

}
