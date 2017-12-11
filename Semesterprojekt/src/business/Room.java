package business;


// Importing standard libraries for managing datastructures.
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
    private String minimapPath;

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
        exits = new HashMap<>();
        fillTenTenArray(tileArray);
         entityArray = new Entity[10][10];
        fillTenTenArray(entityArray);
        this.minimapPath = "/assets/Minimap/"+ this.getName() + ".png";
    }

    /**
     * Fill tile 2D array with nulls. Fail safe method for ensuring the fields
     * are empty.
     * 
     * @param list      Tile[][], 2D list to be set to empty (nulls).
     */
    private void fillTenTenArray(Tile[][] list) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                list[i][j] = null;
            }
        }
    }
    
    /**
     * Fill entity 2D array with nulls. Fail safe method for ensuring the fields
     * are empty.
     * 
     * @param list      Entity[][], 2D list to be set to empty (nulls).
     */
        private void fillTenTenArray(Entity[][] list) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                list[i][j] = null;
            }
        }
    }

    /**
     * Return if there is item in a certain grid position.
     * 
     * @param x     int, X coordinate in room grid.
     * @param y     int, Y coordinate in room grid.
     * @return      boolean, true if there is an item, otherwise false.
     */
    public boolean hasLoot(int x, int y) {
        if (entityArray[y][x] != null) {
            return entityArray[y][x] instanceof Item;
        }
        return false;
    }
    
    /**
     * Remove entity from its current location.
     * 
     * @param entity    Entity, to be removed.
     */
    public void removeEntity (Entity entity) {
        entityArray[entity.getY()][entity.getX()] = null;
    }
    
    // SETTERS & GETTERS
    
    /**
     * Method for designating direction of the exit point. A direction and a
     * neighboring room is passed as arguments to the method, that are then
     * added to the 'exits' map-object.
     *
     * @param direction String, describing a exit direction.
     * @param neighbor  Room, object of neighboring room will be paired with
     *                  description.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Getter method for instance of room's description.
     *
     * @return      String, description of room instance.
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * Retrieve name of room.
     * 
     * @return      String, name of room.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for extended description of room with room descriptions and
     * exit-waypoints from room.
     *
     * @return      String, description and exit-description.
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Getter method for extended description of student with room description.
     *
     * @return      String, student location description.
     */
    public String getStudentDescription() {
        return "Student is " + description;
    }

    /**
     * Getter method for String description of exit routes from room instance.
     *
     * @return      String, describes exit routes from room.
     */
    private String getExitString() {
        // Declare returning String + start of String message.
        String returnString = "Exits:";

        // Declare and assign a Set object 'keys' with all 'exits' Strings.
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
     * @param direction     String, describes direction of other room.
     * @return              Room, teturn corresponding Room object reference.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Getter method, retrieve HashMap of exit points from current room.
     * 
     * @return      HashMap< String, Room >, map of exit point data.
     */
    public HashMap<String, Room> getExits() {
        return this.exits;
    }

    /**
     * Place tile in room grid.
     * 
     * @param x         int, X coordinate in room grid.
     * @param y         int, Y coordinate in room grid.
     * @param tile      Tile, tile object to be placed in room.
     */
    public void setTile(int x, int y, Tile tile) {
        tileArray[y][x] = tile;
    }
    
    /**
     * Retrieve tile list.
     * 
     * @return      Tile[][], 2D list of tile objects in room.
     */
    public Tile[][] getTiles() {
        return this.tileArray;
    }
    
    /**
     * Set the entity to be shown on the GUI.
     * 
     * @param entity    Entity, to be placed in entity array of room.
     */
    public void setEntity (Entity entity) {
        entityArray[entity.getY()][entity.getX()] = entity;
    }
    
    /**
     * Place entity at new grid designated location.
     * 
     * @param x         int, X coordinate in room grid.
     * @param y         int, Y coordinate in room grid.
     * @param entity    Entity, to be placed at certain location in room.
     */
    public void setEntityWithXY(int x, int y, Entity entity){
        entityArray[y][x] = entity;
    }
    
    /**
     * Retrieve entity array.
     * 
     * @return      Entity[][], 2D list with the room's entities.
     */
    public Entity[][] getEntities() {
        return this.entityArray;
    }
    
    /**
     * Retrieve file path for minimap.
     * 
     * @return      String, path of minimap file.
     */
    public String getMinimapPath(){
        return this.minimapPath;
    }
}
