package worldofzuul.People;

import java.util.ArrayList;
import worldofzuul.items.Inventory;
import worldofzuul.items.Item;

/** Player class - subclass of the Person class
 * Used to instantiate a Player
 * @author Gruppe 7, Robin & Niclas
 */
public class Player extends Person {
    private int energy; //current energy level
    private int energyCap; //the max amount of energy
    private int fatigue; //the current fatiuge level
    private int fatigueCap; //the max amount of fatiuge
    private Inventory inventory; // the player's inventory
    private int gradedAssignments;
    private int assignmentProgress;
    
    
/**
 * a constructor for making a player
 * @param name
 * @param speed 
     * @param x 
     * @param y 
 */
    public Player(String name, int speed, int x, int y) {
        super(name, speed, x, y); //a call to the super constructor (in Person)
        this.energy = 100; //the current energy level
        this.energyCap = 100; // the energy cap
        this.fatigue = 0; //the current amount of fatigue
        this.fatigueCap = 100; //the fatigue cap
        inventory = new Inventory(); //instanciate the inventory
        this.gradedAssignments = 0;
        this.assignmentProgress = 0;
        
    }
    
    /**
     * getter for the inventory
     * @return 
     */
    public ArrayList<Item> getInventory(){
        return inventory.getInventory(); //we call the getInventory() method from the inventory object and return it to the player
    }
    
    /**
     * getter for the current energy level
     * @return 
     */
    public int getEnergy() {
        return this.energy;
    }
    
    /**
     * setter for the current energy level
     * @param energy 
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    
    /**
     * getter for the energy cap
     * @return 
     */
    public int getEnergyCap() {
        return this.energyCap;
    }
    
    /**
     * setter for energyCap
     * @param energyCap 
     */
    public void setEnergyCap(int energyCap) {
        this.energyCap = energyCap;
    }
    
    /**
     * getter for the current fatiuge
     * @return 
     */
    public int getFatigue() {
        return this.fatigue;
    }
    
    /**
     * setter for the current fatiuge
     * @param fatigue 
     */
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
    
    /**
     * getter for the fatiuge cap
     * @return 
     */
    public int getFatigueCap() {
        return this.fatigueCap;
    }
    
    /**
     * setter for fatiugeCap
     * @param fatigueCap 
     */
    public void setFatigueCap(int fatigueCap) {
        this.fatigueCap = fatigueCap;
    } 
    
    public int getGradedAssignments(){
        return this.gradedAssignments;
    }
    
    public void setGradedAssignments(int assignment){
        this.gradedAssignments = assignment;
    }

    public int getAssignmentProgress() {
        return this.assignmentProgress;
    }

    public void setAssignmentProgress(int assignmentProgress) {
        this.assignmentProgress = assignmentProgress;
    }
    
    
    
}
