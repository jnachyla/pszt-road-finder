package com.github.roadfinder.model.api;

import com.github.roadfinder.algortihm.api.PathSearcher;
import com.github.roadfinder.model.impl.TerrainData;

/**
 * Created by Jarek on 2015-12-09.
 */
public interface Model {

    void setData( TerrainData data );
    TerrainData getData();
    PathSearcher getPathSearcher();
    float findRoad( int maxGeneration, int population_size );
}
