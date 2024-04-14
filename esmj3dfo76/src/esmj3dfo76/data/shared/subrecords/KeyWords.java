package esmj3dfo76.data.shared.subrecords;

import java.util.ArrayList;

import esmj3d.data.shared.subrecords.FormID;
import tools.io.ESMByteConvert;

public class KeyWords
{
	public ArrayList<FormID> keywords = new ArrayList<FormID>();

	public int size = 0;

	public KeyWords()
	{

	}

	public void setKSIZ(byte[] bytes)
	{
		size = ESMByteConvert.extractInt(bytes, 0);
	}

	public void setKWDA(byte[] bytes)
	{
		//- 	KSIZ 	count 	unit32 	Number of formids in the following KWDA subrecord
		//- 	KWDA 	keywords 	formid 	Formid array of keywords
		//so this is a bunch of formIds not just 1
		//keywords.add(new FormID(bytes));
	}
}
