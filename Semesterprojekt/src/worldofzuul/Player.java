package worldofzuul;

/** Player class - subclass the Person class
 * Used to instantiate a Player
 *
 * @author Gruppe 7
 */
public class Player extends Person {
    private int energy;
    private int energyCap;
    private int fatigue;
    private int fatigueCap;
    

    public Player() {
        // isnt present on uml diagram but will most likely be implemented
    }
    public int getEnergy() {
        return this.energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getEnergyCap() {
        return this.energyCap;
    }
    
    public int getFatigue() {
        return this.fatigue;
    }
    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }
    public int getFatigueCap() {
        return this.fatigueCap;
    }
}
