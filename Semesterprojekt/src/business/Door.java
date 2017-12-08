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

     /**
     * Constructor for Door class.
     * 
     * @param x                 int, horizontal position in room grid.
     * @param y                 int, vertical position in room grid.
     * @param width             int, pixel width of adderall.
     * @param height            int, pixel height of adderall.
     * @param currentRoom       Room, currently in this room.
     * @param imagePath         string, path to get the door image.
     * @param isLocked          boolean, check if the door is locked or not
     */
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

    /**
     * Override; on interact with door. Checks if Person is an instance of 
     * player, if the door is locked, then the player needs a key
     * to go through it. When the door is not locked, then the 
     * goThroughDoormethod is called for both player and student.
     * 
     * @param p  Person, this means all subclasses of person can interact
     * with doors.
     */
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
    /**
     * Method for going for through doors for player object.
     * 
     * @param player        Player, only the player object will move.
     */
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
    
    /**
     * Method for going for through doors for student objects.
     * 
     * @param student        Student, only the student object will move.
     */
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
    /**
     * Override; Retreive x coordinate for door object.
     * 
     * @return int, horizontal position in room grid.
     */
    @Override
    public int getX() {
        return super.getX();
    }
    
    /**
     * Override; Retreive y coordinate for door object.
     * 
     * @return int, vertical position in room grid.
     */
    @Override
    public int getY() {
        return super.getY();
    }

}
