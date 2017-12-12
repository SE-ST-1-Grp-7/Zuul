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
    private String playerName;
    private boolean interactHappend;

    /* Two variables for checking when the player interacted with a student/
       tutor if they are in Person.java because the onInteract method on Student
       and Tutor needs a Person*/
    // Used to check if player asked student.
    private boolean playerAskedStudent;
    // used to check if player asked tutor.
    private boolean playerAskedTutor;

    /**
     * Constructor for player. Passes all relevant arguments to Superclass and
     * sets the attributes for the player.
     *
     * @param id                Override; upon interaction with furniture.
     * @param x                 int, horizontal positioning in grid.
     * @param y                 int, vertical positioning in grid.
     * @param currentRoom       Room, placed currently in this room.
     * @param name              String, name of player.
     * @param em                EntityManager, used for persons giving items.
     */
    public Player(String id,
            int x,
            int y,
            String name,
            Room currentRoom,
            EntityManager em) {
        
        // Pass parameters to superclass.
        super(id,
                x,
                y,
                currentRoom,
                em);
        // Pass image path to Superclass.
        super.setEntityImage(playerImage);

        // PLAYER STATS
        this.playerName = name;
        // Assign energy level at the start.
        this.energy = 200;
        // Assign energy capacity.
        this.energyCap = 200;
        // Instantiate inventory.
        inventory = new Inventory();
        // Amount of graded assignements starts at 0.
        this.gradedAssignments = 0;
        // Progress of grading an assignment starts at 0.
        this.assignmentProgress = 0;
        this.hasKey = false;
        this.playerAskedStudent = false;
        this.playerAskedTutor = false;
    }

    /**
     * Interacts with nearby interactable object.
     */
    public void interact() {
        // Switch case with direction of facing.
        switch (facing) {
            case "right":
                if (getCurrentRoom().getEntities()
                        [getY()][getX() + 1] != null) {
                    getCurrentRoom().getEntities()
                            [getY()][getX() + 1].onInteract(this);
                }
                break;
                
            case "left":
                if (getCurrentRoom().getEntities()
                        [getY()][getX() - 1] != null) {
                    getCurrentRoom().getEntities()
                            [getY()][getX() - 1].onInteract(this);
                }
                break;
                
            case "up":
                if (getCurrentRoom().getEntities()
                        [getY() - 1][getX()] != null) {
                    getCurrentRoom().getEntities()
                            [getY() - 1][getX()].onInteract(this);
                }
                break;
                
            case "down":
                if (getCurrentRoom().getEntities()
                        [getY() + 1][getX()] != null) {
                    getCurrentRoom().getEntities()
                            [getY() + 1][getX()].onInteract(this);
                }
                break;
        }
    }

    /**
     * Move in given direction.
     *
     * @param direction     String, lowercase written direction for movement.
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
    }

    /**
     * Place item referenced by tempItem into the room.
     */
    public void placeItem() {
        // If tempItem exists.
        if (tempItem != null) {
            tempItem.setCurrentRoom(getCurrentRoom());
            
            // Place it.
            getCurrentRoom().getEntities()
                    [tempItem.getY()][tempItem.getX()] = tempItem;
            
            getEntityManager().getItemList().add(tempItem);
            // Don't set previous to null.
            dont = true;
        }
    }

    /**
     * Teleportation movement.
     *
     * @param newX      int, new X coordinate in room.
     * @param newY      int, new Y coordinate in room.
     */
    public void move(int newX, int newY) {
        // If no collision.
        if (!checkCollision(newX, newY)) {
            // Place tempItem if exist.
            placeItem();
            // If there is loot.
            if (getCurrentRoom().hasLoot(newX, newY)) {
                // If item can be added to inventory.
                if (inventory.addItem((Item)
                        getCurrentRoom().getEntities()[newY][newX])) {
                // else set tempItem.
                } else { // if not
                    tempItem = (Item)
                            getCurrentRoom().getEntities()[newY][newX];
                }
            }
            // Move player to this location.
            getCurrentRoom().getEntities()[newY][newX] = this;
            
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

    /**
     * Override; upon interaction with player. (if students catch up to the
     * professor.)
     * 
     * @param p     Person, person whom are interacting with player.
     */
    @Override
    public void onInteract(Person p) {
        setEnergy(getEnergy() - 40);
        this.interactHappend = true;
        // this gets called when an "evil" student interacts with the player
        // needs to pop up window/something to get input from the player
    }
    
    // GETTERS & SETTERS
    
    /**
     * Override; retrieve player name.
     * 
     * @return      String, name of player retrieved.
     */
    @Override
    public String getName() {
        return playerName;
    }

    /**
     * Override; set player name.
     * 
     * @param playerName    String, name to be set as player name.
     */
    @Override
    public void setName(String playerName) {
        this.playerName = playerName;
    }
    
    /**
     * Under special circumstance, change player image.
     * 
     * @param path  String, file path of new image texture.
     */
    public void setImage(String path) {
        this.playerImage = path;
        super.setEntityImage(playerImage);
        this.getCurrentRoom().setEntity(this);
    }

    /**
     * Get the player's inventory.
     *
     * @return      Inventory, returns the inventory object.
     */
    public Inventory inventory() {
        return this.inventory;
    }

    /**
     * Getter for tempItem - used to drop an item.
     *
     * @return      Item, return the tempItem object.
     */
    public Item getTempItem() {
        return this.tempItem;
    }

    /**
     * Set tempItem - used to set the tempitem to be dropped.
     *
     * @param i     Item, assigns an Item object to tempItem.
     */
    public void setTempItem(Item i) {
        this.tempItem = i;
    }

    /**
     * Get current energy level.
     *
     * @return      int, current value of energy level.
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Setter for the current energy level.
     *
     * @param energy    int, value to be current energy level.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Get energy capacity.
     *
     * @return      int, value of energy capacity.
     */
    public int getEnergyCap() {
        return this.energyCap;
    }

    /**
     * Set energy capacity.
     *
     * @param energyCap     int, value to be energy capacity.
     */
    public void setEnergyCap(int energyCap) {
        this.energyCap = energyCap;
    }

    /**
     * Get for graded assignments.
     *
     * @return      int, value of current assignments graded.
     */
    public int getGradedAssignments() {
        return this.gradedAssignments;
    }

    /**
     * Setter for graded assignments.
     *
     * @param assignment    int, assign new value of graded assignments.
     */
    public void setGradedAssignments(int assignment) {
        this.gradedAssignments = assignment;
    }

    /**
     * Get assignment progress.
     *
     * @return      int, value representation of assignment progress.
     */
    public int getAssignmentProgress() {
        return this.assignmentProgress;
    }

    /**
     * Set assignment progress.
     *
     * @param assignmentProgress    int, change value of assignment progress.
     */
    public void setAssignmentProgress(int assignmentProgress) {
        this.assignmentProgress = assignmentProgress;
    }

    /**
     * Retrieve interaction status.
     * 
     * @return      boolean, interaction status.
     */
    public boolean getInteractionHappend() {
        return this.interactHappend;
    }

    /**
     * Set interaction status.
     * 
     * @param b     boolean, new interaction status.
     */
    public void setInteractionHappend(boolean b) {
        this.interactHappend = b;
    }

    /**
     * Get has key.
     * 
     * @return      boolean, true if got key, otherwise false.
     */
    public boolean getHasKey() {
        return this.hasKey;
    }

    /**
     * Set has key.
     * 
     * @param hasKey    boolean, set this as status of have key.
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Get current assignment.
     * 
     * @return      Assignment, get the assignment getting graded.
     */
    public Assignment getCurrentAssignment() {
        return this.currentAssignment;
    }

    /**
     * Set assignment to current.
     * 
     * @param currentAssignment     Assignment, to be set to current.
     */
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

    /**
     * Get player interacted with a student.
     *
     * @return      boolean, true if the player asked a student.
     */
    public boolean getPlayerAskedStudent() {
        return playerAskedStudent;
    }

    /**
     * Set true when a student was asked.
     *
     * @param playerAskedStudent    boolean, set state.
     */
    public void setPlayerAskedStudent(boolean playerAskedStudent) {
        this.playerAskedStudent = playerAskedStudent;
    }

    /**
     * getter for checking if the player interacted with a tutor
     *
     * @return true if player asked a tutor
     */
    public boolean getPlayerAskedTutor() {
        return playerAskedTutor;
    }

    /**
     * Set true when a tutor was asked.
     *
     * @param playerAskedTutor      boolean, set state.
     */
    public void setPlayerAskedTutor(boolean playerAskedTutor) {
        this.playerAskedTutor = playerAskedTutor;
    }
}
