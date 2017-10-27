
package worldofzuul.items;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class Coffee extends Item implements IConsumable {
    private int energyRestore = 15;
    public Coffee() {
        super.setName("Coffee");
        super.setDescription("A cup of coffee. Yum!");
        super.setWeight(5);
    }
    @Override
    public void consume(Player p) {
        p.setEnergy(energyRestore + p.getEnergy());
    }
    @Override
    public void use(Player p) {
        consume(p);
    }

}
