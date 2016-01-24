package com.github.roadfinder.algortihm.impl;

public class City extends Place
{

	public final int mass;

	public City( int x, int y, int mass )
	{
		super( x, y );
		this.mass = mass;
	}

	@Override
	public int getSize()
	{
		return 2 * this.mass + 10;
	}

	@Override
	public String toString()
	{
		return "City{" +
				" x= " + getX() +
				" y= " + getY() +
				" mass= " + mass +
				'}';
	}
}
