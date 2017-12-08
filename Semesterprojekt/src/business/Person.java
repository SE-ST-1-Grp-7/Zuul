package business;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public abstract class Person extends Entity {
    // Define default values for size of person.
    protected static final int DEFAULT_PERSON_WIDTH = 64,
                               DEFAULT_PERSON_HEIGHT = 64;
    protected String name; // Person name
    private EntityManager em;
    /**
     * Person 1st constructor, without given name.
     * 
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param width         int, pixel width of person.
     * @param height        int, pixel height of person.
     * @param currentRoom   Room, currently in this room.
     * @param em
     */
    public Person(String id, int x, int y, int width, int height, Room currentRoom, EntityManager em) {
        // Pass arguments to superclass.
        super(id, x, y, width, height, currentRoom);
        // Get random name as name for person.
        //name = getRandomName();
        this.em = em;
    }
        /**
     * Method for collision check.
     *
     * @param x int, X coordinate of grid location to be checked.
     * @param y int, Y coordinate of grid location to be checked.
     * @return boolean, true if collision, false otherwise.
     */ 
    public boolean checkCollision(int x, int y) {
        if(getCurrentRoom().getTiles()[y][x].isSolid()) {
            return true;
        } else {
            return getCurrentRoom().getEntities()[y][x] != null;
        }
    }
    
    /**
     * Person 2nd constructor, with given name.
     * 
     * @param x             int, horizontal position in room grid.
     * @param y             int, vertical position in room grid.
     * @param width         int, pixel width of person.
     * @param height        int, pixel height of person.
     * @param currentRoom   Room, currently in this room.
     * @param name          String, name of person.
     */
    public Person(String id,
            int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            String name, EntityManager em) {
        // Pass arguments to superclass.
        super(id, x, y, width, height, currentRoom);
        // Assign given name.
        this.name = name;
        this.em = em;
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
     * Generate a (predefined) random name.
     * 
     * @return      String, generated name of person.
     */
    public static String getRandomName(){
        // List of random names to choose from.
        String[] names = {"Niclas",
            "Rasmus",
            "Søren",
            "Robin",
            "Jonas",
            "Magnus",
            "Frederik"};
        // Random number for index selection.
        int index = (int) (Math.random() * names.length);
        // Return chosen String name.
        return names[index];
    }
    
    public EntityManager getEntityManager(){
        return this.em;
    }
}

        
