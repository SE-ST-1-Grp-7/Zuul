/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.mapAndRooms;

/**
 *
 * @author Søren Bendtsen
 */
public class MapCoord {

    private int X;
    private int Y;

    // no-arg constructor to set a standard map to 0,0 coordinates.
    public MapCoord() {
        this(0, 0);
    }

    // x y constructor for map coordinates
    public MapCoord(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    // getter for the maps x-value
    public int getMapX() {
        return X;
    }

    // getter for the map y-value
    public int getMapY() {
        return Y;
    }

    // setter for the map x-value
    public void setMapX(int X) {
        this.X = X;
    }

    // setter for the rooms y-value
    public void setMapY(int Y) {
        this.Y = Y;
    }
}
