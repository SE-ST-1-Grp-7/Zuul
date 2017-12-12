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
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal positioning in grid.
     * @param y                 int, vertical positioning in grid.
     * @param currentRoom       Room, placed currently in this room.
     * @param hasQ              boolean, true if student has a questions.
     * @param studentImage      String, file path to texture.
     * @param em                EntityManager, used for giving items.
     */
    public Student(String id,
            int x,
            int y,
            Room currentRoom,
            boolean hasQ,
            String studentImage,
            EntityManager em) {

        // Pass arguments to superclass.
        super(id,
                x,
                y,
                currentRoom,
                em);
        // Student has a question to player.
        this.hasQuestionToPlayer = hasQ;
        
        this.studentImage = studentImage;
        super.setEntityImage(studentImage);
        getCurrentRoom().getEntities()[getY()][getX()] = null;
    }

    /**
     * Algorithm for students to chase player.
     */
    public void chasePlayer() {
        double distanceToPlayer = Math.sqrt(Math.pow(getEntityManager()
                .getPlayer().getX() - this.getX(),2 ) +
                 (Math.pow(getEntityManager().getPlayer().getY()
                         - this.getY(),2 )));
        
        // If student is within reach of player, then interact.
        if(distanceToPlayer <= 1) {
            getEntityManager().getPlayer().onInteract(this);
            setHasQuestionToPlayer(false);
            
        // Else chase.
        } else {
            // Go right.
            if(this.getX() < getEntityManager().getPlayer().getX()){
                if (!checkCollision(getX() + 1, getY())) {
                    move((getX()+1),(getY()));
                }
            }
            
            // Go down.
            if(this.getY() < getEntityManager().getPlayer().getY()){
                if (!checkCollision(getX(), getY() + 1)) {
                    move((getX()),(getY()+1));
                }
            }
            
            // Go left.
            if(this.getX() > getEntityManager().getPlayer().getX()){
                if (!checkCollision(getX() - 1, getY())) {
                    move((getX()-1),(getY()));
                }
            }
            
            // Go up.
            if(this.getY() > getEntityManager().getPlayer().getY()){
                if (!checkCollision(getX(), getY() - 1)) {
                    move((getX()),(getY()-1));
                }
            }
        }
    }

    /**
     * One of two move methods for student, random. Student moves in a random
     * direction with a 50% chance per call used by an idling student.
     */
    public void idleMove() {
        if (getEntityManager().getPlayer().getCurrentRoom() == getCurrentRoom()
                && hasQuestionToPlayer) {
            chasePlayer();
        // else if true, move.
        } else if (rand.nextBoolean()) {
            String[] directions = {"left", "right", "up", "down"};
            // Roll for a random direction.
            String direction = directions[rand.nextInt(4)];
            
            // Random direction movement.
            switch (direction) {
                case "left":
                    if (getCurrentRoom().getEntities()[getY()][getX() - 1]
                            instanceof Door) {
                        getCurrentRoom().getEntities()[getY()][getX() - 1].
                                onInteract(this);
                        
                    } else if (!checkCollision(getX() - 1, getY())) {
                        move(getX() - 1, getY());
                    }
                    break;
                    
                case "right":
                    if (getCurrentRoom().getEntities()[getY()][getX() + 1]
                            instanceof Door) {
                        getCurrentRoom().getEntities()[getY()][getX() + 1].
                                onInteract(this);
                        
                    } else if (!checkCollision(getX() + 1, getY())) {
                        move(getX() + 1, getY());
                    }
                    break;
                    
                case "up":
                    if (getCurrentRoom().getEntities()[getY() - 1][getX()]
                            instanceof Door) {
                        getCurrentRoom().getEntities()[getY() - 1][getX()].
                                onInteract(this);
                        
                    } else if (!checkCollision(getX(), getY() - 1)) {
                        move(getX(), getY() - 1);
                    }
                    break;
                    
                case "down":
                    if (getCurrentRoom().getEntities()[getY() + 1][getX() + 1]
                            instanceof Door) {
                        getCurrentRoom().getEntities()[getY() + 1][getX() + 1].
                                onInteract(this);
                        
                    } else if (!checkCollision(getX(), getY() + 1)) {
                        move(getX(), getY() + 1);
                    }
                    break;
            }
        }
    }

    /**
     * The second movement method for student. Removes student from current
     * location and assign student to new location.
     *
     * @param newX      int, new horizontal grid position.
     * @param newY      int, new vertical grid position.
     */
    public void move(int newX, int newY) {
        // Set current position to null.
        getCurrentRoom().getEntities()[getY()][getX()] = null;
        // Place student at new location.
        getCurrentRoom().getEntities()[newY][newX] = this;
        // Update x & y.
        setX(newX);
        setY(newY);
    }

    // GETTERS & SETTERS
    
    /**
     * Get has qusetion for player.
     *
     * @return      boolean, true if student has question for player, false
     *              otherwise.
     */
    public boolean getHasQuestionToPlayer() {
        return this.hasQuestionToPlayer;
    }

    /**
     * Set for has qusetion for player.
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
    public void onInteract(Person p) {
        // Set player attribute to true.
        ((Player)p).setPlayerAskedStudent(true);
    }
}