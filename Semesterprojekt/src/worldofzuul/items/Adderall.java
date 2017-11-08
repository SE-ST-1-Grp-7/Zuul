package worldofzuul.items;

import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.People.Player;
import worldofzuul.interfaces.IConsumable;

/**
 *
 * @author J & Rasmus Willer
 */
public class Adderall extends Item implements IConsumable {
    private final int ENERGY_RESTORE = 70;
    private final int ENERGY_CAP_INCREASE = 20;
    public Adderall(Link link,
                       float x,
                       float y,
                       int width,
                       int height) {
        super(link,
                x,
                y,
                width,
                height,
                "Adderall",
                "Adderall - Energy restore and cap increase. Yay!",
                1);
        super.setName("Adderall");
        super.setDescription("Adderall description");
        super.setWeight(1);
    }

    @Override
    public void consume(Player p) {
        p.setEnergy(ENERGY_RESTORE + p.getEnergy());
        p.setEnergyCap(p.getEnergyCap() + ENERGY_CAP_INCREASE);
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
