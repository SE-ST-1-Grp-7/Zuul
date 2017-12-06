package business;

/**
 *
 * @author Magnus Mortensen & Robin Petersen
 */
public class Door extends Furniture {

    private String direction;
    private String doorImage = "/textures/door2.png"; // Path to texture.
    private RoomManager roomManager;
    private boolean isLocked;

    public Door(int x, int y, int width, int height,
            Room currentRoom, String imagePath, boolean isLocked) {

        // Pass arguments to superclass.
        super(x, y, width, height, currentRoom,
                "Door", // Item name.
                "This is a door, maybe you should open it");  // Item description.
        super.setEntityImage(imagePath);
        this.isLocked = isLocked;
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
    public void onInteract(Person p) {
        if (p instanceof Player) {
            if (this.isLocked == true && ((Player) p).getHasKey()) {
                System.out.println("you unlock the door and go through");
                goThroughDoorMethod((Player) p);
            } else if (this.isLocked) {
                System.out.println("the door is locked,"
                        + " you need a key to open it");
            } else {
                goThroughDoorMethod((Player) p);
            }
        } else if (p instanceof Student) {
            if (!this.isLocked) {
                goThroughDoorMethod2((Student) p);
            }
        }

    }

    private void goThroughDoorMethod(Player player) {
        //Place tempItem if it exists
        if (player.getTempItem() != null) { 
            roomManager.getCurrentRoom().setEntityWithXY(
                    player.getX(), player.getY(), player.getTempItem());
            player.setTempItem(null);
        } else { // else set prev position to null
            roomManager.getCurrentRoom().setEntityWithXY(
                    player.getX(), player.getY(), null);
        }

        useDoor(player, this.roomManager);
        roomManager.getCurrentRoom().setEntity(player);
    }

    private void goThroughDoorMethod2(Student student) {
        student.getCurrentRoom().setEntityWithXY(
                student.getX(), student.getY(), null);
        useDoor(student, this.roomManager);
        student.getCurrentRoom().setEntity(student);
    }

    /**
     * method for using a door
     *
     * @param person
     * @param roomManager
     */
    public void useDoor(Person person, RoomManager roomManager) {
        if (person.getX() == getX() && getY() > 5) {
            this.direction = "south";
            setPersonToNewRoom(person, roomManager, this.direction);
            person.setY(1);
            person.setX(getX());
        } else if (person.getX() == getX() && getY() < 5) {
            this.direction = "north";
            setPersonToNewRoom(person, roomManager, this.direction);
            person.setY(8);
            person.setX(getX());
        } else if (person.getY() == getY() && getX() > 5) {
            this.direction = "east";
            setPersonToNewRoom(person, roomManager, this.direction);
            person.setX(1);
            person.setY(getY());
        } else if (person.getY() == getY() && getX() < 5) {
            this.direction = "west";
            setPersonToNewRoom(person, roomManager, this.direction);
            person.setX(8);
            person.setY(getY());
        }
    }

    /**
     * this method sets the player's position to the room in the specified
     * direction
     *
     * @param person
     * @param roomManager
     * @param direction
     */
    private void setPersonToNewRoom(Person person, RoomManager roomManager,
            String direction) {
        person.setCurrentRoom(getCurrentRoom().getExit(direction));
        if (person instanceof Player) {
            roomManager.setCurrentRoom(getCurrentRoom().getExit(direction));
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
