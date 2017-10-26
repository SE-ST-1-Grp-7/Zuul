package worldofzuul.items;

import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class EnergyDrink extends Item implements IConsumable {

    private int energyRestore = 40;

    public EnergyDrink() {
        super.setName("Energy drink");
        super.setDescription("A can of monster. Yum!");
        super.setWeight(10);
    }
    @Override
    public void consume(Player  p) {
        p.setEnergy(energyRestore + p.getEnergy());
    }
    @Override
    public void use(Player p) {
        consume(p);
    }

}
