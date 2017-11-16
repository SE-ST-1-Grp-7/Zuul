package worldofzuul.Furniture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import worldofzuul.mapAndRooms.Room;

/**
 *a subclass of furniture that makes a chair
 * @author Robin & Rasmus Willer
 */
public class Chair extends Furniture /*implements Interactable*/{
    private Image chairImage = new Image("/texture/chair.png");  

    /**
     * Constructor with all the necessary parameters for the class
     * @param x                     int x coordinate
     * @param y                     int y coordinate
     * @param width                 int pixel width
     * @param height                int pixel height
     * @param currentRoom           Room currently in room ...
     * @param chairName             String Name of furniture
     * @param chairDescription      String Description of furniture
     */
    public Chair(int x,
            int y,
            int width,
            int height,
            Room currentRoom,
            String chairName,
            String chairDescription){
            
        // Pass arguments to superclass
        super(x,
                y,
                width,
                height,
                currentRoom,
                chairName,
                chairDescription);
        super.setEntityImage(chairImage);
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
}
