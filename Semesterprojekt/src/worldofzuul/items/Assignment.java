package worldofzuul.items;

import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.People.Player;
import worldofzuul.Quit;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Robin & Søren
 */
public class Assignment extends Item implements IConsumable {
    
    /**
     * (no-args)constructor for an assignment
     * @param link
     * @param x
     * @param y
     * @param width
     * @param height
     * @param itemName
     * @param itemDescription
     * @param weight 
     */
    public Assignment(Link link, float x, float y, int width, int height, String itemName, String itemDescription, int weight) {
        super(link, x, y, width, height, itemName, itemDescription, weight);
        super.setName("Assignment"); //sets the name - calls the set name in Item super class
        //set a discription for the assignment item
        super.setDescription("An assignment you can gr - calls the set name in Item super classade");
        super.setWeight(1); //set the weight for the assignment
    }

    @Override
    public void use(Player p) {
        consume(p);
        
    }
    
    /**
     * the consume method will grade the assignment and remove it from the inventory
     * @param p 
     */
    @Override
    public void consume(Player p) {
        //if you have more than or equal to 20 energy
        if (p.getEnergy() >= 20) {
            //we remove energy and add assignment progress 5 times
            for (int i = 0; i < 5; i++) {
                p.setEnergy(p.getEnergy() - (20 / 5)); //we remove the required amount of energy/5 so it will be 20 when it's done
                p.setAssignmentProgress(p.getAssignmentProgress() + 20); // we add 20 to the progress
                
                //we print the progress
                System.out.println("Energy = " + p.getEnergy() + " and assignmentProgress = " + p.getAssignmentProgress());
            }
            //if the progress is 100 we set the progress to 0 and add 1 to the gradedAssignments and removes it from the inventory
            if (p.getAssignmentProgress() >= 100) {
                p.setAssignmentProgress(0); //set the progress back to 0
                p.setGradedAssignments(p.getGradedAssignments() + 1); //add 1 to the total amount of graded assignments
                System.out.println("Assignment graded");
                p.inventory().removeItem(this);//removes this assignment from the player's inventory
                
                //if you have graded 10 assignments; you win and quit the game
                if(p.getGradedAssignments() >= 2){
                    System.out.println("you won");
                    //quit game
                    Quit.quit(); //quit the game
                }

            }
        }else{
            //this will be printed if you don't have enough energy
            System.out.println("You do not have enough energy");
        }
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }

}
