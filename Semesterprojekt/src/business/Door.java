package business;

/**
 *
 * @author Magnus Mortensen
 */
public class Door extends Furniture {
    
    private String direction;
    private String doorImage = "/texture/Door.png"; // Path to texture.

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
    }
    
    public void onInteract(Player goPlayer, RoomManager roomManager) {
        goPlayer.setCurrentRoom(currentRoom.getExit(direction));
        roomManager.setCurrentRoom(currentRoom.getExit(direction));
        
        switch (goPlayer.x) {
            
            case 1:                
                if (goPlayer.getX() == getX());
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
