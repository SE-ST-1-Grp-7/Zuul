package business;

/**
 *
 * @author Magnus Mortensen & Robin Petersen
 */
public class Door extends Furniture {

    private String direction;
    private String doorImage = "/texture/door1.png"; // Path to texture.
    private Player player;
    private RoomManager roomManager;

    public Door(int x,
            int y,
            int width,
            int height,
            String direction,
            Room currentRoom) {

        // Pass arguments to superclass.
        super(x,
                y,
                width,
                height,
                currentRoom,
                "Door", // Item name.
                "This is a door, maybe you should open it");  // Item description.
        this.direction = direction;
        super.setEntityImage(doorImage);
    }
    
    public void setPlayer(Player p){
        this.player = p;
    }
    
    public void setRoomManager(RoomManager rm){
        this.roomManager = rm;
    }
    
    @Override
    public void onInteract() {
        useDoor(this.player, this.roomManager);
    }

    public void useDoor(Player goPlayer, RoomManager roomManager) {
        goPlayer.setCurrentRoom(currentRoom.getExit(direction));
        roomManager.setCurrentRoom(currentRoom.getExit(direction));

        switch (goPlayer.getX()) {

            case 1:
                if (goPlayer.getX() == getX() && getY() > 5);
                goPlayer.setY(8);
                goPlayer.setX(getX());
                break;
            case 2:
                if (goPlayer.getX() == getX() && getY() < 5);
                goPlayer.setY(1);
                goPlayer.setX(getX());
                break;
            case 3:
                if (goPlayer.getY() == getY() && getX() > 5);
                goPlayer.setX(1);
                goPlayer.setY(getY());
                break;
            case 4:
                if (goPlayer.getY() == getY() && getX() < 5);
                goPlayer.setX(8);
                goPlayer.setY(getY());
                break;
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
