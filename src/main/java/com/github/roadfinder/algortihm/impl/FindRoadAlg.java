package com.github.roadfinder.algortihm.impl;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;


public class FindRoadAlg {
	public Individual[] population; //chwilo PUBLIC
	private City[] cities;
	double mean_fit = 0;
	double best_fit = 0;
	Random randmut = new Random();
	private class Place {
		int x, y;
	}
	private Vector<Place> forbidPlaces = new Vector<Place>();
	public void AddForbidPlace(int x, int y)
	{
		Place place = new Place();
		place.x = x;
		place.y = y;
		forbidPlaces.add(place);
		for ( int i = 0; i < population.length; ++i ) {
			population[ i ].fitValue = 0;
		}
	}
	public boolean CheckCollision(int sX, int sY, int eX, int eY)
	{
		boolean vertical = (sX == eX);
		double a = 0, b = 0;
		if(vertical == false)
		{
			a = (eY-sY)/(eX-sX);
			b =sY- a*sX  ;
		}
		
		int maxY = Math.max(sY, eY);
		int minY = Math.min(sY, eY);
		int maxX = Math.max(sX, eX);
		int minX = Math.min(sX, eX);
		
		
		for(Place place : forbidPlaces)
		{
			
			if(vertical == true)
			{
				if ( maxY >= place.y && minY <= place.y + 1 )
				{
					if ( eX == place.x || eX == place.x + 1 )
						return true;
				}
			}
			else
			{
				if ( maxX >= place.x && minX <= place.x + 1 ) // czy odcinek sie miesci
				{
					double poX = (((place.x+0.5)+a*(place.y+0.5))-a*b)/(a*a+1);
					double poY = (a*((place.x+0.5)+a*(place.y+0.5))+b)/(a*a+1);
					
					//double dist = Math.abs(a*(place.x+0.5)-(place.y+0.5)+b)/Math.sqrt(a*a+1);
					
					
					if(place.x <= poX  && place.x+1 >= poX &&
							place.y <= poY && place.y+1 >= poY) // czy punkt sie miesci
					{
						return true;
					}
					/*if( dist <= 0.5 )
						return true;
					if( dist < 0.8 ) // if dist < sqrt(2)
					{
						if(Math.abs(a*place.x + b - place.x+1) < 1 ||
								Math.abs((place.y-b)/a - place.y+1) < 1)
						{
							return true;	
						}	
					}*/
					
				}
				// sprawdz kolizje
			}
		}
		return false;
	}
	FindRoadAlg(int populationSize, int dataSize) {
		//utworz populacje o losowych sciezkach
		if(populationSize%2 != 0)
		{
			System.out.println("Warning: population size must be divisible by 2");
		}
		population = new Individual[populationSize];
		for( int i = 0; i < populationSize; ++i)
		{
			population[i] = new Individual(dataSize-1);
			population[i].Randomize();
		}
	}
	void setCities(City[] cities) {
		if(population[0] != null)
		{
			if(cities.length-1 == population[0].getSize())
			{
				// Jezeli sie zgadzaja rozmiary, podstaw nowe miasta i oblicz funkcje dopasowania
				this.cities = cities;
				for(int i = 0; i < population.length; ++i)
				{
					population[ i ].fitValue = 0;
					fitFunc(population[i]);
				}
				Arrays.sort(population);
			}
			else
			{
				System.out.println("Different count of cities and lenght of genotype");
			}
		}
		else
		{
			System.out.println("Population wasn't generated");
		}
	}
	double fitFunc(Individual ind)
	{
		//it's impossible to change genotype inside individual
		//and to create a new genotype we have to create new individual
		//which has always fitValue equals 0
		if(ind.fitValue == 0)
		{
			//returned value of function
			double ret = 0;
			
			//our constants
			final double C1 = 1.0;
			final double C2 = 1.0;
			
			int[] order = ind.getGenotype();
			
			//-------------------------------------------
			//computing distances between cities
			int oX=cities[0].x, oY=cities[0].y; // previous city point
			int nX = 0, nY = 0; // next city point
			
			double d = 0; // path length from start to end
			double[] disTable = new double[order.length]; // table of path length to the city from start position
			
			double actDis = 0; // distance between actual cities

			int collisionsCount = 0;
			for(int i = 0 ; i < order.length; i++)
			{
				
				//save next city position
				nX = cities[order[i]].x;  
				nY = cities[order[i]].y;
				
				actDis =  Math.sqrt((nX-oX)*(nX-oX)+(nY-oY)*(nY-oY)); //compute actual distance
				if ( CheckCollision( nX, nY, oX, oY ) ) {
					actDis = 100000000000.0;
					collisionsCount += 1;
				}
				d += actDis; // add to path length 
				disTable[i] = d; // save actual path length to table
				
				//in next iteration actual next city will be previous city
				oX = nX;
				oY = nY;
			}
			
			ret += C1*d;
			
			for(int i =order.length-1 ; i >= 0; --i)
			{
				ret += C2 * cities[ order[ i ] ].mass * disTable[ i ];
			}
			
			ind.fitValue = ret;
			ind.D = d;
			ind.collisionsCount = collisionsCount;
		}
		return ind.fitValue;
	}
	public void oneStep() {
//		System.out.println("======================================");
//		System.out.println("Step started");
		//Selection the best fitted individuals
		int halfPop = population.length/2; 
		for(int i = halfPop; i < population.length; ++i)
		{
			//Delete 
			population[i] = null;
		}
		//Crossing rest of indivduals
		for(int i = 0; i < halfPop-1; i+=2)
		{
			Individual[] childs = Crossovers.ordered(population[i], population[i+1]);
			if (childs == null)
			{
				System.out.println("Childs error");
			}
			else
			{
				//Put new childs in populations
				population[halfPop+i] = childs[0];
				population[halfPop+i+1] = childs[1];
			}
		}
		//Mutation of new population
		for(int i = halfPop; i < population.length; ++i) 
		{
			if (randmut.nextDouble() < (double)(1.00/halfPop)) // Average one mutation per generation
			{
				System.out.println("Mutation in  " + i);
				population[i] = Mutations.RSM(population[i]);
			}
		}
		
		// Calculate fit function for every individual
		for(int i = 0; i < population.length; ++i)
		{
			fitFunc(population[i]);
		}
		Arrays.sort(population);
		
		//Count mean fit for this generation
		mean_fit = 0;
		for(int i = 0; i < population.length; ++i)
			mean_fit += population[i].fitValue;
		
		mean_fit /= population.length;
		
		//Get the best fit
		best_fit = population[0].fitValue;

		//System.out.println( "Actaul result road: " + Arrays.toString( population[ 0 ].getGenotype() ) );

		//System.out.println("Shortest: " + population[0].fitValue);
		//System.out.println("Step finished");
	}
}
