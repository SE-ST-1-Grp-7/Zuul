package business;

/**
 * Player class, the main character of the game and controllable.
 *
 * @author Gruppe 7
 */
public class Player extends Person {

    private int energy; // Current energy level.
    private int energyCap; // Max amount of energy.
    private Inventory inventory; // The player's inventory.
    private int gradedAssignments; // Amount of graded assignments.
    private int assignmentProgress; // Progress of grading an assignment.
    private Assignment currentAssignment;
    private Item tempItem; // Temporary holder for item to be dropped.
    private boolean dont = false; // Avoid tempItem overlap with other items.
    private String playerImage = "/textures/player.png"; // Image path.
    private String facing; // Direction for object to be interacted with.
    private boolean hasKey;
    private int timeLeft;

    /**
     * Constructor for player. Passes all relevant arguments to Superclass and
     * sets the attributes for the player.
     *
     * @param x int, horizontal positioning in grid.
     * @param y int, vertical positioning in grid.
     * @param currentRoom Room, placed currently in this room.
     * @param name String, name of player.
     * @param em EntityManager,
     */
    public Player(int x,
            int y,
            String name,
            Room currentRoom,
            EntityManager em) {

        super(x,
                y,
                Person.DEFAULT_PERSON_WIDTH,
                Person.DEFAULT_PERSON_HEIGHT,
                currentRoom, em);
        // Pass image path to Superclass.
        super.setEntityImage(playerImage);

        // PLAYER STATS
        this.energy = 200; // Assign energy level at the start.
        this.energyCap = 200; // Assign energy capacity.
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
                    getCurrentRoom().getEntities()[getY()][getX() + 1].onInteract(this);
                }
                break;
            case "left":
                if (getCurrentRoom().getEntities()[getY()][getX() - 1] != null) {
                    getCurrentRoom().getEntities()[getY()][getX() - 1].onInteract(this);
                }
                break;
            case "up":
                if (getCurrentRoom().getEntities()[getY() - 1][getX()] != null) {
                    getCurrentRoom().getEntities()[getY() - 1][getX()].onInteract(this);
                }
                break;
            case "down":
                if (getCurrentRoom().getEntities()[getY() + 1][getX()] != null) {
                    getCurrentRoom().getEntities()[getY() + 1][getX()].onInteract(this);
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
                getEntityManager().getItemList().remove(getCurrentRoom().getEntities()[getY() + yOffset][getX() + xOffset]);
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
            tempItem.setCurrentRoom(getCurrentRoom());
            getCurrentRoom().getEntities()[tempItem.getY()][tempItem.getX()] = tempItem; // place it
            getEntityManager().getItemList().add(tempItem);
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
    public void onInteract(Person p) {
        System.out.println("PLS ANSWER MY QUESTION PROFESSOR :(");
        setEnergy(getEnergy() - 40);
        // this gets called when an "evil" student interacts with the player
        // needs to pop up window/something to get input from the player
    }

    public boolean getHasKey() {
        return this.hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public Assignment getCurrentAssignment() {
        return this.currentAssignment;
    }

    public void setCurrentAssignment(Assignment currentAssignment) {
        this.currentAssignment = currentAssignment;
    }
    
    /**
     * Get method for retrieving the time left after loading.
     * 
     * @return      int, time left of the game in seconds at time of loading.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Set method for setting the remaining time at saving point.
     * 
     * @param timeLeft      int, time left in seconds at saving point.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
