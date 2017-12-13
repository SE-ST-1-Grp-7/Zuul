package business;


/**
 * Subclass of furniture, Table class.
 * 
 * @author Robin & Rasmus Willer
 */
public class Table extends Furniture /*implements Inspectable*/{
    
    /**
     * Constructor for Table class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         String, file path to texture.
     */
    public Table(String id,
            int x,
            int y,
            Room currentRoom,
            String imagePath){
        
        // Pass arguments for superclass
        super(id,
                x,
                y,
                currentRoom,
                // Item name.
                "Table",
                // Item description.
                "It might contain items!");
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
        
        // Parameters are assigned to attributes.
    }
    
    /**
     * Override; upon interaction with table.
     *
     * @param p     Person, whom is interacting with furniture.
     */
    @Override
    public void onInteract(Person p) {
    }
}
