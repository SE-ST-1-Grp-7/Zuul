package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author Rasmus Willer & Søren Bendtsen & Jonas Bjørstorp
 */
public class RoomManager {
    private Room currentRoom;
    private static HashMap<String, Room> roomlist = new HashMap<>();
    private static HashMap<String, Tile> tiles = new HashMap<>();
    private static HashMap<String, String[][]> tileMap = new HashMap<>();

    public RoomManager() {
        tileBase();         // Connect tile ID with texture file path.
        loadTiles();        // Load tile ID from file.
        createRooms();      // Instantiate rooms and define exit points.
        addTilesToRooms();  // Add tiles to all grid positions in rooms.
    }

    /**
     * Method declares and configure the rooms of the game.
     */
    private void createRooms() {
        // Declare room objects.

        // Instantiate the rooms and their descriptions
        roomlist.put("garden", new Room(
                "outside in the lovely garden... smells lovely of roses",
                "garden"));
        roomlist.put("relaxing room", new Room(
                "in a nice and cozy relaxing room", "relaxing room"));
        roomlist.put("teacher room", new Room(
                "in your own room, finaly some peace...", "teacher room"));
        roomlist.put("pub", new Room("in the campus pub", "pub"));
        roomlist.put("outside", new Room(
                "outside the main entrance of the university", "outside"));
        roomlist.put("hallway 1", new Room(
                "moving along the hallway", "hallway 1"));
        roomlist.put("dininghall", new Room(
                "in the dining hall, time to nom!", "dinninghall"));
        roomlist.put("hallway 2", new Room(
                "moving along the hallway", "hallway 2"));
        roomlist.put("lecturehall 1", new Room(
                "in a lecturehall, the lights are flickering...",
                "lecturehall 1"));
        roomlist.put("hallway 3", new Room(
                "moving along the hallway", "hallway 3"));
        roomlist.put("lecturehall 2", new Room(
                "in a lecturehall, everything is working... weird...",
                "lecturehall2"));
        roomlist.put("toilet", new Room("pooping", "toilet"));

        /* Define the exit-waypoints:
           From 'garden' Room instance. */
        roomlist.get("garden").setExit("east", roomlist.get("relaxing room"));
        roomlist.get("garden").setExit("south", roomlist.get("outside"));

        /* Define the exit-waypoints:
           From 'relaxing room' Room instance. */
        roomlist.get("relaxing room").setExit("west", roomlist.get("garden"));
        roomlist.get("relaxing room").setExit("south",
                roomlist.get("hallway 1"));

        /* Define the exit-waypoints:
           From 'teachers' room' Room instance. */
        roomlist.get("teacher room").setExit("south",
                roomlist.get("dininghall"));
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
        roomlist.get("hallway 1").setExit("north",
                roomlist.get("relaxing room"));
        roomlist.get("hallway 1").setExit("east", roomlist.get("dininghall"));

        /* Define exit-waypoints:
           From 'dining hall' Room instance. */
        roomlist.get("dininghall").setExit("north",
                roomlist.get("teacher room"));
        roomlist.get("dininghall").setExit("south",
                roomlist.get("lecturehall 2"));
        roomlist.get("dininghall").setExit("west", roomlist.get("hallway 1"));

        /* Define exit-waypoints:
           From 'hallway 2' Room instance. */
        roomlist.get("hallway 2").setExit("north", roomlist.get("pub"));
        roomlist.get("hallway 2").setExit("south", roomlist.get("toilet"));

        /* Define exit-waypoints:
           From 'lecture hall 1' Room instance. */
        roomlist.get("lecturehall 1").setExit("north", roomlist.get("outside"));
        roomlist.get("lecturehall 1").setExit("east",
                roomlist.get("hallway 3"));

        /* Define exit-waypoints:
           From 'hallway 3' Room instance. */
        roomlist.get("hallway 3").setExit("west",
                roomlist.get("lecturehall 1"));
        roomlist.get("hallway 3").setExit("east",
                roomlist.get("lecturehall 2"));

        /* Define exit-waypoints:
           From 'lecture hall 2' Room instance. */
        roomlist.get("lecturehall 2").setExit("west",
                roomlist.get("hallway 3"));
        roomlist.get("lecturehall 2").setExit("north",
                roomlist.get("dininghall"));
        roomlist.get("lecturehall 2").setExit("east",
                roomlist.get("toilet"));

        /* Define exit-waypoints:
           From 'toilet' Room instance. */
        roomlist.get("toilet").setExit("west", roomlist.get("lecturehall 2"));
        roomlist.get("toilet").setExit("north", roomlist.get("hallway 2"));

        /* Assign the Room object reference 'teacher room' as the
        currentRoom object.*/
        currentRoom = roomlist.get("garden");
    }
    
    /**
     * Creates a hashmap with new Tile object as value.
     * Connecting tile ID to a texture file.
     */
    public final void tileBase() {
        // Defining tile ID with a new instantiated tile object.
        tiles.put("ID23", new Tile("/textures/floor5.png", true));
        tiles.put("ID01", new Tile("/textures/floor17.png"));
        tiles.put("ID19", new Tile("/textures/floor11.png"));
        tiles.put("ID17", new Tile("/textures/floor6.png"));
        tiles.put("ID14", new Tile("/textures/floor2.png"));
        tiles.put("ID15", new Tile("/textures/floor4.png"));
        tiles.put("ID41", new Tile("/textures/wall2.png", true));
        tiles.put("ID42", new Tile("/textures/wall6.png", true));
        tiles.put("ID43", new Tile("/textures/wall1.png", true));
    }

    /**
     * Reads tile IDs from csv file.
     */
    public static void loadTiles() {
        // CSV file.
        String csvFile = "res/presets/roomTiles.csv";
        // Declare buffer to make readback from file simpler.
        BufferedReader fileReader = null;
        // Temp variable to hold one line at a time during the readback.
        String line = "";
        // Designate which split operator the content is split by.
        String splitBy = ",";
        // Temp variable to hold the String name of the room getting handled.
        String roomName = "";
        // Keeps track of which iteration of line in a room is being handled.
        int lineNo = 0;
        
        // Try catch for file IO operations. 
        try {
            // Instantiate buffer with file reader and file as parameter.
            fileReader = new BufferedReader(new FileReader(csvFile));
            // As long as there are more lines.
            while ((line = fileReader.readLine()) != null) {
                line = line.trim(); // Trim leading and tailing whitespaces.
                // If line is not empty.
                if (!"".equals(line)) {
                    // Split line into String array at defined split operator.
                    String[] segments = line.split(splitBy);
                    
                    // If reached new room in csv file, new entry in hashmap.
                    if ("-".equals(segments[0])) {
                        // Reset line count as it is a new room.
                        lineNo = 0;
                        // Parse the room name String from line.
                        roomName = segments[1].trim();
                        /* Create a two-dimensional array and make a HashMap
                           entry with room String as key and the two dimensional
                           array as the value. */
                        String[][] idList = new String[10][10];
                        tileMap.put(roomName, idList);
                        
                    // Otherwise assign ID to grid position in hashmap value[][] 
                    } else {
                        // Iterate through each parameter from split up line.
                        for (int i = 0; i < segments.length; i++) {
                            /* At the specified grid position assign the trimmed
                               paramter to the HashMap value. */
                            tileMap.get(roomName)[lineNo][i] =
                                    segments[i].trim();
                        }
                        // Increment line count after operation.
                        lineNo++;
                    }
                }
            }
        // Exception catching.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        // Finally try to close file IO operation.
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method runs through all rooms and each grid position within and assigns
     * a tile to the position.
     */
    public static void addTilesToRooms() {
        // For each room.
        for (String name : roomlist.keySet()) {
            // Iterate through y-coordinates (rows).
            for (int i = 0; i < tileMap.get(name).length; i++) {
                // Iterate through x-coordinates (column position).
                for (int j = 0; j < tileMap.get(name)[i].length; j++) {
                    // Which tile to be placed in room object grid position.
                    Tile tile = tiles.get(tileMap.get(name)[i][j]);
                    // Call setTile function and place tile object.
                    roomlist.get(name).setTile(i, j, tile);
                }
            }
        }
    }

    /**
     * Go to a different room and update current room.
     *
     * @param direction String, north/west/south/east direction to next room.
     * @param p         Player, the player object whos data requires updating.
     */
    public void goRoom(String direction, Player p) {
        /* Assign next room according to matching direction defined in the
           createRooms method */
        Room nextRoom = currentRoom.getExit(direction);

        // If there is no such direction print message. 
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } /* Update current room reference and print long description of new
           current room. */ 
        else {
            currentRoom = nextRoom;
            p.setX(5);
            p.setY(5);
            p.setCurrentRoom(nextRoom);
            //p.spawnPlayer();
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    // GETTERS & SETTERS
    
    /**
     * getRoom method returns the requested room object, based on a passed
     * argument matching a key in the roomlist HashMap.
     * 
     * @param name      String, name of the room object requested.
     * @return          Room, requested room object.
     */
    public Room getRoom(String name) {
        return roomlist.get(name);
    }
    
    /**
     * Retrieve hashmap with all room objects.
     * 
     * @return      HashMap, contains all rooms objects in the game.
     */
    public HashMap getRoomlist() {
        return roomlist;
    }
    
    /**
     * Change the attribute currentRoom to a new room object.
     * 
     * @param room  Room, new room to be the current room in focus.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    
    /**
     * Retrieve the room object of the current room in focus.
     * 
     * @return      Room, retrieve object of current room in focus.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }
}
