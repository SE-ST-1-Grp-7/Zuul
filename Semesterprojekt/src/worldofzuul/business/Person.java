package worldofzuul.business;


import worldofzuul.business.Entity;
import worldofzuul.business.Room;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public abstract class Person extends Entity {
    protected static final int DEFAULT_PERSON_WIDTH = 64,
                               DEFAULT_PERSON_HEIGHT = 64;
    protected String name; // Person name
    
    public Person(int x, int y, int width, int height, Room currentRoom) {
        super(x, y, width, height, currentRoom);
        name = getRandomName();
    }
    
    public Person(int x, int y, int width, int height, Room currentRoom, String name) {
        super(x, y, width, height, currentRoom);
        this.name = name;
    }
    
    /**
     * getter for the name
     * @return 
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * setter for the name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Randomly create a (predefined)name
     * @return 
     */
    public static String getRandomName(){
        String[] names = {"Niclas", "Rasmus", "Søren", "Robin","Jonas","Magnus","Frederik"};
        int index = (int) (Math.random() * names.length);
        return names[index];
    }
    
}

        
