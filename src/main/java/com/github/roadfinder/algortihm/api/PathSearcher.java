package com.github.roadfinder.algortihm.api;


public interface PathSearcher  {

	public static class CitiesData {
		public int X,Y;
		public int mass_of_pack;
		public CitiesData(int X, int Y, int mass)
		{
			this.X = X;
			this.Y = Y;
			this.mass_of_pack = mass;
		}
	};
	public static class ForbidPlace {
		public short X,Y;
	}

	public void resetMap();

	public void setMaxGeneration(int max);

	public void startSearch();

	public void stopSearch();

	public boolean hasFinished();	

	public Float[] getMinFitPlot();

	public Float[] getMeanFitPlot();
	
	public int[] getActBestPath();

	public float getActMaxFit();

	public float getActMeanFit();

	public int getActGeneration();

	public int getMaxGeneration();

	public void setPopulationSize(int population_size);

	public int getPopulationSize();

	public void setCitiesToMap(CitiesData[] data, int start_city);
	
	public void setForbidPlaces(ForbidPlace[] data);
	
	// TODO Podczas dzia³aania algorytmu zmiany w³asciwoœæi sygnalizowaæ b³êdem
}
