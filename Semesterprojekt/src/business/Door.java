package business;

/**
 *
 * @author Magnus Mortensen & Robin Petersen
 */
public class Door extends Furniture {

    private String direction;
    private String doorImage = "/textures/door2.png"; // Path to texture.
    private Player player;
    private RoomManager roomManager;

    public Door(int x, int y, int width, int height, String direction,
            Room currentRoom, String imagePath) {

        // Pass arguments to superclass.
        super(x, y, width, height, currentRoom, 
                "Door", // Item name.
                "This is a door, maybe you should open it");  // Item description.
        super.setEntityImage(imagePath);
        this.direction = direction;
    }

    /**
     * settermethod: mostly used to give the useDoor and onInteract methods
     * acces to the player
     *
     * @param p
     */
    public void setPlayer(Player p) {
        this.player = p;
    }

    /**
     * setter method: mostly used to give the useDoor and onInteract methods
     * acces to the roomManager
     *
     * @param rm
     */
    public void setRoomManager(RoomManager rm) {
        this.roomManager = rm;
    }

    @Override
    public void onInteract() {
        roomManager.getCurrentRoom().setEntityWithXY(this.player.getX(), this.player.getY(), null);
        useDoor(this.player, this.roomManager);
        roomManager.getCurrentRoom().setEntity(this.player);
    }

    /**
     * method for using a door
     *
     * @param goPlayer
     * @param roomManager
     */
    public void useDoor(Player goPlayer, RoomManager roomManager) {
        goPlayer.setCurrentRoom(getCurrentRoom().getExit(direction));
        roomManager.setCurrentRoom(getCurrentRoom().getExit(direction));
        if (goPlayer.getX() == getX() && getY() > 5) {
            goPlayer.setY(1);
            goPlayer.setX(getX());
        } else if (goPlayer.getX() == getX() && getY() < 5) {
            goPlayer.setY(8);
            goPlayer.setX(getX());
        } else if (goPlayer.getY() == getY() && getX() > 5) {
            goPlayer.setX(1);
            goPlayer.setY(getY());
        } else if (goPlayer.getY() == getY() && getX() < 5) {
            goPlayer.setX(8);
            goPlayer.setY(getY());
        }
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

}
