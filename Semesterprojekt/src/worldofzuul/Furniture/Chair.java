/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.Furniture;

/**
 *a subclass of furniture that makes a chair
 * @author Robin
 */
public class Chair extends Furniture /*implements Interactable*/{
    
    /**
     * constructor for a chair
     * @param chairName
     * @param chairDescription 
     */
    public Chair(String chairName, String chairDescription){
        super(chairName, chairDescription);//a call to the super constructor - sets the name and description
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
