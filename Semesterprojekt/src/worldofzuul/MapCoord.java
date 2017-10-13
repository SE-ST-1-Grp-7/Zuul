/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Soren
 */
public class MapCoord{
    private int X;
    private int Y;

    public MapCoord() {
        this(0,0);
    }        
    public MapCoord(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    public int getMapX() {
        return X;
    }
    public int getMapY() {
        return Y;
    }
    public void setMapX(int X) {
        this.X = X;
    }
    public void setMapY(int Y) {
        this.Y = Y;
    }
}