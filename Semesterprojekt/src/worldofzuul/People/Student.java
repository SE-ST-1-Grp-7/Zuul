package worldofzuul.People;


import java.awt.Graphics;
import worldofzuul.Link;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import worldofzuul.PrintOut;
import worldofzuul.mapAndRooms.Room;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.userCommand.Command;
import worldofzuul.userCommand.CommandWord;

/**
 *A student class that extends person
 * @author Robin & Niclas & Rasmus Willer
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player

    public Student(Link link, float x, float y, int width, int height){
        super(link, x, y, width, height); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
        // original position of student - use for idle moving
        /*this.ogX = x;
        this.ogY = y;
        // the room the student resides in
        this.room = room;
        this.leash = leash;
//        this.rm = rm;
        place();
        System.out.println(room.getStudentDescription());*/
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
