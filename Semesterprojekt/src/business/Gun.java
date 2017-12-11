package business;

/**
 *
 * @author J
 */
public class Gun extends Item {

    private String gunImage = "/textures/shotgun.png";
    public Gun(String id,
            int x,
            int y,
            Room currentRoom) {

        // Pass arguments to superclass
        super(id,
                x, // X grid position in room.
                y, // Y grid position in room.
                currentRoom, // Placed in this room.
                "Shotgun", // Name of item.
                "A sawed-off shotgun!", // Description of item.
                5);                          // Weight of item.

        // Pass path of texture to superclass.
        super.setEntityImage(gunImage);
    }

    @Override
    public boolean use(Player p) {
        p.setEnergy(-69);
        return true;
    }

}
