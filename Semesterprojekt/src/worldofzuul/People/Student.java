package worldofzuul.People;

/**
 *A student class that extends person
 * @author Robin & Niclas
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    
    /**
     * Constructor that gives a random name
     * @param speed 
     * @param x 
     * @param y 
     */
    public Student(int speed, int x, int y){
        super(speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
    }
    
    
    /**
     * This constructor makes a student that has a question for the Player
     * the student also has a name, speed and position
     * @param name
     * @param speed
     * @param x
     * @param y
     */
    public Student(String name, int speed, int x, int y){
        super(name, speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
    }
    
    /**
     * getter for hasQusetionForPlayer
     * @return 
     */
    public boolean getHasQuestionToPlayer() {
        return this.hasQuestionToPlayer;
    }
    /**
     * setter for hasQusetionForPlayer
     * @param hasQuestionToPlayer 
     */
    public void setHasQuestionToPlayer(boolean hasQuestionToPlayer) {
        this.hasQuestionToPlayer = hasQuestionToPlayer;
    }
   
}
