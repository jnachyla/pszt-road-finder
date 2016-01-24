package com.github.roadfinder.algortihm.api;


import com.github.roadfinder.algortihm.impl.City;
import com.github.roadfinder.model.utils.PointInterface;

public interface PathSearcher  {

	public static class CityData
	{
		public int X,Y;
		public int mass_of_pack;

		public CityData( int X, int Y, int mass )
		{
			this.X = X;
			this.Y = Y;
			this.mass_of_pack = mass;
		}
	};

	public static class ForbidPlace implements PointInterface
	{

		public int X, Y;

		public int getX()
		{
			return X;
		}

		public int getY()
		{
			return Y;
		}

		public int getSize()
		{
			return 30;
		}
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

	public void setCitiesToMap( CityData[] data, int start_city );

	public void setCitiesToMap( City[] data, int start_city );

	public void setForbidPlaces( PointInterface[] data );

	// TODO Podczas dzia³aania algorytmu zmiany w³asciwoœæi sygnalizowaæ b³êdem
}
