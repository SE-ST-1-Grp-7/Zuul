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
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of assignment.
     * @param height            int, pixel height of assignment.
     * @param currentRoom       Room, currently in this room.
     */
    public Assignment(int x,
            int y,
            int width,
            int height,
            Room currentRoom, EntityManager em) {
        
        // Pass arguments to superclass.
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Assignment",                   // Name of item.
                "An assigment you can grade.",  // Description of item.
                1,                              // Weight of item.
                em);                            // Entitymanager             
        // Pass path of texture to superclass.
        super.setEntityImage(assignmentImage);
    }
    
    /**
     * Override, upon use of item.
     * 
     * @param p     Player, player is the one doing the item.
     */
    @Override
    public void use(Player p) {
        //if you have more than or equal to 20 energy
        if (p.getEnergy() >= 20 && p.currentRoom.getName().equals("teacher room")) {
            //we remove energy and add assignment progress 5 times
            for (int i = 0; i < 5; i++) {
                //we remove the required amount of energy/5 so it will be 20
                p.setEnergy(p.getEnergy() - (20 / 5));
                // we add 20 to the progress
                p.setAssignmentProgress(p.getAssignmentProgress() + 20);
                
                //we print the progress
                System.out.println("Energy = " + p.getEnergy() +
                        " and assignmentProgress = " +
                        p.getAssignmentProgress());
            }
            /*if the progress is 100 we set the progress to 0 and add 1 to the
            gradedAssignments and removes it from the inventory*/
            if (p.getAssignmentProgress() >= 100) {
                p.setAssignmentProgress(0); //set the progress back to 0
                //add 1 to the total amount of graded assignments
                p.setGradedAssignments(p.getGradedAssignments() + 1); 
                System.out.println("Assignment graded");
                //removes this assignment from the player's inventory
                p.inventory().removeItem(this);
                
                //if you have graded 10 assignments; you win and quit the game
                if(p.getGradedAssignments() >= 2){
                    System.out.println("you won");
                    //quit game
                }

            }
        }else{ // If not enough energy, do the following.
            System.out.println("You do not have enough energy or you are not in your own room");
        }
        
    }
}
