package business;

// importing standard libraries for managing datastructures.
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

/**
 *
 * @author Rasmus Willer & Jonas
 */
public class Room {

    // instantiates room coordinates to set and get.
    // Declare attributes -section.
    private String description;
    private String name;
    private Tile[][] tileArray = new Tile[10][10];
    private Entity[][] entityArray;
    // Map type (key/value -pairs data structure)
    private HashMap<String, Room> exits;

    /**
     * One-arg constructor with description String.
     *
     * @param description Passed string argument providing room description.
     * @param name
     */
    public Room(String description, String name) {
        // Assigning description argument to instance attribute.
        this.description = description;
        this.name = name;
        /* Instantiating map object with key of String type and
           value as Room object. */
        exits = new HashMap<String, Room>();
        fillTenTenArray(tileArray);
         entityArray = new Entity[10][10];
        fillTenTenArray(entityArray);
    }

    /**
     * Method for designating direction of the exit point. A direction and a
     * neighboring room is passed as arguments to the method, that are then
     * added to the 'exits' map-object.
     *
     * @param direction String describing a exit direction.
     * @param neighbor Room object of neighboring room will be paired with
     * description
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Getter method for instance of room's description.
     *
     * @return Returns String of description of room instance.
     */
    public String getShortDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    /**
     * Getter method for extended description of room with room descriptions and
     * exit-waypoints from room.
     *
     * @return Returns String with description and exit-description.
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Getter method for extended description of room with room descriptions and
     * exit-waypoints from room.
     *
     * @return Returns String with description and exit-description.
     */
    public String getStudentDescription() {
        return "Student is " + description;
    }

    /**
     * Getter method for String description of exit routes from room instance.
     *
     * @return Returns String describing exit routes from room.
     */
    private String getExitString() {
        // Declare returning String + start of String message.
        String returnString = "Exits:";

        // Declare and assign a Set object 'keys' with all 'exits' Strings
        Set<String> keys = exits.keySet();

        // Add to returning String a list of exit points.
        for (String exit : keys) {
            returnString += " " + exit;
        }
        // Return structured String.
        return returnString;
    }

    /**
     * Getter method, retrieve Room reference for chosen direction. The chosen
     * direction is passed to this method and the corresponding room reference
     * is returned.
     *
     * @param direction String describing direction of other room.
     * @return Return corresponding Room object reference.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public HashMap<String, Room> getExits() {
        return this.exits;
    }

    private void fillTenTenArray(Tile[][] list) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                list[i][j] = null;
            }
        }
        /*Assignment a = new Assignment();
        a.setX(3);
        a.setY(0);
        itemsInRoom.add(a);
        Assignment b = new Assignment();
        b.setX(3);
        b.setY(1);
        itemsInRoom.add(b);
        for (Item i : itemsInRoom) {
            roomArray[i.getY()][i.getX()] = i;
        }*/
    }
        private void fillTenTenArray(Entity[][] list) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                list[i][j] = null;
            }
        }
        /*Assignment a = new Assignment();
        a.setX(3);
        a.setY(0);
        itemsInRoom.add(a);
        Assignment b = new Assignment();
        b.setX(3);
        b.setY(1);
        itemsInRoom.add(b);
        for (Item i : itemsInRoom) {
            roomArray[i.getY()][i.getX()] = i;
        }*/
    }

    public boolean hasLoot(int x, int y) {
        if (entityArray[y][x] != null) {
            return entityArray[y][x] instanceof Item;
        }
        return false;

    }

    public void setTile(int x, int y, Tile tile) {
        tileArray[y][x] = tile;
    }
    
    public Tile[][] getTiles() {
        return this.tileArray;
    }
    
    /**
     * this method will set the entity to be shown on the GUI
     * @param entity 
     */
    public void setEntity (Entity entity) {
        entityArray[entity.getY()][entity.getX()] = entity;
    }
    
    /**
     * Remove entity from its current location.
     * 
     * @param entity    Entity, to be removed.
     */
    public void removeEntity (Entity entity) {
        entityArray[entity.getY()][entity.getX()] = null;
    }
    
    /**
     * this method is mostly for deleting the player from the room he is leaving
     * @param x
     * @param y
     * @param entity 
     */
    public void setEntityWithXY(int x, int y, Entity entity){
        entityArray[y][x] = entity;
    }
    
    public Entity[][] getEntities() {
        return this.entityArray;
    }
}
