package business;

/**
 * Super class of all elements placed in the game that are not a fixed part of
 * the world.
 * 
 * @author Group 7
 */
public abstract class Entity {
    protected int x, y;             // X and Y grid position in room.
    protected int width, height;    // Pixel width and height of entity.
    protected Room currentRoom;     // Placed in this room.
    protected String entityImage;   // Path of texture of entity.
    
    /**
     * Constructor of Entity class.
     * 
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param width         int, pixel width of coffee.
     * @param height        int, pixel height of coffee.
     * @param currentRoom   Room, currently in this room.
     */
    public Entity(int x, int y, int width, int height, Room currentRoom) {
        // Assign parameters to variables.
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
     * Retrieve int value of width of entity.
     * 
     * @return      int, width to retrieve.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set int value of width of entity.
     * 
     * @param width     int, new width of entity.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Retrieve int value of height of entity.
     * 
     * @return      int, height to retrieve.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set int value of height of entity.
     * 
     * @param height    int, new height of entity.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getEntityImage() {
        return entityImage;
    }

    public void setEntityImage(String entityImage) {
        this.entityImage = entityImage;
    }
    
    /**
     * abstact method - gets called when entity is interacted with
     */
    public abstract void onInteract();    
    
}
//    public BufferedImage getGraphics() {
//        return graphics;
//    }
//
//    public void setGraphics(BufferedImage graphics) {
//        this.graphics = graphics;
//    }
//}
