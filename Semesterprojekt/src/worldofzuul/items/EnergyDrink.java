
package worldofzuul.items;

import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class EnergyDrink extends Item implements IConsumable{
    private int energyRestore = 25;
    public EnergyDrink() {
        super.setName("Energy Drink");
    }

    @Override
    public void consume() {
        
    }

}
