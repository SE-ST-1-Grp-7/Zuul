package business;

import java.util.HashMap;

/**
 * Manager of rooms and instantiates them.
 * 
 * @author Rasmus Willer & Søren Bendtsen & Jonas Bjørstorp
 */
public class RoomManager {
    private Room currentRoom;
    private HashMap<String, Room> roomlist = new HashMap<>();
    private HashMap<String, Tile> tiles = new HashMap<>();
    private HashMap<String, String[][]> tileMap;

    /**
     * Constructor for RoomManager. Tile data is given as parameter and the
     * rooms are instantiated and tiles are added to the rooms.
     * 
     * @param tileMap   HashMap< String, String[][] >, key is name of the room,
     *                  value is data in the 10x10 room grid.
     */
    public RoomManager(HashMap<String, String[][]> tileMap) {
        // Assign tile data to local collection.
        this.tileMap = tileMap;
        // Connect tile ID with texture file path.
        tileBase();
        // Instantiate rooms and define exit points.
        createRooms();
        // Add tiles to all grid positions in rooms.
        addTilesToRooms();
    }

    /**
     * Method declares and configure the rooms of the game.
     */
    private void createRooms() {
        // Instantiate the rooms and their descriptions
        roomlist.put("garden", new Room("garden"));
        roomlist.put("relaxing room", new Room("relaxing room"));
        roomlist.put("teacher room", new Room("teacher room"));
        roomlist.put("pub", new Room("pub"));
        roomlist.put("outside", new Room("outside"));
        roomlist.put("hallway 1", new Room("hallway 1"));
        roomlist.put("dininghall", new Room("dininghall"));
        roomlist.put("hallway 2", new Room("hallway 2"));
        roomlist.put("lecturehall 1", new Room("lecturehall 1"));
        roomlist.put("hallway 3", new Room("hallway 3"));
        roomlist.put("lecturehall 2", new Room("lecturehall 2"));
        roomlist.put("toilet", new Room("toilet"));

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
        roomlist.get("dininghall").setExit("east",
                roomlist.get("hallway 2"));
        roomlist.get("dininghall").setExit("west", roomlist.get("hallway 1"));

        /* Define exit-waypoints:
           From 'hallway 2' Room instance. */
        roomlist.get("hallway 2").setExit("north", roomlist.get("pub"));
        
        roomlist.get("hallway 2").setExit("west", roomlist.get("dininghall"));
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
        tiles.put("ID01", new Tile("/textures/floor17.png"));
        tiles.put("ID11", new Tile("/textures/floor19.png"));
        tiles.put("ID12", new Tile("/textures/floweredgrass.png"));
        tiles.put("ID13", new Tile("/textures/floor13.png"));
        tiles.put("ID14", new Tile("/textures/floor2.png"));
        tiles.put("ID15", new Tile("/textures/floor4.png"));
        tiles.put("ID17", new Tile("/textures/floor6.png"));
        tiles.put("ID18", new Tile("/textures/floor25.png"));
        tiles.put("ID19", new Tile("/textures/floor11.png"));
        tiles.put("ID23", new Tile("/textures/floor5.png", true));
        tiles.put("ID30", new Tile("/textures/HedgeWall10.png", true));
        tiles.put("ID31", new Tile("/textures/HedgeWall1.png", true));
        tiles.put("ID32", new Tile("/textures/HedgeWall2.png", true));
        tiles.put("ID33", new Tile("/textures/HedgeWall3.png", true));
        tiles.put("ID34", new Tile("/textures/HedgeWall4.png", true));
        tiles.put("ID35", new Tile("/textures/HedgeWall5.png", true));
        tiles.put("ID36", new Tile("/textures/HedgeWall6.png", true));
        tiles.put("ID37", new Tile("/textures/HedgeWall7.png", true));
        tiles.put("ID38", new Tile("/textures/HedgeWall8.png", true));
        tiles.put("ID39", new Tile("/textures/HedgeWall9.png", true));
        tiles.put("ID41", new Tile("/textures/wall2.png", true));
        tiles.put("ID42", new Tile("/textures/wall6.png", true));
        tiles.put("ID43", new Tile("/textures/wall1.png", true));
        tiles.put("ID44", new Tile("/textures/blackboard.png",true));
        tiles.put("ID45", new Tile("/textures/sink.png",true));
        tiles.put("ID46", new Tile("/textures/carpet.png"));
        tiles.put("ID47", new Tile("/textures/teachertable2.png",true));
        tiles.put("ID48", new Tile("/textures/floweredgrass.png",true));
        
    }

    /**
     * Method runs through all rooms and each grid position within and assigns
     * a tile to the position.
     */
    public void addTilesToRooms() {
        // For each room.
        for (String name : roomlist.keySet()) {
            // Iterate through y-coordinates (rows).
            for (int i = 0; i < tileMap.get(name).length; i++) {
                // Iterate through x-coordinates (column position).
                for (int j = 0; j < tileMap.get(name)[i].length; j++) {
                    // Which tile to be placed in room object grid position.
                    Tile tile = tiles.get(tileMap.get(name)[i][j]);
                    // Call setTile function and place tile object.
                    roomlist.get(name).setTile(j, i, tile);
                }
            }
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
     * @param room      Room, new room to be the current room in focus.
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