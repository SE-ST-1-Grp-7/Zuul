package worldofzuul.People;


import java.awt.image.BufferedImage;
import java.util.Random;
import worldofzuul.mapAndRooms.Room;

/**
 *A student class that extends person
 * @author Robin & Niclas & Rasmus Willer
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    Random rand = new Random(); // used for randomization of move()

    public Student(int x, int y, int width, int height, Room currentRoom, BufferedImage graphics, Boolean hasQ){
        super(x, y, width, height, currentRoom, graphics); //a call to the super constructor
        this.hasQuestionToPlayer = hasQ; //the player has a question to the player
    }
    /**
     * one of two move methods for a student
     * moves student in a random direction with a 50% chance per call
     * used by an idling student
     */
    public void idleMove() {
        // assuming that this gets executed once per second
        if(rand.nextBoolean()) { // rolls either true or false, if true then move
            String[] directions = { "left","right","up","down" };
            String direction = directions[rand.nextInt(4)]; // roll for a random direction
            switch(direction) {
                case "left":
                    if(isLegal(getX()-1,getY())) // if the move is legal
                        move(getX()-1,getY()); // then move
                    break;
                case "right":
                    if(isLegal(getX()+1,getY()))
                        move(getX()+1,getY());
                    break;
                case "up":
                    if(isLegal(getX(),getY()-1))
                        move(getX(),getY()-1);
                    break;
                case "down":
                    if(isLegal(getX()-1,getY()))
                        move(getX(),getY()+1);
                    break;         
            }
            
        }
    }
    public void move(int newX, int newY) {
        currentRoom.roomArray[getY()][getX()] = null; // set current position in array to null
        currentRoom.roomArray[newY][newX] = this; // place student in new position
        // update x & y
        setX(newX);
        setY(newY);
    }
    /**
     * checks if a move is legal
     * a move is legal if the desired field is null
     * @param newX
     * @param newY
     * @return 
     */
    public boolean isLegal(int newX, int newY) {
        return currentRoom.roomArray[newY][newX] == null;
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
