package com.github.roadfinder.algortihm.impl;

public class Mutations {
	public static Individual RSM(Individual ind)
	{
		int[] genotype = ind.getGenotype();
		Individual.Interval inter = ind.randInterval();
		System.out.println("Rand interval for mutation <" + inter.start + "; " + inter.end + ">");
		
		int[] newGenotype = ind.getGenotype();
		for(int i = 0; i <= inter.end-inter.start; ++i)
		{
			newGenotype[inter.start+i] = genotype[inter.end-i];
		}
		return new Individual(newGenotype);
	}
}
