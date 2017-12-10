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
            int width,
            int height,
            Room currentRoom) {

        // Pass arguments to superclass
        super(id,
                x, // X grid position in room.
                y, // Y grid position in room.
                width, // Pixel width.
                height, // Pixel height.
                currentRoom, // Placed in this room.
                "Sawed-off", // Name of item.
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
