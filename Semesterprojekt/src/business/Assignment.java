package business;

/**
 * Subclass of Item, Assignment class.
 *
 * @author Robin & Søren & Rasmus Willer
 */
public class Assignment extends Item {

    // Path of texture for assignment.
    private String assignmentImage = "/textures/assignment.png";

    /**
     * Constructor for Assignment class.
     *
     * @param x int, horizontal position in room grid.
     * @param y int, vertical position in room grid.
     * @param currentRoom Room, currently in this room.
     */
    public Assignment(String id,
            int x,
            int y,
            Room currentRoom) {

        // Pass arguments to superclass.
        super(id,
                x,
                y,
                currentRoom,
                // Name of item.
                "Assignment",
                // Description of item.
                "An assigment you can grade.",
                // Weight of item.
                1);

        // Pass path of texture to superclass.
        super.setEntityImage(assignmentImage);
    }

    /**
     * Override, upon use of item.
     *
     * @param p Player, player is the one using the item.
     */
    @Override
    public boolean use(Player p) {
        if (p.getCurrentAssignment() == null && p.getCurrentRoom().getName().equals("teacher room")) {
            p.setCurrentAssignment(this);
            return true;
        } else {
            System.out.println("You're currently working on another assignment");
            return false;
        }
    }
    
    /**
     * tick method: Used on a assignment when grading them. When tick is called
     * 20 points is added to assignment progress with each tick and when 
     * progress is 100, the assignment is graded. 
     * 
     * @param p     Player, the player must have an assignemt. 
     */
    public void tick(Player p) {
        if (p.getAssignmentProgress() >= 100) {
            p.setAssignmentProgress(0); //set the progress back to 0
            //add 1 to the total amount of graded assignments
            p.setGradedAssignments(p.getGradedAssignments() + 1);
            System.out.println("Assignment graded");
            //removes this assignment from the player's inventory
            p.inventory().removeItem(this);
            p.setCurrentAssignment(null);
            return;
        }
        //if you have more than or equal to 20 energy

        p.setAssignmentProgress(p.getAssignmentProgress() + 20);
        p.setEnergy(p.getEnergy() - 5);
        //we remove energy and add assignment progress 5 times
        /*if the progress is 100 we set the progress to 0 and add 1 to the
            gradedAssignments and removes it from the inventory*/
    }
}
