package com.github.roadfinder.algortihm.impl;

import com.github.roadfinder.algortihm.impl.Individual.Interval;

public class Crossovers {
	private static Individual orderedFunc(Individual parent1, Individual parent2, Individual.Interval inter)
	{
		if(inter.length() == 0)
			return parent2;
		
		final int N = parent1.getSize();
		int[] swatch = inter.getSwatch();
		
		int[] genotype2 = parent2.getGenotype();
		
		//Selecting repeating values
		for(int x= 0; x <swatch.length; ++x)
		{
			for(int y = 0; y < N; ++y)
			{
				if(genotype2[y] == swatch[x])
				{
					genotype2[y] = 0;
					break;
				}
			}
		}
		
		
		//Putting only swatch into child
		int[] child_genotype = parent1.getGenotype();
		for(int i = 0; i < N; ++i)
		{
			if(i < inter.start || i > inter.end)
			{
				child_genotype[i] = 0;
			}
		}
		
		//Looking for misssing gens
		int i = 0;
		for(int y= 0; y < N; ++y)
		{
			if(child_genotype[y] == 0)
			{
				while(genotype2[i] == 0) { ++i; }
				child_genotype[y] = genotype2[i];
				++i;
			} 
		}
		
		Individual child = new Individual(child_genotype);
		
		return child;
	}
	public static Individual[] ordered(Individual parent1, Individual parent2)
	{
		if(parent1.getSize() != parent2.getSize())
			return null;
		
		Interval inter1 = parent1.randInterval(), inter2 = parent2.getInterval(inter1);
		
		Individual[] childs = new Individual[2];
		childs[0]=  orderedFunc(parent1, parent2, inter1);
		childs[1]=  orderedFunc(parent2, parent1, inter2);
	
		return childs;
		
	}
}
