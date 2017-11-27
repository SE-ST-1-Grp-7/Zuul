package business;

/**
 * Subclass of furniture, Plant class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Plant extends Furniture /*implements Interactable*/{

    /**
     * Constructor for Chair class.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath
     */
    public Plant(int x,
            int y,
            int width,
            int height,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Plant",            // Item name.
                "Looks so lovely");  // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    /**
     * Override, upon interaction with furniture.
     */
    @Override
    public void onInteract() {
        System.out.println("This is a plant");
        System.out.println("Looks lovely!");
    }
    
}
