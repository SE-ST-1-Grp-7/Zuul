package worldofzuul.People;

import worldofzuul.items.Inventory;

/** Player class - subclass the Person class
 * Used to instantiate a Player
 *
 * @author Gruppe 7, Robin & Niclas
 */
public class Player extends Person {
    private int energy; //current energy level
    private int energyCap; //the max amount of energy
    private int fatigue; //the current fatiuge level
    private int fatigueCap; //the max amount of fatiuge
    private Inventory inventory = new Inventory();
    
/**
 * a constructor for making a player
 * @param name
 * @param speed 
 */
    public Player(String name, int speed) {
        super(name, speed);
        this.energy = 100;
        this.energyCap = 100;
        this.fatigue = 0;
        this.fatigueCap = 100; 
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
    
    
    
}
