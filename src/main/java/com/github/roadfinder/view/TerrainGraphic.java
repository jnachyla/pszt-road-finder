package com.github.roadfinder.view;

import com.github.roadfinder.algortihm.impl.City;
import com.github.roadfinder.algortihm.impl.Place;
import com.github.roadfinder.model.impl.TerrainData;
import com.github.roadfinder.model.utils.PointInterface;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by jnachyla on 2016-01-23.
 */
public class TerrainGraphic extends Group
{

    public static final int LINE_WIDTH = 5;

    Canvas canvas;
    private TerrainData terrainData;
    private Grid grid;

    public TerrainGraphic( TerrainData terrainData )
    {
        this.terrainData = terrainData;
        canvas = new Canvas( View.WIDTH, View.HEIGHT );

        drawTerrain();
        getChildren().add( canvas );
    }

    private GraphicsContext gc()
    {
        return canvas.getGraphicsContext2D();
    }

    private void drawTerrain()
    {
        grid = new Grid( terrainData.getSize() );
        grid.drawGrid();

        for ( City city : terrainData.getCities() ) {
            grid.drawPoint( city );
            grid.addPointLabel( city, String.valueOf( city.mass ) );
        }
        for ( Place place : terrainData.getForbidPlaces() ) {
            grid.drawPoint( place, Color.BLUE );
        }

        grid.markStartPoint( terrainData.getCities()[ 0 ] );
    }

    public void drawConnections( int[] order )
    {

        City[] cities = terrainData.getCities();
        if ( order.length != cities.length - 1 ) {
            return;
        }

        int start = 0;
        for ( int next : order ) {
            grid.connectPoints( cities[ start ], cities[ next ] );
            start = next;
        }
    }

    private class Grid
    {

        public static final int FONT_SIZE = 30;
        private static final int START_POS = 40;

        private final int offset;
        private final int size;

        Grid( int size )
        {
            this.size = size;
            this.offset = ( View.HEIGHT - START_POS ) / this.size;
        }

        void drawGrid()
        {
            GraphicsContext gc = gc();
            gc.setStroke( Color.BLUE );
            gc.setFont( new Font( FONT_SIZE ) );
            gc.setLineWidth( 1 );

            // Horizaontal lines
            for ( int i = START_POS; i < View.WIDTH; i = i + offset ) {
                gc.strokeLine( i, START_POS, i, View.HEIGHT );
                gc.fillText( String.valueOf( ( i - START_POS ) / offset + 1 ), i - 10, START_POS - 10 );
            }

            // Vertical lines
            for ( int i = START_POS; i < View.HEIGHT; i = i + offset ) {

                gc.strokeLine( START_POS, i, View.WIDTH, i );
                gc.fillText( String.valueOf( ( i - START_POS ) / offset + 1 ), START_POS - 30, i );
            }
        }

        void drawPoint( PointInterface point, Color color )
        {
            gc().setFill( color );
            int x = getCordinate( point.getX() );
            int y = getCordinate( point.getY() );
            int pointSize = point.getSize();

            gc().fillOval( x - pointSize / 2, y - pointSize / 2, pointSize, pointSize );

            gc().setFill( Color.GREEN );
        }

        void drawPoint( PointInterface point )
        {
            drawPoint( point, Color.GREEN );
        }

        void addPointLabel( PointInterface point, String label )
        {
            gc().setFont( new Font( 35 ) );
            int x = getCordinate( point.getX() );
            int y = getCordinate( point.getY() );

            gc().fillText( label, x + point.getSize() / 2, y + point.getSize() / 2 );
        }

        private int getCordinate( int gridCordinate )
        {
            int pixelCordinate = ( gridCordinate - 1 ) * offset + START_POS;
            return pixelCordinate;
        }

        void connectPoints( PointInterface p1, PointInterface p2 )
        {

            gc().setStroke( Color.RED );

            gc().setLineWidth( LINE_WIDTH );

            int p1X = getCordinate( p1.getX() );
            int p1Y = getCordinate( p1.getY() );
            int p2x = getCordinate( p2.getX() );
            int p2Y = getCordinate( p2.getY() );

            gc().strokeLine( p1X, p1Y, p2x, p2Y );
            gc().setStroke( Color.BLUE );
        }

        void markStartPoint( PointInterface point )
        {
            gc().setFill( Color.RED );
            int x = getCordinate( point.getX() );
            int y = getCordinate( point.getY() );
            int pointSize = 30;

            gc().fillOval( x - pointSize / 2, y - pointSize / 2, pointSize, pointSize );

            gc().fillText( "Start", x + point.getSize() / 2, y + ( point.getSize() + 20 ) / 2 );
        }
    }
}
