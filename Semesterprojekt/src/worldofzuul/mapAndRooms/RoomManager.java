package worldofzuul.mapAndRooms;

import java.awt.Graphics;
import worldofzuul.userCommand.Command;
import java.util.HashMap;
import worldofzuul.Link;
import worldofzuul.People.Player;
import worldofzuul.PrintOut;


/**
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class RoomManager {
    private Link link;
    private Room currentRoom;
    private static HashMap<String, Room> roomlist;
    
    public RoomManager(Link link) {
        this.link = link;
        createRooms();
        
    }

    /**
     * Method declares and configure the rooms of the game.
     */
    private void createRooms() {
        // Declare room objects.

        // Instantiate the rooms and their descriptions
        roomlist = new HashMap<String, Room>();
        roomlist.put("garden",new Room("outside in the lovely garden... smells lovely of roses"));
        roomlist.put("relaxing room",new Room("in a nice and cozy relaxing room"));
        roomlist.put("teacher room", new Room("in your own room, finaly some peace..."));
        roomlist.put("pub", new Room("in the campus pub"));
        roomlist.put("outside", new Room("outside the main entrance of the university"));
        roomlist.put("hallway 1", new Room("moving along the hallway"));
        roomlist.put("dininghall", new Room("in the dining hall, time to nom!"));
        roomlist.put("hallway 2", new Room("moving along the hallway"));
        roomlist.put("lecturehall 1", new Room("in a lecturehall, the lights are flickering..."));
        roomlist.put("hallway 3", new Room("moving along the hallway"));
        roomlist.put("lecturehall 2", new Room("in a lecturehall, everything is working... weird..."));
        roomlist.put("toilet", new Room("pooping"));
        

        /* Define the exit-waypoints:
           From 'garden' Room instance. */
        roomlist.get("garden").setExit("east", roomlist.get("relaxing room"));
        roomlist.get("garden").setExit("south", roomlist.get("outside"));

        /* Define the exit-waypoints:
           From 'relaxing room' Room instance. */
        roomlist.get("relaxing room").setExit("west", roomlist.get("garden"));
        roomlist.get("relaxing room").setExit("south", roomlist.get("hallway 1"));
       
        /* Define the exit-waypoints:
           From 'teachers' room' Room instance. */
        roomlist.get("teacher room").setExit("south", roomlist.get("dininghall"));
        roomlist.get("teacher room").setExit("east", roomlist.get("pub"));

        /* Define exit-waypoints:
           From 'pub' Room instance. */
        roomlist.get("pub").setExit("west", roomlist.get("teacher room"));
        roomlist.get("pub").setExit("south", roomlist.get("hallway 2"));
       
        /* Define exit-waypoints:
           From 'outside' Room instance. */
        roomlist.get("outside").setExit("north", roomlist.get("garden"));
        roomlist.get("outside").setExit("south", roomlist.get("lecturehall 1"));
      
        /* Define exit-waypoints:
           From 'hallway_1' Room instance. */
        roomlist.get("hallway 1").setExit("north", roomlist.get("relaxing room"));
        roomlist.get("hallway 1").setExit("east", roomlist.get("dininghall"));
        
        /* Define exit-waypoints:
           From 'dining hall' Room instance. */
        roomlist.get("dininghall").setExit("north", roomlist.get("teacher room"));
        roomlist.get("dininghall").setExit("south", roomlist.get("lecturehall 2"));
        roomlist.get("dininghall").setExit("west", roomlist.get("hallway 1"));
        
        /* Define exit-waypoints:
           From 'hallway 2' Room instance. */
        roomlist.get("hallway 2").setExit("north", roomlist.get("pub"));
        roomlist.get("hallway 2").setExit("south", roomlist.get("toilet"));

        /* Define exit-waypoints:
           From 'lecture hall 1' Room instance. */
        roomlist.get("lecturehall 1").setExit("north", roomlist.get("outside"));
        roomlist.get("lecturehall 1").setExit("east", roomlist.get("hallway 2"));

        /* Define exit-waypoints:
           From 'hallway 3' Room instance. */
        roomlist.get("hallway 3").setExit("west", roomlist.get("lecturehall 1"));
        roomlist.get("hallway 3").setExit("east", roomlist.get("lecturehall 2"));

        /* Define exit-waypoints:
           From 'lecture hall 2' Room instance. */
        roomlist.get("lecturehall 2").setExit("west", roomlist.get("hallway 3"));
        roomlist.get("lecturehall 2").setExit("north", roomlist.get("dininghall"));
        roomlist.get("lecturehall 2").setExit("east", roomlist.get("toilet"));

        /* Define exit-waypoints:
           From 'toilet' Room instance. */
        roomlist.get("toilet").setExit("west", roomlist.get("lecturehall 2"));
        roomlist.get("toilet").setExit("north", roomlist.get("hallway 2"));
        
        // Assign the Room object reference 'teachers' room' as the currentRoom object.
        currentRoom = roomlist.get("teacher room");
    }
    public HashMap getRoomlist() {
        return roomlist;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Go to a different room and update current room.
     *
     * @param command String argument of user command input.
     */
    public void goRoom(Command command, Player p) {
        // If no direction after go-command, print line and return. 
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        // Get second parsed command word and assign it to String variable.
        String direction = command.getSecondWord();

        /* Assign next room according to matching direction defined in the
           createRooms method */
        Room nextRoom = currentRoom.getExit(direction);

        // If there is no such direction print message.
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } /* Update current room reference and print long description of new
           current room. */ else {
            currentRoom = nextRoom;
            p.setX(0);
            p.setY(0);
            p.setCurrentRoom(nextRoom);
            //p.spawnPlayer();
            System.out.println(currentRoom.getLongDescription());
            PrintOut.displayRoom(currentRoom);
        }
    }
    
    // GAME LOOP METHODS
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        
    }
}
