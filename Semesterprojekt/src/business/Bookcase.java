package business;

/**
 * Subclass of furniture, Bookcase class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Bookcase extends Furniture /*implements Interactable*/{

    /**
     * Constructor for Chair class.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, file path of texture.
     */
    public Bookcase(String id,
            int x,
            int y,
            Room currentRoom, String imagePath){
            
        // Pass arguments to superclass.
        super(id,
                x,
                y,
                // Placed in this room.
                currentRoom,
                // Item name.
                "Bookcase",
                // Item description
                "full of stuff");
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
