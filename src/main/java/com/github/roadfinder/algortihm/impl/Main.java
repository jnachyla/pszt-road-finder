package com.github.roadfinder.algortihm.impl;

import java.util.ArrayList;
import java.util.Random;
public class Main {
	public static int[] RandPermutation(int p, int j)
	{
		int[] elements = new int[j-p+1];
		Random random = new Random();
		ArrayList<Integer>  list = new ArrayList<Integer>(); 
		for(int i = p; i <= j; ++i)
		{
			list.add(i);
		}
		for(int i = 0; i <= j-p; ++i)
		{
			int index = random.nextInt(list.size());
			elements[i] = list.get(index);
			list.remove(index);
		}
		return elements;
	}
	public static class Interval {
		public int start, end;
		Interval() { };
	};
	public static Interval RandInterval(int s, int e)
	{
		Random random = new Random();
		int x= random.nextInt(e-s)+s,y=random.nextInt(e-s)+s;
		Interval inter = new Interval();
		if(x < y)
		{
			inter.start = x;
			inter.end = y;
		}
		else
		{
			inter.start = y;
			inter.end = x;
		}
		return inter;
	}
	public static void main(String[] args) {
		int[] el = RandPermutation(1,30);
		for(int i = 0; i < el.length; ++i)
			System.out.print(el[i] + " ");
		System.out.println("");
		Interval inter = RandInterval(0, 30);
		System.out.println("S: " + inter.start + " E: " + inter.end);

		Individual parent1 = new Individual(10);
		parent1.Randomize();

		System.out.println("Rodzice:");
		System.out.println(parent1);
		Individual parent2 = new Individual(10);
		parent2.Randomize();
		System.out.println(parent2);

		Individual[] childs = Crossovers.ordered(parent1, parent2);
		System.out.println("Dzieci:");
		System.out.println(childs[0]);
		System.out.println(childs[1]);

		Individual mut1 = Mutations.RSM(parent1), mut2 = Mutations.RSM(parent1);
		System.out.println("Mutacje:");
		System.out.println(mut1);
		System.out.println(mut2);


		City[] cities = new City[7];
		cities[0] = new City(0,0, 1);
		cities[1] = new City(3,0, 3);
		cities[2] = new City(3,3, 1);
		cities[3] = new City(0,3, 10);
		//cities[4] = new City(0,1, 2);
		cities[4] = new City(1,0, 3);
		cities[5] = new City(3,1, 2);
		cities[6] = new City(1,3, 2);
		FindRoadAlg alg = new FindRoadAlg( 10, cities.length );

		alg.setCities(cities);
		//alg.AddForbidPlace(0, 1);

		//Testy kolizji

		alg.AddForbidPlace(2, 4);
		alg.AddForbidPlace(3, 5);
		System.out.println("Collsion test: ");
		System.out.println("Expect 1: " + alg.CheckCollision(2, 1 , 2, 5));
		System.out.println("Expect 1: " + alg.CheckCollision(1, 4 , 5, 4));
		System.out.println("Expect 0: " + alg.CheckCollision(1, 3 , 5, 4));
		System.out.println("Expect 1: " + alg.CheckCollision(5, 5 , 3, 7));
		System.out.println("Expect 0: " + alg.CheckCollision(1, 2 , 6, 6));
		System.out.println("Expect 0: " + alg.CheckCollision(1, 2 , 6, 5));
		System.out.println("Expect 0: " + alg.CheckCollision(1, 7 , 6, 6));
		System.out.println("Expect 1: " + alg.CheckCollision(4, 1 , 3, 7));
		for(int i = 0; i < 3000; ++i)
			alg.oneStep();
		System.out.println( "Wykonano kroki fit: " + alg.population[ 0 ].fitValue );

/*		System.out.println("Testy klasy PathSearcherGenetic :");

		PathSearcherGenetic pathSer = new PathSearcherGenetic();
		CitiesData[] citiesD = new PathSearcherGenetic.CitiesData[7];
		citiesD[0] = new PathSearcherGenetic.CitiesData(0,0, 1);
		citiesD[1] = new PathSearcherGenetic.CitiesData(3,0, 3);
		citiesD[2] = new PathSearcherGenetic.CitiesData(3,3, 1);
		citiesD[3] = new PathSearcherGenetic.CitiesData(0,3, 10);
		//cities[4] = new City(0,1, 2);
		citiesD[4] = new PathSearcherGenetic.CitiesData(1,0, 3);
		citiesD[5] = new PathSearcherGenetic.CitiesData(3,1, 2);
		citiesD[6] = new PathSearcherGenetic.CitiesData(1,3, 2);
		pathSer.setCitiesToMap(citiesD, 0);
		pathSer.setMaxGeneration(100);
		pathSer.setPopulationSize(20);
		pathSer.startSearch();
		System.out.println("Poczatek obliczen");
		while(!pathSer.hasFinished());
		System.out.println("Koniec obliczen");
		System.out.println("Wynik: " + pathSer.getActMaxFit());*/

	}

	private static class CitiesData
	{

	}
}
