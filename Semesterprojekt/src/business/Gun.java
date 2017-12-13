package business;

/**
 * Why is there a gun at school, luckily it only shoots the one trying to use
 * it.
 * 
 * @author J & Rasmus Willer
 */
public class Gun extends Item {
    private String gunImage = "/textures/shotgun.png";
    
    /**
     * Constructor of the gun class. Instantiates entity with parameters.
     * 
     * @param id                String, ID of specific instantiation.
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param currentRoom       Room, currently in this room.
     */
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
                "Shotgun");

        // Pass path of texture to superclass.
        super.setEntityImage(gunImage);
    }

    /**
     * Override; upon use, remove energy of player and change him.
     * 
     * @param p     Player, the player using it.
     * @return      boolean, used ot not.
     */
    @Override
    public boolean use(Player p) {
        // Remove energy from player.
        p.setEnergy(-69);
        // Set image to player.
        p.setImage("/textures/skeleton1.png");
        return true;
    }
}