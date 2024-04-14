package esmj3dfo76.data.subrecords;

import tools.io.ESMByteConvert;

public class BODT
{

	public static int HEAD_MASK = 0x00000001; //= Head

	public static int HAIR_MASK = 0x00000002; //= Hair

	public static int BODY_MASK = 0x00000004; //= Body

	public static int HAND_MASK = 0x0000008; //= Hand

	public static int AMULET_MASK = 0x00000020; //= Amulet	

	public static int RING_MASK = 0x00000040; //= Ring

	public static int FOOT_MASK = 0x00000080; //= Feet

	public static int SHIELD_MASK = 0x00002000; //= Shield

	public static int TAIL_MASK = 0x00000400; //= Tail

	public int flags;

	public BODT(byte[] bytes)
	{
		flags = ESMByteConvert.extractInt(bytes, 0);
	}

	public boolean isHead()
	{
		return (flags & HEAD_MASK) > 0;
	}

	public boolean isHair()
	{
		return (flags & HAIR_MASK) > 0;
	}

	public boolean isBody()
	{
		return (flags & BODY_MASK) > 0;
	}

	public boolean isHand()
	{
		return (flags & HAND_MASK) > 0;
	}

	public boolean isFoot()
	{
		return (flags & FOOT_MASK) > 0;
	}

	public boolean isRing()
	{
		return (flags & RING_MASK) > 0;
	}

	public boolean isAmulet()
	{
		return (flags & AMULET_MASK) > 0;
	}

	public boolean isShield()
	{
		return (flags & SHIELD_MASK) > 0;
	}

	public boolean isTail()
	{
		return (flags & TAIL_MASK) > 0;
	}

}
