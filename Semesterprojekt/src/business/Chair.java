package business;

/**
 * Subclass of furniture, Chair class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Chair extends Furniture {

    /**
     * Constructor for Chair class.
     * 
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, path of texture.
     */
    public Chair(int x,
            int y,
            int width,
            int height,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(x,                    // X grid position in room.
                y,                  // Y grid position in room.
                width,              // Pixel width.
                height,             // Pixel height.
                currentRoom,        // Placed in this room.
                "Chair",            // Item name.
                "Nice to sit on");  // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    /**
     * Override, upon interaction with furniture.
     */
    @Override
    public void onInteract() {
        System.out.println("this is a chair");
        System.out.println("you cant sit on it though");
    }
    
}
