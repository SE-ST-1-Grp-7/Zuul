package business;

/**
 * Subclass of furniture, Chair class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Chair extends Furniture /*implements Interactable*/{
    private String chairImage = "/textures/chair1.png";  

    /**
     * Constructor for Chair class.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     */
    public Chair(int x,
            int y,
            int width,
            int height,
            Room currentRoom){
            
        // Pass arguments to superclass.
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Chair",            // Item name.
                "Nice to sit on");  // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(chairImage);
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
