package worldofzuul.items;

import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Robin
 */
public class Assignment extends Item implements IConsumable{

    public Assignment(){
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
        if(p.getEnergy() >= 20){
            p.setEnergy(p.getEnergy()-20);
            p.setAssignmentProgress(p.getAssignmentProgress()+20);
                if (p.getAssignmentProgress()>=100)
                    p.setAssignmentProgress(0);
                    p.setGradedAssignments(p.getGradedAssignments()+1);
                        
                }
        
        p.setGradedAssignments(p.getGradedAssignments()+1);
        }
    }
    
}
