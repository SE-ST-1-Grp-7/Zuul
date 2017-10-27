package worldofzuul.People;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7, Robin & Niclas & soren
 */
public abstract class Person {
    private String name;
    private int speed;
    
    
    public Person(int speed){
        this.name = getRandomName();
        this.speed = speed;
    }
    
    public Person(String name, int speed){
        this.name = name;
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
         /**
     * Randomly create a (predefined)name
     *
     * @return 
     */
    public String getRandomName(){
        String[] names = {"Niclas", "Rasmus", "Søren", "Robin","Jonas","Magnus","Frederik"};
        int index = (int) (Math.random() * names.length);
        return names[index];
    }
}

        
