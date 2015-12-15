package com.github.roadfinder.algortihm.api;

public interface PathSearcher {
	public class CitiesData {
		public short X,Y;
		public short mass_of_pack;
	};
	
	public void resetMap();
	public void addCitiesToMap(CitiesData[] data, short start_city);
	public void setMaxGeneration(int max);
	public void getMaxGeneration(int max);
		
	public void startSearch();
	public void stopSearch();
	public boolean hasFinished();
	
	public float[] getMinFitPlot();
	public float[] getMeanFitPlot();
	
	//This functions are for getting properties of actual generations
	public int[] getActBestPath();
	public float getActMaxFit();
	public float getActMeanFit();
	public int getActGeneration();	
};
