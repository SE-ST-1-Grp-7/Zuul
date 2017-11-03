package worldofzuul.People;

import java.util.Random;
import worldofzuul.mapAndRooms.Room;

/**
 * A student class that extends person
 *
 * @author Robin & Niclas
 */
public class Student extends Person {

    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    private Room room;
    private int ogX;
    private int ogY;
    private int leash;

    /**
     * Constructor that gives a random name
     *
     * @param speed
     * @param x
     * @param y
     */
    public Student(int speed, int x, int y, Room room, int leash) {
        super(speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
        // original position of student - use for idle moving
        this.ogX = x;
        this.ogY = y;
        // the room the student resides in
        this.room = room; 
        this.leash = leash;
    }

    /**
     * This constructor makes a student that has a question for the Player the
     * student also has a name, speed and position
     *
     * @param name
     * @param speed
     * @param x
     * @param y
     */
    public Student(String name, int speed, int x, int y) {
        super(name, speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
    }

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
    public void move() {
        // step 1
        //roll if the student should move
        Random rand = new Random();
        if(rand.nextInt(10) != 1 ) {
            return; 
        }
        // step 2
        // roll student should move in the x or y axis
        if(rand.nextBoolean()) { // if true, move in the x axis
            // roll left or right
            if(rand.nextBoolean()) {
                // move right
                room.roomArray[getY()][getX()+1] = this;
                setX(getX()+1);
            }
            else {
                room.roomArray[getY()][getX()-1] = this;
                setX(getX()-1);
            }
        } else {
            
        }
    }
    // checks if a movement is can be made
    public boolean isLegal(int newX) {
        return true;
    }
}

