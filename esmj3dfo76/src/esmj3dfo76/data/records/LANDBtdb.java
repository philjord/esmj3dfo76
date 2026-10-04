package esmj3dfo76.data.records;

import java.util.ArrayList;
 

import esfilemanager.common.data.record.Record;
import esfilemanager.common.data.record.Subrecord;
import esmj3d.data.shared.records.LAND;

/**
 * Fake LAND cos Btdb is the real guy
 *
 */
public class LANDBtdb extends LAND {

	private static class FakeRecord extends Record {
		public FakeRecord(int fakeFormId) {
			this.formID = fakeFormId;
			this.subrecordList = new ArrayList<Subrecord>();
		}
	}

	public LANDBtdb(int fakeFormId) {
		super(new FakeRecord(fakeFormId));
	}

	@Override
	public String showDetails() {
		return "LANDbtdb fake id " + this.formId;
	}

	 

}
