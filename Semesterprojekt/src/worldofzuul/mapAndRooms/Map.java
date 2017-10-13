/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.mapAndRooms;

/**
 *
 * @author niclasjohansen
 */
public class Map {
    MapCoord mapcords = new MapCoord();
    private int item;
    private int exit;
    private String description;
    private boolean locked;
    
    public Map(){
        
    }

    public Map() {

    }

    public int item() {
        return this.item;
    }

    public int exit() {
        return this.exit;
    }

    public String description() {
        return this.description;
    }

    public boolean locked() {
        return this.locked;

    }
    public int item(){
        return this.item;
    }
    
    public int exit(){
        return this.exit;
    }
    
    public String description(){
        return this.description;
    }
    
    public boolean locked() {
        return this.locked;
    }

}
