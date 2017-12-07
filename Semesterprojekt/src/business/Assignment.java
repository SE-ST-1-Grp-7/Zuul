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
     * @param width int, pixel width of assignment.
     * @param height int, pixel height of assignment.
     * @param currentRoom Room, currently in this room.
     */
    public Assignment(int x,
            int y,
            int width,
            int height,
            Room currentRoom) {

        // Pass arguments to superclass.
        super(x,
                y,
                width,
                height,
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
