package com.github.roadfinder.algortihm.impl;

import java.util.ArrayList;
import java.util.Random;

public class Individual implements Comparable<Individual>{ 
	private int[] genotype;
	double fitValue=0;
	public double D = 0;

	public class Interval {
		public int start = 0, end = 0;
		Interval() { };
		Interval(Interval inter) {
			start = inter.start;
			end = inter.end;
		}
		public void Randomize()
		{
			Random random = new Random();
			int x= random.nextInt(genotype.length), y=random.nextInt(genotype.length);
			if(x < y)
			{
				start = x;
				end = y;
			}
			else
			{
				start = y;
				end = x;
			}
		}
		public int length() {
			return end-start+1;
		}
		public int[] getSwatch() {
			int[] swatch = new int[length()];
			int index = 0;
			for(int i = start; i <= end; ++i)
			{
				swatch[index++] = genotype[i];
			}
			return swatch;
		}
		
	};
	
	public int[] getGenotype() {
		int[] array = new int[getSize()];
		System.arraycopy(genotype, 0, array, 0, genotype.length);
		return array;
	}
	public int getSize() {
		return genotype.length;
	}
	Individual(int size) {
		genotype = new int[size];
		for(int i = 0; i < size; ++i)
			genotype[i] = 0;
	}
	Individual(int[] genotype){
		this.genotype = new int[genotype.length];
		System.arraycopy(genotype, 0, this.genotype, 0, genotype.length);
	}
	Individual(Individual ind)
	{
		this.genotype = new int[ind.getSize()];
		System.arraycopy(ind.genotype, 0, this.genotype, 0, ind.getSize());
	}
	
	public void Randomize() {
		Random random = new Random();
		ArrayList<Integer>  list = new ArrayList<Integer>(); 
		for(int i = 1; i <= genotype.length; ++i)
		{
			list.add(i);
		}
		for(int i = 0; i <  genotype.length; ++i)
		{
			int index = random.nextInt(list.size());
			genotype[i] = list.get(index);
			list.remove(index);
		}
	}
	
	public Interval randInterval()
	{
		Interval inter =new Interval();
		inter.Randomize();
		return inter;
	}
	public Interval getInterval(Interval interval)
	{
		return new Interval(interval);
	}
	
	public String toString() {
		String out = new String();
		for(int i = 0; i < genotype.length; ++i)
			out += genotype[i] + " ";
		return out;
	}
	//TODO: Funkcja obliczajaca dopasowanie
	//TODO: Funkcja mutacji???
	public int compareTo(Individual compareInd) {
		// TODO Auto-generated method stub
		return (int)Math.signum(this.fitValue - compareInd.fitValue);
	}
	
}
