package worldofzuul.People;
import worldofzuul.Inventory;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7, Robin & Niclas & soren
 */
public abstract class Person {
    private String name;
    private String gender;
    private int speed;
    Inventory inventory = new Inventory();
    
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
     * getter for the gender
     * @return 
     */
    public String getGender() {
        return this.gender;
    }
    /**
     * setter for the gender
     * @param gender 
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * getter for the speed
     * @return 
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * setter for the speed
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    } 
}

        
