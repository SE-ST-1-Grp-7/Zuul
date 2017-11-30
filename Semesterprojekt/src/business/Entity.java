package business;

/**
 *
 * @author Group 7
 */
public abstract class Entity {
    protected int x, y;
    protected int width, height;
    protected Room currentRoom;
    protected String entityImage;
    
    public Entity(int x, int y, int width, int height, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.currentRoom = currentRoom;
    }
    
    // GETTERS & SETTERS
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

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
    public abstract void onInteract(Person p);    
    
}
//    public BufferedImage getGraphics() {
//        return graphics;
//    }
//
//    public void setGraphics(BufferedImage graphics) {
//        this.graphics = graphics;
//    }
//}
