package com.withertech.example.block;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum FaceMode implements StringRepresentable
{
	INPUT,
	OUTPUT,
	NONE;

	@Override
	public String getSerializedName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
