package business;

import java.util.Random;

/**
 * Student class, NPC in the game.
 * 
 * @author Robin & Niclas & Rasmus Willer & Magnus Mortensen
 */
public class Student extends Person {
    // true if student has a question for the player.
    private boolean hasQuestionToPlayer;
    private String studentImage = ""; // Path to image used for this entity.
    private Random rand = new Random(); // used for randomization of move().
    
    /**
     * Student constructor, actions upon instantiation.
     * 
     * @param x             int, horizontal positioning in grid.
     * @param y             int, vertical positioning in grid.
     * @param currentRoom   Room, placed currently in this room.
     * @param hasQ          boolean, true if student has a questions.
     */
    public Student (int x,
            int y,
            Room currentRoom,
            boolean hasQ){
        
        // Pass arguments to superclass.
        super(x,
                y,
                Person.DEFAULT_PERSON_WIDTH,
                Person.DEFAULT_PERSON_HEIGHT,
                currentRoom);
        
        if ("".equals(studentImage)) {
            // Generate random choice of student texture, 12 options.
            int number = (1 + (int) (Math.random()*12));
            studentImage = "/texture/student"+number+".png";
            // Pass the chosen texture path to superclass.
            super.setEntityImage(studentImage);
        }
        this.hasQuestionToPlayer = hasQ; // Student has a question to player.
    }
    
    public Student (int x,
            int y,
            Room currentRoom,
            boolean hasQ,
            String studentImage) {
        // Pass arguments to other constructor.
        this(x, y, currentRoom, hasQ);
        this.studentImage = studentImage;
        super.setEntityImage(studentImage);
    }
    
    /**
     * One of two move methods for student, random.
     * Student moves in a random direction with a 50% chance per call
     * used by an idling student.
     */
    public void idleMove() {
        // Assumes gets executed once per second.
        
        if(rand.nextBoolean()) { // If true, move.
            String[] directions = { "left","right","up","down" };
            // Roll for a random direction.
            String direction = directions[rand.nextInt(4)];
            switch(direction) {
                case "left":
                    if(isLegal(getX()-1,getY())) // If move is legal, move.
                        move(getX()-1,getY());
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
    
    /**
     * The second movement method for student.
     * Removes student from current location and assign student to new location.
     * 
     * @param newX      int, new horizontal grid position. 
     * @param newY      int, new vertical grid position.
     */
    public void move(int newX, int newY) {
        currentRoom.getEntities()[getY()][getX()] = null; // set current position in array to null
        currentRoom.getEntities()[newY][newX] = this; // place student in new position
        // update x & y
        setX(newX);
        setY(newY);
    }
    
    /**
     * Checks if move is legal.
     * (A move is legal if the desired field is null.)
     * 
     * @param newX      int, horizontal grid position to be checked.
     * @param newY      int, vertical grid position to be checked.
     * @return          boolean, true if empty field, false otherwise.
     */
    public boolean isLegal(int newX, int newY) {
        return currentRoom.getEntities()[newY][newX] == null;
    }
    
    // GETTERS & SETTERS
    
    /**
     * Getter for hasQusetionForPlayer.
     *
     * @return      boolean, true if student has question for player,
     *              false otherwise.
     */
    public boolean getHasQuestionToPlayer() {
        return this.hasQuestionToPlayer;
    }

    /**
     * Setter for hasQusetionForPlayer
     *
     * @param hasQuestionToPlayer   boolean, set variable for student.
     */
    public void setHasQuestionToPlayer(boolean hasQuestionToPlayer) {
        this.hasQuestionToPlayer = hasQuestionToPlayer;
    }
    
    /**
     * Override, upon interacted with student.
     */
    @Override
    public void onInteract() {
        System.out.println("Hello professor! :sunglasses: ");
    }
}
