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
    protected float xMove, yMove;
    protected String name; // Person name
    
    public Person(Link link, float x, float y, int width, int height) {
        super(link, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
        name = getRandomName();
    }
    
    public Person(Link link, float x, float y, int width, int height, String name) {
        super(link, x, y, width, height);
        speed = DEFAULT_SPEED;
        this.name = name;
    }
    
    // MOVEMENT
    
    public void move() {
        moveX();
        moveY();
    }
    
    public void moveX() {
        /* Setting up if-else to give options for implementing simple collision
           detection later on. Even though it will appear like redundancy as it
           is in current state */
        
        // Move right
        if (xMove > 0) {
            x += xMove;
        } else if (xMove < 0) { // Move left
            x += xMove;
        }
        
    }
    
    public void moveY() {
        /* Setting up if-else to give options for implementing simple collision
           detection later on. Even though it will appear like redundancy as it
           is in current state */
        
        // Move down
        if (yMove < 0) {
            y += yMove;
        } else if (yMove > 0) { // Move up
            y += yMove;
        }
    }
    
    // GETTERS & SETTERS
    
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
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

        
