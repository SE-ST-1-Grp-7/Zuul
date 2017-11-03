package worldofzuul.items;

import worldofzuul.People.Player;
import worldofzuul.Quit;
import static worldofzuul.Quit.quit;
import worldofzuul.interfaces.IConsumable;
import worldofzuul.userCommand.Command;

/**
 *
 * @author Robin & Søren
 */
public class Assignment extends Item implements IConsumable {

    public Assignment() {
        super.setName("Assignment");
        super.setDescription("An assignment you can grade");
        super.setWeight(1);
    }

    @Override
    public void use(Player p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consume(Player p) {
        if (p.getEnergy() >= 20) {
            for (int i = 0; i < 5; i++) {
                p.setEnergy(p.getEnergy() - (20 / 5));
                p.setAssignmentProgress(p.getAssignmentProgress() + 20);
                System.out.println("Energy = " + p.getEnergy() + " and assignmentProgress = " + p.getAssignmentProgress());
            }
            if (p.getAssignmentProgress() >= 100) {
                p.setAssignmentProgress(0);
                p.setGradedAssignments(p.getGradedAssignments() + 1);
                System.out.println("Assignment graded");
                p.removeItemFromIntevtory(this);
                
                if(p.getGradedAssignments() >= 10){
                    System.out.println("you won");
                    //quit game
                    Quit.quit();
                }

            }
        }else{
            System.out.println("You do not have enough energy");
        }
    }

}
