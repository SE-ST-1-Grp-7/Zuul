package worldofzuul.People;

import java.util.ArrayList;
import worldofzuul.PrintOut;
import worldofzuul.items.Inventory;
import worldofzuul.items.Item;
import worldofzuul.mapAndRooms.Room;
import worldofzuul.userCommand.Command;

/**
 * Player class - subclass of the Pemson class Used to instantiate a Player
 *
 * @author Gruppe 7, Robin, Niclas, Søren & Jonas
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

    /**
     * a constructor for making a player
     *
     * @param name
     * @param speed
     * @param x
     * @param y
     * @param currentRoom
     */
    public Player(String name, int speed, int x, int y, Room currentRoom) {
        super(name, speed, x, y); //a call to the super constructor (in Person)
        this.energy = 100; //the current energy level
        this.energyCap = 100; // the energy cap
        this.fatigue = 0; //the current amount of fatigue
        this.fatigueCap = 100; //the fatigue cap
        inventory = new Inventory(); //instanciate the inventory
        this.currentRoom = currentRoom;
        this.gradedAssignments = 0; //the amount of graded assignments is set to 0
        this.assignmentProgress = 0; //the progress of grading an assignment is set to 0
        spawnPlayer();

    }

    /**
     * getter for the inventory
     *
     * @return
     */
    public ArrayList<Item> getInventory() {
        return inventory.getInventory(); //we call the getInventory() method from the inventory object and return it to the player
    }

    public void spawnPlayer() {
        currentRoom.roomArray[getY()][getX()] = this;
    }

    /**
     * remove an item from the inventory
     *
     * @param item
     */
    public void removeItemFromIntevtory(Item item) {
        inventory.removeItem(item);
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
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

    public void move(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        printInventory();

        // Get second parsed command word and assign it to String variable.
        String direction = command.getSecondWord();
        /* Assign next room according to matching direction defined in the
           createRooms method */
        switch (direction) {
            case "left":
                move(getX() - 1, 'x');
                break;
            case "right":
                move(getX() + 1, 'x');
                break;
            case "down":
                move(getY() + 1, 'y');
                break;
            case "up":
                move(getY() - 1, 'y');
                break;

        }
        System.out.println(currentRoom.getLongDescription());
        PrintOut.displayRoom(currentRoom);
    }

    public void move(int num, char c) {
        try {
            if (c == 'x' && !checkCollision(num, getY())) { // c = x && theres no collision occurring
                if (currentRoom.hasLoot(num, getY())) { // if theres loot, then loot it
                    lootItem((Item) currentRoom.roomArray[getY()][num]);
                }
                currentRoom.roomArray[getY()][num] = this; // move the player to another location
                if (tempItem != null) { // player has a temp item
                    currentRoom.roomArray[getY()][getX()] = tempItem; // place it in previous position
                    tempItem = null;
                } else {
                    currentRoom.roomArray[getY()][getX()] = null; // reset current position
                }

                setX(num); // set player x
            } else if (c == 'y' && !checkCollision(getX(), num)) {
                if (currentRoom.hasLoot(getX(), num)) {
                    lootItem((Item) currentRoom.roomArray[num][getX()]);
                }
                currentRoom.roomArray[num][getX()] = this;
                currentRoom.roomArray[getY()][getX()] = null;
                setY(num);

            } else {
                System.out.println("Collissioned occurred, ouch!!");
            }
        } catch (Exception ex) {
            System.out.println("You hit the wall. Ouch.");
        }

    }

    public void lootItem(Item i) {
        inventory.addItem(i);
        System.out.println(i.getName() + " added to inventory!");

    }

    public boolean checkCollision(int x, int y) {
        if (currentRoom.roomArray[y][x] instanceof Item) {
            return false;
        } else {
            return currentRoom.roomArray[y][x] != null;
        }

    }

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

    public void printInventory() {
        System.out.println(getInventory());
    }

    public void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
        int index = Integer.parseInt(command.getSecondWord());
        inventory.getItem(index).use(this);
    }

}

/**
 * getter for gradedAssignments
 *
 * @return
 */
