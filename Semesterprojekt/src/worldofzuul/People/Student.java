package worldofzuul.People;


import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.mapAndRooms.Room;

/**
 *A student class that extends person
 * @author Robin & Niclas & Rasmus Willer & Magnus Mortensen
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    private Image studentImage = new Image("/texture/student.png");

    public Student(int x, int y, int width, int height, Room currentRoom, BufferedImage graphics, Boolean hasQ){
        super(x, y, width, height, currentRoom); //a call to the super constructor
        super.setEntityImage(studentImage);
        this.hasQuestionToPlayer = hasQ; //the player has a question to the player
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
