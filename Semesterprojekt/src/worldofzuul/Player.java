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
    private String name;
    

    public Player(String name, int age, String gender, int speed) {
        super.setPersonName(name);
        super.setPersonAge(age);
        super.setPersonGender(gender);
        super.setSpeed(speed);
        this.energy = 100;
        this.energyCap = 100;
        this.fatigue = 0;
        this.fatigueCap = 100; 
    }
    
    public String getName() {
        return this.name;
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
    
    Player peter = new Player("Hans", 22, "Male", 10);
    System.out.println(getName(peter));
}
