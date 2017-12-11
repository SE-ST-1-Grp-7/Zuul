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
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, path of texture.
     */
    public Chair(String id,
            int x,
            int y,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,                    // X grid position in room.
                y,                  // Y grid position in room.
                currentRoom,        // Placed in this room.
                "Chair",            // Item name.
                "Nice to sit on");  // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    /**
     * Override, upon interaction with furniture.
     * @param p                 //Player, effect of interacting with this.
     */
    @Override
    public void onInteract(Person p) {
        System.out.println("this is a chair");
        System.out.println("you cant sit on it though");
    }
    
}
