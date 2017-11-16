package worldofzuul.People;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.items.Inventory;
import worldofzuul.items.Item;
import worldofzuul.mapAndRooms.Room;

/**
 * Player class - subclass of the Person class Used to instantiate a Player
 *
 * @author Gruppe 7
 */
public class Player extends Person {

    private int energy; //current energy level
    private int energyCap; //the max amount of energy
    private int fatigue; //the current fatiuge level
    private int fatigueCap; //the max amount of fatiuge
    private Inventory inventory; // the player's inventory
    private Room currentRoom;
    private int gradedAssignments; //the amount of graded assignments
    private int assignmentProgress; //the progress of grading an assignment
    private Item tempItem;
    private boolean dont = false;
    private Image playerImage = new Image("/texture/player.png");

    /**
     * Constructor for player.
     *
     * @param x
     * @param y
     * @param currentRoom
     * @param graphics
     * @param name
     */
    public Player(int x, int y, String name, Room currentRoom) {
        super(x,
                y,
                Person.DEFAULT_PERSON_WIDTH,
                Person.DEFAULT_PERSON_HEIGHT,
                currentRoom);
        super.setEntityImage(playerImage);
        
        this.energy = 100; //the current energy level
        this.energyCap = 100; // the energy cap
        this.fatigue = 0; //the current amount of fatigue
        this.fatigueCap = 100; //the fatigue cap
        inventory = new Inventory(); //instanciate the inventory
        this.gradedAssignments = 0; //the amount of graded assignments is set to 0
        this.assignmentProgress = 0; //the progress of grading an assignment is set to 0
    }

    public void move(String direction) {
        this.inventory.printInventory();

        // Get second parsed command word and assign it to String variable.
        /* Assign next room according to matching direction defined in the
           createRooms method */
        switch (direction) {
            case "left":
                move((int) getX() - 1, 'x');
                break;
            case "right":
                move((int) getX() + 1, 'x');
                break;
            case "down":
                move((int) getY() + 1, 'y');
                break;
            case "up":
                move((int) getY() - 1, 'y');
                break;

        }
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * places the item referenced by tempItem in the room
     */
    public void placeItem() {
        if (tempItem != null) { // if tempItem exists
            currentRoom.roomArray[tempItem.getY()][tempItem.getX()] = tempItem; // place it
            dont = true; // dont set previous field to null
        }
    }

    public void move(int num, char c) {
        try {
            if (c == 'x' && !checkCollision(num, getY())) { // c = x && theres no collision occurring
                placeItem(); // places tempItem if it exists
                if (currentRoom.hasLoot(num, (int) getY())) { // if theres loot && inventory isnt full, then loot it
                    if (inventory.addItem((Item) currentRoom.roomArray[getY()][num])) { // if addItem was successful
                        System.out.println("YOU LOOTED IT"); // print loot message
                    } else { // if not
                        tempItem = (Item) currentRoom.roomArray[getY()][num]; // set temp item to be whatevers in pos x & y

                    }
                }
                currentRoom.roomArray[getY()][num] = this; // move the player to another location
                if (!dont) {
                    currentRoom.roomArray[getY()][getX()] = null; // reset current position
                } else {
                    dont = false;
                    tempItem = null;
                }

                setX(num);

                // set player x
            } else if (c == 'y' && !checkCollision(getX(), num)) { // c = x && theres no collision occurring
                placeItem(); // places tempItem if it exists
                if (currentRoom.hasLoot(getX(), num)) { // if theres loot && inventory isnt full, then loot it
                    if (inventory.addItem((Item) currentRoom.roomArray[num][getX()])) { // if addItem was successful
                        System.out.println("YOU LOOTED IT"); // print loot message
                    } else { // if not
                        tempItem = (Item) currentRoom.roomArray[num][getX()]; // set temp item to be whatevers in pos x & y

                    }
                }
                currentRoom.roomArray[num][getX()] = this; // move the player to another location
                if (!dont) {
                    currentRoom.roomArray[getY()][getX()] = null; // reset current position
                } else {
                    dont = false;
                    tempItem = null;
                }

                setY(num);

            } else {
                System.out.println("Collissioned occurred, ouch!!");
            }
        } catch (Exception ex) {
            System.out.println("You hit the wall. Ouch.");
        }

    }

    /**
     * method for collision check
     *
     * @param x
     * @param y
     * @return
     */
    public boolean checkCollision(int x, int y) {
        if (currentRoom.roomArray[y][x] instanceof Item) {
            return false;
        } else {
            return currentRoom.roomArray[y][x] != null;
        }

    }

    // GETTERS & SETTERS
    /**
     * a method to call/get the player's inventory (getter)
     *
     * @return
     */
    public Inventory inventory() {
        return this.inventory;
    }

    /**
     * getter for the current energy level
     *
     * @return
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * setter for the current energy level
     *
     * @param energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * getter for the energy cap
     *
     * @return
     */
    public int getEnergyCap() {
        return this.energyCap;
    }

    /**
     * setter for energyCap
     *
     * @param energyCap
     */
    public void setEnergyCap(int energyCap) {
        this.energyCap = energyCap;
    }

    /**
     * getter for the current fatiuge
     *
     * @return
     */
    public int getFatigue() {
        return this.fatigue;
    }

    /**
     * setter for the current fatiuge
     *
     * @param fatigue
     */
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    /**
     * getter for the fatiuge cap
     *
     * @return
     */
    public int getFatigueCap() {
        return this.fatigueCap;
    }

    /**
     * setter for fatiugeCap
     *
     * @param fatigueCap
     */
    public void setFatigueCap(int fatigueCap) {
        this.fatigueCap = fatigueCap;
    }

    /**
     * getter for gradedAssignments
     *
     * @return
     */
    public int getGradedAssignments() {
        return this.gradedAssignments;
    }

    /**
     * setter for gradedAssignments
     *
     * @param assignment
     */
    public void setGradedAssignments(int assignment) {
        this.gradedAssignments = assignment;
    }

    /**
     * getter for assignmentProgress
     *
     * @return
     */
    public int getAssignmentProgress() {
        return this.assignmentProgress;
    }

    /**
     * setter for assignmentProgress
     *
     * @param assignmentProgress
     */
    public void setAssignmentProgress(int assignmentProgress) {
        this.assignmentProgress = assignmentProgress;
    }
}
