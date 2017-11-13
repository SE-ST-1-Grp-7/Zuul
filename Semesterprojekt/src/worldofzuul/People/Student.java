package worldofzuul.People;


import java.awt.Graphics;

/**
 *A student class that extends person
 * @author Robin & Niclas & Rasmus Willer
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player

    public Student(float x, float y, int width, int height){
        super(x, y, width, height); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
    }

    // GAME LOOP METHODS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
    // GETTERS & SETTERS
    
    /**
     * getter for hasQusetionForPlayer
     *
     * @return
     */
    public boolean getHasQuestionToPlayer() {
        return this.hasQuestionToPlayer;
    }

    /**
     * setter for hasQusetionForPlayer
     *
     * @param hasQuestionToPlayer
     */
    public void setHasQuestionToPlayer(boolean hasQuestionToPlayer) {
        this.hasQuestionToPlayer = hasQuestionToPlayer;
    }
}
