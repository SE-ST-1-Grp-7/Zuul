package worldofzuul.Furniture;

import java.awt.Graphics;

/**
 *a subclass of furniture that makes a chair
 * @author Robin & Rasmus Willer
 */
public class Chair extends Furniture /*implements Interactable*/{
    
    public Chair(float x, float y, int width, int height, String chairName, String chairDescription){
        super(x, y, width, height, chairName, chairDescription);//a call to the super constructor - sets the name and description
    }
    
    /**
     * method for sitting in the chair
     */
    public void sitInChair(){
        //not implementet yet
    }
    
    /**
     * method for standing up again after sitting in the chair
     */
    public void standUp(){
        //not implementet yet
    }
    
    // GETTERS & SETTERS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }
    
}
