package esmj3dfo76.data.records;

import java.util.List;

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.RECO;
import esmj3d.data.shared.subrecords.ZString;
import esmj3dfo76.data.subrecords.DNAM;
/**
 * https://falloutck.uesp.net/wiki/IdleMarker
 * An IdleMarker allows you to specify how an Actor should behave when they reach a specific area. 
 * These IdleMarkers can represent specific animations such as SweepIdleMarker or CounterLeanMarker, 
 * or allow the Actor to choose randomly from among a set of potential animations such as PatrolIdleMarker, 
 * which includes a set of ten animations like shifting weight or swatting flies. 
 * Actors will ignore any animations they can't use. The IdleMarker object is found within the Object Window under the Miscellaneous Category. 
 */
public class IDLM extends RECO
{
	public ZString EDID = null;

	public DNAM DNAM = null;

	//data data

	public IDLM(Record recordData)
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
			else if (sr.getSubrecordType().equals("OBND"))
			{

			}
			else if (sr.getSubrecordType().equals("IDLA"))
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
			else if (sr.getSubrecordType().equals("KSIZ"))
			{

			}
			else if (sr.getSubrecordType().equals("KWDA"))
			{

			}
			else if (sr.getSubrecordType().equals("QNAM"))
			{

			}
			
			
			
			else
			{
				System.out.println("unhandled : " + sr.getSubrecordType() + " in record " + recordData + " in " + this);
			}

		}
	}
}
