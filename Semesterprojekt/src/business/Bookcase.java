package business;

/**
 * Subclass of furniture, Bookcase class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Bookcase extends Furniture /*implements Interactable*/{

    /**
     * Constructor for Chair class.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     */
    public Bookcase(String id,
            int x,
            int y,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,                    // X grid position in room.
                y,                  // Y grid position in room.
                currentRoom,        // Placed in this room.
                "Bookcase",         // Item name.
                "full of stuff");   // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
    }
    
    /**
     * Override, upon interact with bookcase.
     */
    @Override
    public void onInteract(Person p) {
        System.out.println("this is a bookcase!");
        System.out.println("you can rumage through its contents");
        
    }
}
