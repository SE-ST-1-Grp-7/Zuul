package business;

// IMPORTS

import Acq.IItem;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Entity Manager class. Keeps track of all the entities currently in the game.
 *
 * @author Rasmus Willer & Søren Bendtsen
 */
public class EntityManager {

    // Containers for the different type entities.
    private ArrayList<Item> itemlist = new ArrayList<>();
    private Player player;
    private ArrayList<Student> studentlist = new ArrayList<>();
    private ArrayList<Furniture> furniturelist = new ArrayList<>();
    
    // Container for entity IDs from CSV file.
    private HashMap<String, String[][]> entityCSV;
    private RoomManager rm;
    private String playerName;

    /**
     * Primary constructor for the EntityManager class.
     *
     * @param rm    RoomManager, used to assign entities to a specific room.
     */
    public EntityManager(RoomManager rm) {
        // Assign room manager object to class.
        this.rm = rm;
    }

    /**
     * Set up entities according to starting a new game.
     * 
     * @param playerName    String, select entities based on player name.
     * @param csvData       HashMap< String, String[][] >, key is the name of
     *                      the room, value is entity data in a 10x10 grid.
     */
    public void newGameEnts(String playerName,
            HashMap<String, String[][]> csvData) {
        //Assign entity data from csv to local attribute.
        this.entityCSV = csvData;
        // Assign player name to local attribute.
        this.playerName = playerName;
        // Instantiate entities defined in CSV.
        addEntitiesToRooms(playerName);
    }
    
    /**
     * Call for creation of entity. Based on player name, the entity will be
     * instantiated based on normal or easteregg edition of the game.
     * 
     * @param x             int, X coordinate grid position in room.
     * @param y             int, Y coordinate grid position in room.
     * @param name          String, name of the room entity is to be placed in.
     * @param IDnum         String, id of the instantiation version.
     */
    private void makeEnt(int x,
            int y,
            String name,
            String IDnum) {
        /* Call instantiation of entity based on ID number.
           If player name is Peter, instantiate easteregg edition objects. */
        if (this.playerName.equalsIgnoreCase("Peter")) {
            senpaiTypes(y, x, name, IDnum);
        // Else instantiate normal edition objects.
        } else {
            entityTypes(y, x, name, IDnum);
        }
    }

    /**
     * Spawn students in their respective active room, in the beginning of the
     * game.
     */
    public void showStudents() {
        for (Student s : studentlist) {
            s.getCurrentRoom().setEntity(s);
        }
    }

    /**
     * Spawn furnitures in their respective active room, in the beginning of the
     * game.
     */
    public void showFurniture() {
        for (Furniture d : furniturelist) {
            d.getCurrentRoom().setEntity(d);
        }
    }

    /**
     * Spawn items in their respective active room, at the beginning of the
     * game.
     */
    public void showItems() {
        for (Item i : itemlist) {
            i.getCurrentRoom().setEntity(i);
        }
    }

    /**
     * Removes items from rooms.
     */
    public void nullItems() {
        for (Item i : itemlist) {
            i.getCurrentRoom().setEntityWithXY(i.getX(), i.getY(), null);
        }
    }

    // SETTERS & GETTERS
    
    /**
     * Get player object.
     *
     * @return      Player, player object retrieved.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get student list.
     *
     * @return      ArrayList< Student >, student list to retrieve.
     */
    public ArrayList<Student> getStudentList() {
        return studentlist;
    }

    /**
     * Get furniture list.
     *
     * @return      ArrayList< Furniture >, furniture list to retrieve.
     */
    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }

    /**
     * Get item list.
     *
     * @return      ArrayList< Item >, item list to retrieve.
     */
    public ArrayList<Item> getItemList() {
        return itemlist;
    }

    // ENTITY MANAGEMENT METHODS

    /**
     * Iterate through all rooms and their position grids and call instantiation
     * of entities where CSV file had defined one to be placed.
     *
     * @param playerName    String, name of player.
     */
    public void addEntitiesToRooms(String playerName) {
        String IDnum;
        // Get HashMap of all rooms and iterate through it based on keys.
        HashMap<String, Room> roomlist = rm.getRoomlist();
        for (String name : roomlist.keySet()) {
            // Iterate through y-coordinates (rows) in each room.
            for (int i = 0; i < entityCSV.get(name).length; i++) {
                // Iterate through x-coordinates (column position).
                for (int j = 0; j < entityCSV.get(name)[i].length; j++) {
                    // ID number in the specific grid position.
                    IDnum = entityCSV.get(name)[i][j];
                    // If no ID continue to next iteration.
                    if (IDnum == null) {
                        continue;
                    }
                    // Call instantiation of entity based on ID number.
                    makeEnt(j, i, name, IDnum);
                }
            }
        }
        // Place entities in their rooms.
        showStudents();
        showFurniture();
        showItems();
    }

    /**
     * Instantiate entity objects based on ID number and add them to their
     * respective entity list.
     *
     * @param i             int, y-coordinate of grid position.
     * @param j             int, x-coordinate of grid position.
     * @param name          String, name of room for entity to 'spawn' in.
     * @param id            String, ID of the type of entity to be created.
     */
    public void entityTypes(int i,
            int j,
            String name,
            String id) {

        // Switch cases for what type of entity it is
        switch (id.toUpperCase()) {
            // Instance of player to be added.
            case "ID50":
                player = new Player(id,
                        j,
                        i,
                        this.playerName,
                        rm.getRoom(name),
                        this);
                this.rm.getRoom(name).setEntity(this.player);
                break;

            // Instance of white t-shirt, brunette student.
            case "ID51":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student1.png",
                        this));
                break;

            // Instance of red t-shirt, brunette student.
            case "ID52":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student2.png",
                        this));
                break;

            // Instance of green t-shirt, brunette student.
            case "ID53":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student3.png",
                        this));
                break;

            // Instance of white t-shirt, blond student.
            case "ID54":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student4.png",
                        this));
                break;

            // Instance of red t-shirt, blond student.
            case "ID55":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student5.png",
                        this));
                break;

            // Instance of green t-shirt, blond student.
            case "ID56":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student6.png",
                        this));
                break;

            // Instance of white t-shirt, black student.
            case "ID57":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student7.png",
                        this));
                break;

            // Instance of red t-shirt, black student.
            case "ID58":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student8.png",
                        this));
                break;

            // Instance of green t-shirt, black student.
            case "ID59":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student9.png",
                        this));
                break;

            // Instance of white t-shirt, asian student.
            case "ID60":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student10.png",
                        this));
                break;

            // Instance of red t-shirt, asian student.
            case "ID61":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student11.png",
                        this));
                break;

            // Instance of green t-shirt, asian student.
            case "ID62":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student12.png",
                        this));
                break;

            // Door, inner, vertical.
            case "ID63":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door2.png",
                        false));
                break;

            //Door, inner, vertical locked
            case "ID63L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door2.png",
                        true));
                break;

            // Door, inner, horizontal.
            case "ID64":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door1.png",
                        false));
                break;

            //Door, inner, horizontal, locked
            case "ID64L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door1.png",
                        true));
                break;

            // Door, outer, vertical, handle down.
            case "ID65":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door12.png",
                        false));
                break;

            // Door, outer, vertical handle down, locked
            case "ID65L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door12.png",
                        true));
                break;

            // Door, outer, vertical, handle up.
            case "ID66":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door11.png",
                        false));
                break;

            // Door, outer, vertical, handle up. locked
            case "ID66L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door11.png",
                        true));
                break;

            // Door, outer, horizontal, handle right.
            case "ID67":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door13.png",
                        false));
                break;

            // Door, outer, horizontal, handle right, locked.
            case "ID67L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door13.png",
                        true));
                break;

            // Door, outer, horizontal, handle left.
            case "ID68":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door14.png",
                        false));
                break;

            // Door, outer, horizontal, handle left, locked.
            case "ID68L":
                furniturelist.add(new Door(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/door14.png",
                        true));
                break;

            // Assignment item                
            case "ID69":
                itemlist.add(new Assignment(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;
            // Chair facing north.
            case "ID70":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/chair1.png"));
                break;

            // Chair facing west.    
            case "ID71":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/chair2.png"));
                break;
 
            // Chair facing south.    
            case "ID72":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/chair3.png"));
                break;

            // Chair facing east.
            case "ID73":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/chair4.png"));
                break;

            // Table furniture.
            case "ID74":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false, 0, "/textures/table1.png"));
                break;

            // Bookcase, left end, facing south.
            case "ID75":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase1.png"));
                break;

            // Bookcase, middle section, facing south, open book on top.
            case "ID76":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase2.png"));
                break;

            // Bookcase, right end, facing south.
            case "ID77":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase3.png"));
                break;

            // Bookcase, right end, facing north.
            case "ID78":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase4.png"));
                break;

            // Bookcase, middle section, facing north.
            case "ID79":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase5.png"));
                break;

            // Bookcase, left end, facing north.
            case "ID80":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase6.png"));
                break;

            // Bookcase, left end, facing south.
            case "ID81":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase7.png"));
                break;

            // Bookcase, middle section, facing south, open book on top.
            case "ID82":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase19.png"));
                break;

            // Bookcase, right end, facing south.
            case "ID83":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase9.png"));
                break;

            // Bookcase, right end, facing north.
            case "ID84":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase10.png"));
                break;

            // Bookcase, middle section, facing north.
            case "ID85":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase20.png"));
                break;

            // Bookcase, left end, facing north.
            case "ID86":
                furniturelist.add(new Bookcase(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bookcase12.png"));
                break;

            // Table, pooltable.
            case "ID87":

                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/pooltable1.png"));
                break;
                
            // Table pooltable.
            case "ID88":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/pooltable2.png"));
                break;

            // Key item, for unlocking doors.
            case "ID89":
                itemlist.add(new Key(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;

            // Coffee item.
            case "ID90":
                itemlist.add(new Coffee(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;

            // Adderall item.
            case "ID91":
                itemlist.add(new Adderall(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;

            // Bench facing west, top end part.
            case "ID92":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bench6.png"));
                break;

            // Bench facing west, bottom end part.
            case "ID93":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bench5.png"));
                break;

            // Bench facing east, bottom end part.
            case "ID94":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bench4.png"));
                break;

            // Bench facing east, top end part.
            case "ID95":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/bench3.png"));
                break;
                
            // Blackboard (check søren)  
            case "ID96":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/blackboard2.png"));
                break;
                
            // Instance of toilet. 
            case "ID97":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/toilet.png"));
                break;
            
            // Instance of teacher table.
            case "ID98":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/teachertable1.png"));
                break;
                
            // Instance of teacher table.
            case "ID99":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/teachertable2.png"));
                break;
                
            // Instance of teacher table.
            case "ID100":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/teachertable3.png"));
                break;
                
            // Instance of teacher chair.
            case "ID101":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/teacherchair1.png"));
                break;
                
            // Instance of teacher chair.
            case "ID102":
                furniturelist.add(new Chair(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/teacherchair2.png"));
                break;
                
            // Instance of Energy drink.
            case "ID103":
                itemlist.add(new EnergyDrink(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;
                
            // Instance of dinnertable.
            case "ID104":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/dinnertable1.png"));
                break;
                
            // Instance of dinnertable.
            case "ID105":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/dinnertable2.png"));
                break;
                

            // Instance of dinnertable.
            case "ID106":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/dinnertable3.png"));
                break;
                
            // Instance of dinnertable.
            case "ID107":
                furniturelist.add(new Table(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        0,
                        "/textures/dinnertable4.png"));
                break;

            // Instance of tutor.
            case "ID108":
                studentlist.add(new Tutor(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/tutor1.png",
                        this));
                break;

            // Instance of tutor.
            case "ID109":
                studentlist.add(new Tutor(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/tutor2.png",
                        this));
                break;

            // Instance of tutor.
            case "ID110":
                studentlist.add(new Tutor(id,
                        j,
                        i,
                        rm.getRoom(name),
                        "/textures/tutor3.png",
                        this));
                break;

            // Instance of gun.
            case "ID111":
                itemlist.add(new Gun(id,
                        j,
                        i,
                        rm.getRoom(name)));
                break;

            // In case the ID is not recognized.
            default:
                System.out.println("Error. Entity ID   " + id
                        + "   not defined.");
                break;
        }
    }

    /**
     * Senpai-edition. Instantiate entity objects based on ID number and add
     * them to their respective entity list.
     *
     * @param i         int, y-coordinate of grid position.
     * @param j         int, x-coordinate of grid position.
     * @param name      String, name of room for entity to 'spawn' in.
     * @param id        String, ID of the type of entity to be created.
     */
    public void senpaiTypes(int i,
            int j,
            String name,
            String id) {

        // Switch cases for what type of entity it is
        switch (id.toUpperCase()) {
            // Instance of peaceful kouhai (face covered by hair).
            case "ID51":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png",
                        this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID52":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png",
                        this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID53":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png",
                        this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID54":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png",
                        this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID55":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png",
                        this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID56":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png",
                        this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID57":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png",
                        this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID58":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png",
                        this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID59":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png",
                        this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID60":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png",
                        this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID61":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png",
                        this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID62":
                studentlist.add(new Student(id,
                        j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png",
                        this));
                break;

            /* In case the ID is not a special case, instantiate entity from
                the regular game edition. */
            default:
                entityTypes(i, j, name, id);
                break;
        }
    }

    // LOAD & SAVE METHODS
    
    /**
     * Method for gathering the data that must be saved during saving.
     *
     * @return      HashMap< String, ArrayList < ArrayList < String > > >,
     *              key is path to file, value is data.
     */
    public HashMap parseForSave() {
        // Instantiate the HashMap.
        HashMap<String, ArrayList<ArrayList<String>>> savePackage =
                new HashMap<>();

        // Place collected data about player in HashMap.
        savePackage.put("saveFiles\\SavePlayersTest.txt",
                savePlayers());
        
        // Place collected data about students in HashMap.
        savePackage.put("saveFiles\\SaveStudentTest.txt",
                saveStudents());

        // Place collected data about inventory in HashMap.
        savePackage.put("saveFiles\\SaveInventoryTest.txt",
                saveInventory());

        // Place collected data about items in HashMap.
        savePackage.put("saveFiles\\SaveItemsTest.txt",
                saveItems());

        // Return the HashMap with all the data collections to be saved.
        return savePackage;
    }

    /**
     * Parse data for saving items in the game.
     *
     * @return      ArrayList< ArrayList < String > >, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> saveItems() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> itemsData = new ArrayList<>();
        // Iterate through all items in the game and gather their data.
        for (Item item : itemlist) {
            // Sub-list to store the individual item data.
            ArrayList<String> itemData = new ArrayList<>();
            // Gather item ID.
            itemData.add(item.getID());
            // Gatherig item name.
            itemData.add(item.getName());
            // Gathering X grid position.
            itemData.add(String.valueOf(item.getX()));
            // Gathering Y grid position.
            itemData.add(String.valueOf(item.getY()));
            // Gathering the item's current room.
            itemData.add(item.getCurrentRoom().getName());

            // Add sub-list to main 2D list.
            itemsData.add(itemData);
        }
        // Return gathered data.
        return itemsData;
    }

    /**
     * Parse data for saving player's inventory status.
     *
     * @return      ArrayList< ArrayList < String > >, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> saveInventory() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> invenData = new ArrayList<>();
        /* Iterate through player's inventory and assign item name to sub-list
           before adding sub-list to the main 2D list. */
        for (IItem item : player.inventory().getInventory()) {
            ArrayList<String> itemData = new ArrayList<>();
            itemData.add(item.getName());
            invenData.add(itemData);
        }
        // Return gathered data.
        return invenData;
    }

    /**
     * Parse data for saving player's status.
     *
     * @return      ArrayList< ArrayList < String > >, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> savePlayers() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> playersData = new ArrayList<>();
        // Sub-list for holding the player's data.
        /* Not directly necessary, but this way it will work without making
           dedicated code for handling this type save data. */
        ArrayList<String> playerData = new ArrayList<>();
        
        // Gathering ID of player.
        playerData.add(player.getID());
        // Gathering X grid position.
        playerData.add(String.valueOf(player.getX()));
        // Gathering Y grid position.
        playerData.add(String.valueOf(player.getY()));
        // Gathering player's name.
        playerData.add(player.getName());
        // Gathering player's current room.
        playerData.add(String.valueOf(player.getCurrentRoom().getName()));
        // Gathering assignment progress.
        playerData.add(String.valueOf(player.getAssignmentProgress()));
        // Gathering assignment graded count.
        playerData.add(String.valueOf(player.getGradedAssignments()));
        // Gathering if player has key or not.
        playerData.add(String.valueOf(player.getHasKey()));
        // Gathering player energy level.
        playerData.add(String.valueOf(player.getEnergy()));
        // Gathering player energy capacity.
        playerData.add(String.valueOf(player.getEnergyCap()));
        // Gather time left from player attribute.
        playerData.add(String.valueOf(player.getTimeLeft()));

        // Add sub-list to main list.
        playersData.add(playerData);

        // Return parsed data.
        return playersData;
    }

    /**
     * Parse data for saving student's status.
     *
     * @return      ArrayList< ArrayList < String > >, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> saveStudents() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> studentsData = new ArrayList<>();
        // Iterate through all the students and collect the data.
        for (Student student : studentlist) {
            // Sub-list instantiation.
            ArrayList<String> studData = new ArrayList<>();
            
            // Gathering ID of student.
            studData.add(student.getID());
            // Gathering X grid position.
            studData.add(String.valueOf(student.getX()));
            // Gathering Y grid position.
            studData.add(String.valueOf(student.getY()));
            // Gathering name of current room.
            studData.add(student.getCurrentRoom().getName());
            // Gathering String version of boolean for has question for player.
            studData.add(String.valueOf(student.getHasQuestionToPlayer()));
            // Add sub-list to main list.
            studentsData.add(studData);
        }
        // Return parsed data for saving.
        return studentsData;
    }

    /**
     * Directing loading data to the different entity load methods.
     *
     * @param loadPackage HashMap< String, ArrayList < ArrayList < String > > >,
     *                    key is file path, value is 2D list with data.
     */
    public void parseLoading(HashMap<String, ArrayList<ArrayList<String>>> loadPackage) {
        // Retrieve list with save file paths.
        ArrayList<String> saveFiles = getSaveFiles();
        
        // Important player is loaded first.
        // Call player load with player data as parameter.
        loadPlayers(loadPackage.get(saveFiles.get(0)));
        
        // Call student load with student data as parameter.
        loadStudents(loadPackage.get(saveFiles.get(1)));

        // Call inventory load with inventory data as parameter.
        loadInventory(loadPackage.get(saveFiles.get(2)));

        // Call item load with item data as parameter.
        loadItems(loadPackage.get(saveFiles.get(3)));
    }

    /**
     * Load items from collected data. Iterate through load data and instantiate
     * items based on item name and parameters.
     *
     * @param data  ArrayList< ArrayList < String > >, 2D list with load data.
     */
    public void loadItems(ArrayList<ArrayList<String>> data) {
        // Set item allocations in rooms to null.
        nullItems();
        // Clear item list.
        itemlist.clear();

        // Iterate through data of all items for loading.
        for (ArrayList<String> itemData : data) {
            // If not empty, check for type of item.
            if (itemData.size() > 0) {
                // Instantiate item based on item ID and other data.
                makeEnt(// x position in room grid.
                        Integer.parseInt(itemData.get(2)),
                        // y position in room grid.
                        Integer.parseInt(itemData.get(3)),
                        // Room name.
                        itemData.get(4),
                        // ID of item.
                        itemData.get(0));
            }
        }
        showItems();
    }

    /**
     * Load inventory from data collected from file. Iterate through the data
     * and instantiate items according to item name and parameters.
     *
     * @param data  ArrayList< ArrayList < String > >, 2D list with load data.
     */
    public void loadInventory(ArrayList<ArrayList<String>> data) {
        try {
            // Clear inventory list.
            player.inventory().getInventory().clear();

            // Iterate through the load data.
            for (ArrayList<String> invenData : data) {
                if (invenData.size() > 0) {
                    
                    switch (invenData.get(0)) {
                        // If adderall, add to inventory list.
                        case "Adderall":
                            Adderall d = new Adderall("ID91",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(d);
                            break;

                        // If coffee, add to inventory list.
                        case "Coffee":
                            Coffee c = new Coffee("ID90",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(c);
                            break;

                        // If assignment, add to inventory list.
                        case "Assignment":
                            Assignment a = new Assignment("ID69",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(a);
                            break;

                        // If key, add to inventory list.
                        case "Key":
                            Key k = new Key("ID89",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(k);
                            break;

                        // If energy drink, add to inventory list.
                        case "Energy drink":
                            EnergyDrink e = new EnergyDrink("ID103",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(e);
                            break;
                            
                        // If gun, add to inventory list.
                        case "Shotgun":
                            Gun g = new Gun("ID111",
                                    0,
                                    0,
                                    null);
                            player.inventory().addItem(g);
                            break;
                            
                        // Ignore anything else.
                        default:
                            break;
                    }
                }
            }
        } catch (NullPointerException s) {
            System.out.println("Inventory is empty ");
        }
    }

    /**
     * Update player with load data. Parse retrieved data and assign the new
     * values.
     *
     * @param data  ArrayList< ArrayList < String > >, 2D list with loaded data.
     */
    public void loadPlayers(ArrayList<ArrayList<String>> data) {      
        // Iterate through the data.
        for (ArrayList<String> playerData : data) {
            // Assign loaded name to entity manager
            this.playerName = playerData.get(3);

            makeEnt(// x position in room grid.
                    Integer.parseInt(playerData.get(1)),
                    // y position in room grid.
                    Integer.parseInt(playerData.get(2)),
                    // Room name.
                    playerData.get(4),
                    // ID of item.
                    playerData.get(0));

            // Set assignment progress.
            player.setAssignmentProgress(Integer.parseInt(
                    playerData.get(5)));
            // Set graded assignment count.
            player.setGradedAssignments(Integer.parseInt(
                    playerData.get(6)));
            // Set boolean for if player has key.
            player.setHasKey(Boolean.parseBoolean(playerData.get(7)));
            // Set player's energy level.
            player.setEnergy(Integer.parseInt(playerData.get(8)));
            // Set player's energy capacity.
            player.setEnergyCap(Integer.parseInt(playerData.get(9)));
            // Set player's remaining time.
            player.setTimeLeft(Integer.parseInt(playerData.get(10)));
        }
        player.getCurrentRoom().setEntity(player);
        this.rm.setCurrentRoom(player.getCurrentRoom());
    }

    public void loadStudents(ArrayList<ArrayList<String>> data) {
        // Remove old students from rooms.
        for (Student student: studentlist) {
            student.getCurrentRoom().removeEntity(student);
        }
        // Clear the ArrayList.
        studentlist.clear();
        // Iterate through data for each student.
        for (ArrayList<String> studentData : data) {
            if (studentData.size() > 0) {
                makeEnt(// x position in room grid.
                        Integer.parseInt(studentData.get(1)),
                        // y position in room grid.
                        Integer.parseInt(studentData.get(2)),
                        // Room name.
                        studentData.get(3),
                        // ID of item.
                        studentData.get(0));
            }
        }
        showStudents();
    }

    /**
     * Retrieve list with save files's path.
     *
     * @return      ArrayList< String >, list with save files.
     */
    public ArrayList<String> getSaveFiles() {
        ArrayList<String> saveFiles = new ArrayList<>();
        // The order of this list is important!
        // ParseLoading method follows the order.
        saveFiles.add("saveFiles\\SavePlayersTest.txt");
        saveFiles.add("saveFiles\\SaveStudentTest.txt");
        saveFiles.add("saveFiles\\SaveInventoryTest.txt");
        saveFiles.add("saveFiles\\SaveItemsTest.txt");
        // Return list with file paths.
        return saveFiles;
    }
}