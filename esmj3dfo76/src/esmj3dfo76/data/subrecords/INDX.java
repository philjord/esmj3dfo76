package esmj3dfo76.data.subrecords;

import tools.io.ESMByteConvert;

public class INDX
{
	public int idx;

	public INDX(byte[] bytes)
	{
		idx = ESMByteConvert.extractInt(bytes, 0);
	}
}
