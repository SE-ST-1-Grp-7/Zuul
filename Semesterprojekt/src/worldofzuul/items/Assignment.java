package worldofzuul.items;

import java.awt.image.BufferedImage;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;
import worldofzuul.mapAndRooms.Room;

/**
 *
 * @author Robin & Søren & Rasmus Willer
 */
public class Assignment extends Item implements IConsumable {
    /**
     * Constructor with all the necessary parameters for the class
     * @param x                 x coordinate
     * @param y                 y coordinate
     * @param width             pixel width
     * @param height            pixel height
     * @param currentRoom       currently in room ...
     * @param graphics          graphic image
     * @param itemName          name of the item
     * @param itemDescription   item description
     * @param weight            designated inventory weight
     */
    public Assignment(int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            BufferedImage graphics,
            String itemName,
            String itemDescription,
            int weight) {
        
        // Pass arguments to superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                graphics,
                itemName,
                itemDescription,
                weight);
        
        //sets the name - calls the set name in Item super class
        super.setName("Assignment"); 
        //set a discription for the assignment item
        super.setDescription("An assignment you can gr - " +
                "calls the set name in Item super classade");
        super.setWeight(1); //set the weight for the assignment
        this.graphics = graphics;
    }

    @Override
    public void use(Player p) {
        consume(p);
        
    }
    
    /**
     * consume method grades the assignment and remove it from inventory.
     * @param p 
     */
    @Override
    public void consume(Player p) {
        //if you have more than or equal to 20 energy
        if (p.getEnergy() >= 20) {
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
        }else{
            //this will be printed if you don't have enough energy
            System.out.println("You do not have enough energy");
        }
    }
}
