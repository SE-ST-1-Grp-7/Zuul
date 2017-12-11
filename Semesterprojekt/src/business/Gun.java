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
                x,
                y,
                currentRoom,
                // Name of item.
                "Shotgun",
                // Description of item.
                "A sawed-off shotgun!");

        // Pass path of texture to superclass.
        super.setEntityImage(gunImage);
    }

    @Override
    public boolean use(Player p) {
        p.setEnergy(-69);
        return true;
    }

}
