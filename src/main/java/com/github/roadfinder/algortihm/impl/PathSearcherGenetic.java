package com.github.roadfinder.algortihm.impl;

import com.github.roadfinder.algortihm.api.PathSearcher;
import com.github.roadfinder.model.utils.PointInterface;

import java.util.Vector;

public class PathSearcherGenetic implements PathSearcher {

	//WTF???
	private City toCity( CityData data )
	{
		City city = new City(data.X, data.Y, data.mass_of_pack);
		return city;
	}

	public PathSearcherGenetic() {
		
	}

	public void resetMap() {
		citiesData = null;
		forbidData = null;
	}

	public void setMaxGeneration(int max) {
		maxGeneration = max;
	}

	public void startSearch() {
		if(algThread != null)
		{
			//Try to stop thread
			stopSignal = true;
			try {
				algThread.join();
			} catch (InterruptedException e) {
				System.out.println("Nie powinno sie zdarzyc");
				e.printStackTrace();
			}
		}
		stopSignal = false;
		
		finder = new FindRoadAlg(populationSize, citiesData.length);
		
		City[] citiesToAlg = new City[citiesData.length];
		for(int i = 0; i < citiesData.length; ++i)
		{
			citiesToAlg[i] = toCity(citiesData[i]);
		}
		
		finder.setCities(citiesToAlg);
		
		
		if(forbidData != null) 
		{
			for(int i = 0; i < forbidData.length; ++i)
			{
				finder.AddForbidPlace( forbidData[ i ].getX(), forbidData[ i ].getY() );
			}
		}
		
		algThread = new Thread(new AlgThread());
		algThread.start();
	}

	public void stopSearch() {
		if(algThread != null)
		{
			stopSignal = true;
			try {
				algThread.join();
			} catch (InterruptedException e) {
				System.out.println("Nie powinno sie zdarzyc");
				e.printStackTrace();
			}
			algThread = null;
		}
	}

	public boolean hasFinished() {
		if(algThread == null)
			return false;
		else
			return !algThread.isAlive();
	}

	public Float[] getMinFitPlot() {
		return (Float[])minPlotData.toArray();
	}

	public Float[] getMeanFitPlot() {
		return (Float[])meanPlotData.toArray();
	}

	public int[] getActBestPath() {
		synchronized(finder) {
			return finder.population[0].getGenotype();
		}
	}

	public float getActMaxFit() {
		synchronized(finder) {
			return (float) finder.best_fit;
		}
	}

	public float getActMeanFit() {
		synchronized(finder) {
			return (float) finder.mean_fit;
		}
	}

	public int getActGeneration() {
		return actGeneration;
	}

	public int getMaxGeneration() {
		return maxGeneration;
	}

	public void setPopulationSize(int population_size) {
		populationSize = population_size;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setCitiesToMap( CityData[] data, int start_city )
	{
		citiesData = data.clone();
		if(start_city != 0)
		{
			CityData temp = citiesData[ start_city ];
			citiesData[start_city] = citiesData[0];
			citiesData[0] = temp;
		}
	}

	public void setCitiesToMap( City[] data, int start_city )
	{
		//TODO pozbyc sie klasy CitiesData
		citiesData = new CityData[ data.length ];

		for ( int i = 0; i < data.length; i++ ) {
			CityData cityData = new CityData( data[ i ].x, data[ i ].y, data[ i ].mass );
			citiesData[ i ] = cityData;
		}

		if ( start_city >= 0 ) {
			CityData temp = citiesData[ start_city ];
			citiesData[ start_city ] = citiesData[ 0 ];
			citiesData[ 0 ] = temp;
		}
	}

	public void setForbidPlaces( PointInterface[] data )
	{
		forbidData = data.clone();
	}

	public void setForbidPlaces(ForbidPlace[] data)
	{
		forbidData = data.clone();
	}
	private class AlgThread implements Runnable {
		public void run() {
			for(actGeneration = 0; actGeneration < maxGeneration; ++actGeneration) {
				synchronized(finder) 
				{
					finder.oneStep();
					minPlotData.add((float)finder.best_fit);
					meanPlotData.add((float)finder.mean_fit);
				}
				if(stopSignal)
				{
					stopSignal = false;
					break;
				}
			}
		}
	}
	private Vector<Float> minPlotData  = new Vector<Float>();
	private Vector<Float> meanPlotData= new Vector<Float>();
	private CityData[] citiesData = null;
	private PointInterface[] forbidData = null;
	private int maxGeneration  = 100;
	private int actGeneration = 0;
	private int populationSize = 20;
	private boolean stopSignal = false;
	
	private FindRoadAlg finder = null;
	private Thread algThread = null;
	// TODO Podczas dzia³aania algorytmu zmiany w³asciwoœæi sygnalizowaæ b³êdem
}
