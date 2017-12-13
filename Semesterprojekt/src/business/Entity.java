package business;

/**
 * Super class of all elements placed in the game that are not a fixed part of
 * the world.
 *
 * @author Group 7
 */
public abstract class Entity {
    // X and Y grid position in room.
    private int x, y;
    // Currently in this room.
    private Room currentRoom;
    // Path of texture of entity.
    private String entityImage;
    // ID number identifying the entity.
    private String id;

    /**
     * Constructor of Entity class. Instantiates entity with parameters.
     *
     * @param id            String, ID of specific instantiation.
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param currentRoom   Room, currently in this room.
     */
    public Entity(String id, int x, int y, Room currentRoom) {
        // Assign parameters to variables.
        this.id = id;
        this.x = x;
        this.y = y;
        this.currentRoom = currentRoom;
    }

    // GETTERS & SETTERS
    
    /**
     * Retrieve int value of X grid position in room.
     *
     * @return      int, coordinate to retrieve.
     */
    public int getX() {
        return x;
    }

    /**
     * Set int value of X grid position in room.
     *
     * @param x     int, new X position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieve int value of Y grid position in room.
     *
     * @return      int, coordinate to retrieve.
     */
    public int getY() {
        return y;
    }

    /**
     * Set int value of Y grid position in room.
     *
     * @param y     int, new Y position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the current room of entity.
     *
     * @return      Room, the current room of entity
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Set the current room of entity.
     *
     * @param currentRoom   Room, the room that the entity is in.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Get String with the path to the entity image.
     *
     * @return      String, with image path.
     */
    public String getEntityImage() {
        return entityImage;
    }

    /**
     * Set method used for setting image of entity.
     *
     * @param entityImage   String, path to image.
     */
    public void setEntityImage(String entityImage) {
        this.entityImage = entityImage;
    }

    /**
     * Retrieve ID.
     * 
     * @return      String, ID retrieved.
     */
    public String getID() {
        return id;
    }

    /**
     * Set ID to entity.
     * 
     * @param id    String, new ID to set.
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Upon interact with person. abstact method.
     * 
     * @param p     Person, whom is interacted with.
     */
    public abstract void onInteract(Person p);

}