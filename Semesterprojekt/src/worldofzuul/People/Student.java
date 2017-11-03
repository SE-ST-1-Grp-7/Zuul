package worldofzuul.People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import worldofzuul.PrintOut;
import worldofzuul.mapAndRooms.Room;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.userCommand.Command;
import worldofzuul.userCommand.CommandWord;

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
    private RoomManager rm;

    /**
     * Constructor that gives a random name
     *
     * @param speed
     * @param x
     * @param y
     */
    public Student(int speed, int x, int y, Room room, int leash, RoomManager rm) {
        super(speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
        // original position of student - use for idle moving
        this.ogX = x;
        this.ogY = y;
        // the room the student resides in
        this.room = room;
        this.leash = leash;
        this.rm = rm;
        place();
        move();
        System.out.println("x");
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
    public Student(String name, int speed, int x, int y, RoomManager rm) {
        super(name, speed, x, y); //a call to the super constructor
        hasQuestionToPlayer = true; //the player has a question to the player
        this.rm = rm;
        place();
        move();
        System.out.println("y");
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

    public void place() {
        room.roomArray[getY()][getX()] = this;
    }

    public void move() {
        // Step 1 to fase 1
        //roll if the student should move
        Random rand = new Random();
//        if(rand.nextInt(10) != 11 ) {
//            return; 
//        }
        System.out.println(room.getStudentDescription());
        Command c;
        switch (rand.nextInt(3)) {
            case 0:
                if (room.getExit("east") != null) {
                    room.roomArray[getY()][getX()] = null;
                    room = room.getExit("east");
                    place();
                }
                break;
            case 1:
                if (room.getExit("south") != null) {
                    room.roomArray[getY()][getX()] = null;
                    room = room.getExit("south");
                    place();
                }
                break;
            case 2:
                if (room.getExit("west") != null) {
                    room.roomArray[getY()][getX()] = null;
                    room = room.getExit("west");
                    place();
                }
                break;
            case 3:
                if (room.getExit("north") != null) {
                    room.roomArray[getY()][getX()] = null;
                    room = room.getExit("north");
                    place();
                }
                break;
            default:
                place();
                break;

        }
        // Step 2
        // Roll what room the student should go to.
//        // step 2
//        // roll student should move in the x or y axis
//        if(rand.nextBoolean()) { // if true, move in the x axis
//            // roll left or right
//            if(rand.nextBoolean()) {
//                // move right
//                room.roomArray[getY()][getX()+1] = this;
//                setX(getX()+1);
//            }
//            else {
//                room.roomArray[getY()][getX()-1] = this;
//                setX(getX()-1);
//            }
//        } else {
//            
//        }
    }

   

    // checks if a movement is can be made
    public boolean isLegal(int newX) {
        return true;
    }
}
