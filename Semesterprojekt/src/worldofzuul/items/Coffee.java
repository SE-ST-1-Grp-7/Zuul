
package worldofzuul.items;
import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer & Rasmus Willer
 */
public class Coffee extends Item implements IConsumable {
    private final int ENERGY_RESTORE = 15;
    public Coffee(Link link,
                    float x,
                    float y,
                    int width,
                    int height) {
        super(link,
                x,
                y,
                width,
                height,
                "Coffee",
                "A cup of coffee. Yum!",
                5);
    }
    
    @Override
    public void consume(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
    }
    
    @Override
    public void use(Player p) {
        consume(p);
    }
    
    // GAME LOOP METHODS
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }

}
