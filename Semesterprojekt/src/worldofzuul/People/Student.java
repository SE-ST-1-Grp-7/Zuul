package worldofzuul.People;

/**
 *
 * @author Robin & Niclas
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    
    /**
     * Constructor that gives a random name

     * @param speed 
     */
    public Student(int speed, int x, int y){
        super(speed, x, y);
        hasQuestionToPlayer = true;
    }
    
    
    /**
     * This constructor makes a student that has a question for the Player
     * the student also has a name, age, gender and speed
     * @param name
     * @param speed
     * @param x
     * @param y
     */
    public Student(String name, int speed, int x, int y){
        super(name, speed, x, y);
        hasQuestionToPlayer = true;
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
