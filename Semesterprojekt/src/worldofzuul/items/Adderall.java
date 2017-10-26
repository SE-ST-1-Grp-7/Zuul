package worldofzuul.items;

import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author J
 */
public class Adderall extends Item implements IConsumable {
    int energyRestore = 70;
    int energyCapIncrease = 20;
    public Adderall() {
        super.setName("Adderall");
        super.setDescription("Adderall description");
        super.setWeight(1);
    }

    @Override
    public void consume(Player p) {
        p.setEnergy(energyRestore + p.getEnergy());
        p.setEnergyCap(p.getEnergyCap() + energyCapIncrease);
    }
}
