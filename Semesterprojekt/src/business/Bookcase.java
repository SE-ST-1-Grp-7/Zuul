package business;

/**
 * Subclass of furniture, Bookcase class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Bookcase extends Furniture /*implements Interactable*/{
    private String bookcaseImage = "/texture/bookcase1.png";  

    /**
     * Constructor for Chair class.
     * @param x                 int, horizontal postition in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of chair.
     * @param height            int, pixel height of chair.
     * @param currentRoom       Room, currently in this room.
     */
    public Bookcase(int x,
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
                "Bookcase",            // Item name.
                "full of stuff");  // Item description.
        // Pass path of texture to superclass.
        super.setEntityImage(bookcaseImage);
    }
    @Override
    public void onInteract() {
        System.out.println("this is a bookcase!");
        System.out.println("you can rumage through its contents");
        
    }
}
