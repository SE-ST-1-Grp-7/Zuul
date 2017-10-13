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
public class RoomCoord {

    private int X;
    private int Y;

    // no-arg constructor to set a standard room to 0,0 coordinates.
    public RoomCoord() {
        this(0, 0);
    }

    // x y constructor for room coordinates
    public RoomCoord(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    // getter for the rooms x-value
    public int getRoomX() {
        return X;
    }

    // getter for the rooms Y-value
    public int getRoomY() {
        return Y;
    }

    // setter for the rooms x-value
    public void setRoomX(int X) {
        this.X = X;
    }

    // setter for the rooms y-value
    public void setRoomY(int Y) {
        this.Y = Y;
    }
}
