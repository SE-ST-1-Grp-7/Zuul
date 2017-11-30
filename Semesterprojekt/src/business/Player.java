package business;

/**
 * Player class, the main character of the game and controllable.
 *
 * @author Gruppe 7
 */
public class Player extends Person {

    private int energy; // Current energy level.
    private int energyCap; // Max amount of energy.
    private int fatigue; // Current fatiuge level.
    private int fatigueCap; // Max amount of fatiuge.
    private Inventory inventory; // The player's inventory.
    private int gradedAssignments; // Amount of graded assignments.
    private int assignmentProgress; // Progress of grading an assignment.
    private Item tempItem; // Temporary holder for item to be dropped.
    private boolean dont = false; // Avoid tempItem overlap with other items.
    private String playerImage = "/textures/player.png"; // String of image path.
    private String facing; // Direction for object to be interacted with.
    private boolean hasKey;

    /**
     * Constructor for player. Passes all relevant arguments to Superclass and
     * sets the attributes for the player.
     *
     * @param x int, horizontal positioning in grid.
     * @param y int, vertical positioning in grid.
     * @param currentRoom Room, placed currently in this room.
     * @param name String, name of player.
     */
    public Player(int x, int y, String name, Room currentRoom) {
        super(x,
                y,
                Person.DEFAULT_PERSON_WIDTH,
                Person.DEFAULT_PERSON_HEIGHT,
                currentRoom);
        // Pass image path to Superclass.
        super.setEntityImage(playerImage);

        // PLAYER STATS
        this.energy = 100; // Assign energy level to start at 100.
        this.energyCap = 100; // Assign energy capacity to 100.
        this.fatigue = 0; // Current amount of fatigue starts at 0.
        this.fatigueCap = 100; // Assign fatigue capacity to 100.
        inventory = new Inventory(); // Instantiate inventory.
        this.gradedAssignments = 0; // Amount of graded assignments starts at 0.
        // Progress of grading an assignment starts at 0.
        this.assignmentProgress = 0;
        this.hasKey = false;
    }

    /**
     * Interacts with nearby interactable object.
     */
    public void interact() {
        switch (facing) {
            case "right":
                if (getCurrentRoom().getEntities()[getY()][getX() + 1] != null) {
                    getCurrentRoom().getEntities()[getY()][getX() + 1].onInteract();
                    pickUpItem(0, +1);
                }
                break;
            case "left":
                if (getCurrentRoom().getEntities()[getY()][getX() - 1] != null) {
                    getCurrentRoom().getEntities()[getY()][getX() - 1].onInteract();
                    pickUpItem(0, -1);
                }
                break;
            case "up":
                if (getCurrentRoom().getEntities()[getY() - 1][getX()] != null) {
                    getCurrentRoom().getEntities()[getY() - 1][getX()].onInteract();
                    pickUpItem(-1, 0);
                }
                break;
            case "down":
                if (getCurrentRoom().getEntities()[getY() + 1][getX()] != null) {
                    getCurrentRoom().getEntities()[getY() + 1][getX()].onInteract();
                    pickUpItem(+1, 0);
                }
                break;
        }
        // check if square next to player != null
        // if true - call .interact on the entity

    }

    private void pickUpItem(int yOffset, int xOffset) {
        if (this.inventory.getInventory().size() < this.inventory.getCapacity()) {
            if (getCurrentRoom().getEntities()[getY() + yOffset][getX() + xOffset] instanceof Item) {
                inventory.addItem((Item) getCurrentRoom().getEntities()[getY() + yOffset][getX() + xOffset]);
                getCurrentRoom().getEntities()[getY() + yOffset][getX() + xOffset] = null;
            }
        }
    }

    /**
     * Move in given direction.
     *
     * @param direction String, lowercase written direction for movement.
     */
    public void move(String direction) {
        // Get second parsed command word and assign it to String variable.
        /* Assign next room according to matching direction defined in the
           createRooms method */
        switch (direction) {
            case "left":
                facing = "left";
                move(getX() - 1, getY());
                break;
            case "right":
                facing = "right";
                move(getX() + 1, getY());
                break;
            case "down":
                facing = "down";
                move(getX(), getY() + 1);
                break;
            case "up":
                facing = "up";
                move(getX(), getY() - 1);
                break;

        }
//        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Place item referenced by tempItem into the room
     */
    public void placeItem() {
        if (tempItem != null) { // if tempItem exists
            currentRoom.getEntities()[tempItem.getY()][tempItem.getX()] = tempItem; // place it
            dont = true; // dont set previous field to null
        }
    }

    /**
     * Teleportation movement.
     *
     * @param newX int, new X coordinate in room.
     * @param newY int, new Y coordinate in room.
     */
    public void move(int newX, int newY) {
        try {
            if (!checkCollision(newX, newY)) { // c = x && theres no collision occurring
                placeItem(); // places tempItem if it exists
                if (getCurrentRoom().hasLoot(newX, newY)) { // if theres loot && inventory isnt full, then loot it
                    if (inventory.addItem((Item) getCurrentRoom().getEntities()[newY][newX])) { // if addItem was successful
                    } else { // if not
                        tempItem = (Item) getCurrentRoom().getEntities()[newY][newX]; // set temp item to be whatevers in pos x & y
                    }
                }
                getCurrentRoom().getEntities()[newY][newX] = this; // move the player to another location
                if (!dont) {
                    getCurrentRoom().getEntities()[getY()][getX()] = null; // reset current position
                } else {
                    dont = false;
                    tempItem = null;
                }
                setX(newX);
                setY(newY);
            } else { // if a collision is detected
                System.out.println("Collision occurred, ouch!!");
            }
        } catch (Exception ex) { // catches the out of bounds exception that occurs when you try to move outside the limits of the array
            System.out.println("You hit the wall. Ouch.");
        }

    }

    // GETTERS & SETTERS
    /**
     * Method call/get the player's inventory.
     *
     * @return Inventory, returns the inventory object.
     */
    public Inventory inventory() {
        return this.inventory;
    }

    /**
     * Getter for tempItem - used to drop an item.
     *
     * @return Item, return the tempItem object.
     */
    public Item getTempItem() {
        return this.tempItem;
    }

    /**
     * Setter for tempItem - used to set the tempitem to be dropped
     *
     * @param i Item, assigns an Item object to tempItem.
     */
    public void setTempItem(Item i) {
        this.tempItem = i;
    }

    /**
     * Getter for the current energy level.
     *
     * @return int, current value of energy level.
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Setter for the current energy level.
     *
     * @param energy int, value to be current energy level.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Getter for the energy capacity.
     *
     * @return int, value of energy capacity.
     */
    public int getEnergyCap() {
        return this.energyCap;
    }

    /**
     * Setter for energyCap.
     *
     * @param energyCap int, value to be energy capacity.
     */
    public void setEnergyCap(int energyCap) {
        this.energyCap = energyCap;
    }

    /**
     * Getter for the current fatigue.
     *
     * @return int, current value for fatigue stat.
     */
    public int getFatigue() {
        return this.fatigue;
    }

    /**
     * Setter for the current fatigue.
     *
     * @param fatigue int, value to become the current fatigue level.
     */
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    /**
     * Getter for the fatiuge capacity.
     *
     * @return int, value of fatigue capacity.
     */
    public int getFatigueCap() {
        return this.fatigueCap;
    }

    /**
     * Setter for fatiuge capacity.
     *
     * @param fatigueCap int, change fatigue capacity to this value.
     */
    public void setFatigueCap(int fatigueCap) {
        this.fatigueCap = fatigueCap;
    }

    /**
     * Getter for graded assignments.
     *
     * @return int, value of current assignments graded.
     */
    public int getGradedAssignments() {
        return this.gradedAssignments;
    }

    /**
     * Setter for graded assignments.
     *
     * @param assignment int, assign new value of graded assignments.
     */
    public void setGradedAssignments(int assignment) {
        this.gradedAssignments = assignment;
    }

    /**
     * Getter for assignment progress.
     *
     * @return int, value representation of assignment progress.
     */
    public int getAssignmentProgress() {
        return this.assignmentProgress;
    }

    /**
     * Setter for assignment progress.
     *
     * @param assignmentProgress int, change value of assignment progress.
     */
    public void setAssignmentProgress(int assignmentProgress) {
        this.assignmentProgress = assignmentProgress;
    }

    /**
     * Override, upon interaction with player. (if students catch up to the
     * professor.)
     */
    @Override
    public void onInteract() {
        System.out.println("PLS ANSWER MY QUESTION PROFESSOR :(");
        // this gets called when an "evil" student interacts with the player
        // needs to pop up window/something to get input from the player
    }

    public boolean getHasKey() {
        return this.hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
}
