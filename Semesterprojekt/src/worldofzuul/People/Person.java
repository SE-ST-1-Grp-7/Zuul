package worldofzuul.People;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7, Robin & Niclas & soren
 */
public abstract class Person {
    private String name; // name for the person
    private int speed; // speed of the person
    private int x,y; // x and y coordinates for the person
    
    
    /**
     * a constructor that gives a random name
     * @param speed
     * @param x
     * @param y 
     */
    public Person(int speed, int x, int y){
        //the other constructor is called with these arguments
        this(getRandomName(), speed, x, y);
    }
    
    /**
     * constructor where you can specify the name, speed and position
     * @param name
     * @param speed
     * @param x
     * @param y 
     */
    public Person(String name, int speed, int x, int y){
        //the parameters are set equal to the attributes
        this.name = name;
        this.speed = speed;
        this.x = x;
        this.y = y;
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
     * @return 
     */
    public static String getRandomName(){
        String[] names = {"Niclas", "Rasmus", "Søren", "Robin","Jonas","Magnus","Frederik"};
        int index = (int) (Math.random() * names.length);
        return names[index];
    }
    
    /**
     * getter for x
     * @return 
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * getter for y
     * @return 
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * setter for x
     * @param x 
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * setter for y
     * @param y 
     */
    public void setY(int y){
        this.y = y;
    }
}

        
