package business;


/**
 * Subclass of furniture, Table class.
 * 
 * @author Robin
 * @author Søren
 * @author Rasmus Willer
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
                currentRoom);
        // Pass path of texture to superclass.
        super.setEntityImage(imagePath);
        
        // Parameters are assigned to attributes.
    }
    
    /**
     * Override; upon interaction with table.
     * Can be used in the future for implementing furniture functionality.
     *
     * @param p     Person, whom is interacting with furniture.
     */
    @Override
    public void onInteract(Person p) {
    }
}