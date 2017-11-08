package worldofzuul.People;

import worldofzuul.Link;
import worldofzuul.entities.Entity;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public abstract class Person extends Entity {
    protected static final float DEFAULT_SPEED = 5.0f;
    protected static final int DEFAULT_PERSON_WIDTH = 64,
                               DEFAULT_PERSON_HEIGHT = 64;
    protected float speed;
    protected String name; // Person name
    private Link link; // link handler
    
    public Person(Link link, float x, float y, int width, int height) {
        super(link, x, y, width, height);
        speed = DEFAULT_SPEED;
        name = getRandomName();
    }
    
    public Person(Link link, float x, float y, int width, int height, String name) {
        super(link, x, y, width, height);
        speed = DEFAULT_SPEED;
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
     * getter for the speed
     * @return 
     */
    public float getSpeed() {
        return this.speed;
    }
    
    /**
     * setter for the speed
     * @param speed 
     */
    public void setSpeed(float speed) {
        this.speed = speed;
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

        
