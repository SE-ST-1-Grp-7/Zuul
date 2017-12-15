package business;

/** Person class - superclass of Student and Player
 *
 * @author Niclas Johansen
 * @author Robin
 * @author Jonas
 * @author Rasmus Willer
 */
public abstract class Person extends Entity {
    // Define default values for size of person.
    private String name;
    private EntityManager em;
    
    /**
     * Person constructor, instantiate NPCs. Instantiates entity with
     * parameters.
     * 
     * @param id            String, ID of specific instantiation version.
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param currentRoom   Room, currently in this room.
     * @param em            EntityManager, used for persons giving items.
     */
    public Person(String id,
            int x,
            int y,
            Room currentRoom,
            EntityManager em) {
        // Pass arguments to superclass.
        super(id, x, y, currentRoom);
        
        this.em = em;
    }
    
    /**
     * Method for collision check.
     *
     * @param x     int, X coordinate of grid location to be checked.
     * @param y     int, Y coordinate of grid location to be checked.
     * @return      boolean, true if collision, false otherwise.
     */ 
    public boolean checkCollision(int x, int y) {
        if(getCurrentRoom().getTiles()[y][x].isSolid()) {
            return true;
        } else {
            return getCurrentRoom().getEntities()[y][x] != null;
        }
    }
    
    /**
     * Getter for name.
     * 
     * @return      String, name of person.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for name.
     * 
     * @param name  String, name of person.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retrieve entity manager.
     * 
     * @return      EntityManager, object to retrieve.
     */
    public EntityManager getEntityManager(){
        return this.em;
    }    
}