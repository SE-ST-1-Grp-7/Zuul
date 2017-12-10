package business;

// IMPORTS
import Acq.IItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Entity Manager classL. Keeps track of all the entities currently in the game.
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
    private static HashMap<String, String[][]> entityCSV = new HashMap<>();
    private static RoomManager rm;

    /**
     * Primary constructor for the EntityManager class.
     *
     * @param rm RoomManager, used to assign entities to a specific room.
     */
    public EntityManager(RoomManager rm) {
        this.rm = rm;                   // Assign room manager object to class.
        loadPresetEntities();           // Load entity IDs from CSV file.
        String playerName = "tempPlayerName";    // Temp define for player name.
        addEntitiesToRooms(playerName); // Instantiate entities defined in CSV.
        player.setName(playerName);
    }

    // ENTITY MANAGMENT METHODS
    /**
     * Add initialized student to the student list.
     *
     * @param s Student, student to be added to studet list.
     */
    public void addStudent(Student s) {
        studentlist.add(s);
    }

    /**
     * Despawn student.
     *
     * @param s Student, student that need to be removed from the game.
     */
    public void removeStudent(Student s) {
        studentlist.remove(s);
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

    /**
     * Add initialized furniture to the furniture list.
     *
     * @param f Furniture, to be placed in the furniture list.
     */
    public void addFurniture(Furniture f) {
        furniturelist.add(f);
    }

    /**
     * Remove furniture object from furniture list.
     *
     * @param f Furniture, to be removed from furniture list.
     */
    public void removeFurniture(Furniture f) {
        furniturelist.remove(f);
    }

    /**
     * Add initializedd item to the item list.
     *
     * @param i Item, to be placed in the item list.
     */
    public void addItem(Item i) {
        itemlist.add(i);
    }

    /**
     * Remove item object from item list.
     *
     * @param i Item, to be removed from item list.
     */
    public void removeItem(Item i) {
        itemlist.remove(i);
    }

    // SETTERS & GETTERS
    /**
     * Get player object.
     *
     * @return Player, player object retrieved.
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Replace player object.
     *
     * @param player Player, the new player object to replace previous.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get student list.
     *
     * @return ArrayList< Student >, student list to retrieve.
     */
    public ArrayList<Student> getStudentList() {
        return studentlist;
    }

    /**
     * Replace student list.
     *
     * @param studentlist ArrayList< Student >, student list to assign.
     */
    public void setStudentList(ArrayList<Student> studentlist) {
        this.studentlist = studentlist;
    }

    /**
     * Get furniture list.
     *
     * @return ArrayList< Furniture >, furniture list to retrieve.
     */
    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }

    /**
     * Replace furniture list.
     *
     * @param furniturelist ArrayList< Student >, furniture list to assign.
     */
    public void setFurnitureList(ArrayList<Furniture> furniturelist) {
        this.furniturelist = furniturelist;
    }

    /**
     * Get item list.
     *
     * @return ArrayList< Item >, item list to retrieve.
     */
    public ArrayList<Item> getItemList() {
        return itemlist;
    }

    /**
     * Replace item list.
     *
     * @param itemlist ArrayList< Item >, item list to assign.
     */
    public void setItemList(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    // LOAD ENTITIES FROM CSV
    /**
     * Read entity IDs from CSV file.
     */
    public static void loadPresetEntities() {
        // Path of CSV file.
        String csvFile = "res/presets/roomEntities.csv";
        // Declare a buffer for the file reader.
        BufferedReader fileReader = null;
        // Define a String to contain the data from a single line in the CSV.
        String line = "";
        // The split operator.
        String splitBy = ",";
        // Declare variable for temp holding the room name.
        String roomName = "";
        // Start line count of room to 0.
        int lineNo = 0;

        // Try catch for file IO operation exception handling.
        try {
            // Instantiate file reader on top of a buffer with path to CSV file.
            fileReader = new BufferedReader(new FileReader(csvFile));
            // While stil more lines left in file.
            while ((line = fileReader.readLine()) != null) {
                // Trim leading and tailing whitespaces.
                line = line.trim();
                // If line is not empty.
                if (!"".equals(line)) {
                    // Split the line by the defined split operator.
                    String[] segments = line.split(splitBy);

                    // If reached new room in csv file, new entry in hashmap.
                    if ("-".equals(segments[0])) {
                        // Reset room line count to 0.
                        lineNo = 0;
                        // Parse name of room from same line.
                        roomName = segments[1].trim();
                        /* Create a two-dimensional array and make a HashMap
                           entry with room String as key and the two dimensional
                           array as the value. */
                        String[][] idList = new String[10][10];
                        entityCSV.put(roomName, idList);
                        // Otherwise assign ID to grid position in hashmap value[][] 
                    } else {
                        /* Iterate through each x-coordinate in room grid in CSV
                           file and look for ID numbers. */
                        for (int i = 0; i < segments.length; i++) {
                            /* If parameter isn't blank assign ID number trimmed
                               to room grid. */
                            if (!"".equals(segments[i].trim())) {
                                entityCSV.get(roomName)[lineNo][i]
                                        = segments[i].trim();
                            }
                        }
                        // Increment line count after operation.
                        lineNo++;
                    }
                }
            }

            // Exception handling.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

            // Finally try to close File IO operations.
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
     * Iterate through all rooms and their position grids and call instantiation
     * of entities where CSV file had defined one to be placed.
     *
     * @param playerName String, name of player.
     */
    public final void addEntitiesToRooms(String playerName) {
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
                    if (playerName.equalsIgnoreCase("Peter")) {
                        senpaiTypes(i, j, name, playerName, IDnum);
                    } else {
                        entityTypes(i, j, name, playerName, IDnum);
                    }
                }
            }
        }
        // Place entities in their respective rooms.
        showStudents();
        showFurniture();
        showItems();
    }

    /**
     * Instantiate entity objects based on ID number and add them to their
     * respective entity list.
     *
     * @param i int, y-coordinate of grid position.
     * @param j int, x-coordinate of grid position.
     * @param name String, name of room for entity to 'spawn' in.
     * @param playerName String, name of player.
     * @param IDnum String, ID of the type of entity to be created.
     */
    public void entityTypes(int i,
            int j,
            String name,
            String playerName,
            String IDnum) {

        // Switch cases for what type of entity it is
        switch (IDnum.toUpperCase()) {
            // Instance of player to be added.
            case "ID50":
                player = new Player(j,
                        i,
                        playerName,
                        rm.getCurrentRoom(), this);
                this.rm.getRoom(name).setEntity(this.player);
                break;

            // Instance of white t-shirt, brunette student.
            case "ID51":
                studentlist.add(new Student(j, i, rm.getRoom(name), false,
                        "/textures/student1.png", this));
                break;

            // Instance of red t-shirt, brunette student.
            case "ID52":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student2.png", this));
                break;

            // Instance of green t-shirt, brunette student.
            case "ID53":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student3.png", this));
                break;

            // Instance of white t-shirt, blond student.
            case "ID54":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student4.png", this));
                break;

            // Instance of red t-shirt, blond student.
            case "ID55":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student5.png", this));
                break;

            // Instance of green t-shirt, blond student.
            case "ID56":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student6.png", this));
                break;

            // Instance of white t-shirt, black student.
            case "ID57":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student7.png", this));
                break;

            // Instance of red t-shirt, black student.
            case "ID58":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student8.png", this));
                break;

            // Instance of green t-shirt, black student.
            case "ID59":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student9.png", this));
                break;

            // Instance of white t-shirt, asian student.
            case "ID60":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student10.png", this));
                break;

            // Instance of red t-shirt, asian student.
            case "ID61":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student11.png", this));
                break;

            // Instance of green t-shirt, asian student.
            case "ID62":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student12.png", this));
                break;

            // Door, inner, vertical.
            case "ID63":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door2.png",
                        false));
                break;

            //Door, inner, vertical locked
            case "ID63L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door2.png",
                        true));
                break;

            // Door, inner, horizontal.
            case "ID64":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door1.png",
                        false));
                break;

            //Door, inner, horizontal, locked
            case "ID64L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door1.png",
                        true));
                break;

            // Door, outer, vertical, handle down.
            case "ID65":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door12.png", false));
                break;

            // Door, outer, vertical handle down, locked
            case "ID65L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door12.png", true));
                break;

            // Door, outer, vertical, handle up.
            case "ID66":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door11.png", false));
                break;

            // Door, outer, vertical, handle up. locked
            case "ID66L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door11.png", true));
                break;

            // Door, outer, horizontal, handle right.
            case "ID67":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door13.png", false));
                break;

            // Door, outer, horizontal, handle right, locked.
            case "ID67L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door13.png", true));
                break;

            // Door, outer, horizontal, handle left.
            case "ID68":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door14.png", false));
                break;

            // Door, outer, horizontal, handle left, locked.
            case "ID68L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/door14.png", true));
                break;

            // Assignment item                
            case "ID69":
                itemlist.add(new Assignment(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name)));

            // Chair facing north.
            case "ID70":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair1.png"));
                break;
            
            // Chair facing west.    
            case "ID71":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair2.png"));
                break;
            
            // Chair facing south.    
            case "ID72":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair3.png"));
                break;

            // Chair facing east.
            case "ID73":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair4.png"));
                break;

            // Table furniture.
            case "ID74":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        false, 0, "/textures/table1.png"));
                break;

            // Bookcase, left end, facing south.
            case "ID75":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase1.png"));
                break;

            // Bookcase, middle section, facing south, open book on top.
            case "ID76":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase2.png"));
                break;

            // Bookcase, right end, facing south.
            case "ID77":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase3.png"));
                break;

            // Bookcase, right end, facing north.
            case "ID78":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase4.png"));
                break;

            // Bookcase, middle section, facing north.
            case "ID79":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase5.png"));
                break;

            // Bookcase, left end, facing north.
            case "ID80":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase6.png"));
                break;

            // Bookcase, left end, facing south.
            case "ID81":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase7.png"));
                break;

            // Bookcase, middle section, facing south, open book on top.
            case "ID82":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase19.png"));
                break;

            // Bookcase, right end, facing south.
            case "ID83":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase9.png"));
                break;

            // Bookcase, right end, facing north.
            case "ID84":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase10.png"));
                break;

            // Bookcase, middle section, facing north.
            case "ID85":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase20.png"));
                break;

            // Bookcase, left end, facing north.
            case "ID86":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase12.png"));
                break;

            // Table, pooltable.
            case "ID87":

                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/pooltable1.png"));
                break;
            // Table pooltable.
            case "ID88":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/pooltable2.png"));
                break;

            // Key item, for unlocking doors.
            case "ID89":
                itemlist.add(new Key(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name)));
                break;

            // Coffee item.
            case "ID90":
                itemlist.add(new Coffee(j, i, 64, 64, rm.getRoom(name)));
                break;

            // Adderall item.
            case "ID91":
                itemlist.add(new Adderall(j, i, 64, 64, rm.getRoom(name)));
                break;

            // Bench facing west, top end part.
            case "ID92":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench6.png"));
                break;

            // Bench facing west, bottom end part.
            case "ID93":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench5.png"));
                break;

            // Bench facing east, bottom end part.
            case "ID94":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench4.png"));
                break;

            // Bench facing east, top end part.
            case "ID95":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench3.png"));
                break;
                
            //blackboard (check søren)  
            case "ID96":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/blackboard2.png"));
                break;
                
            //instance of toilet. 
            case "ID97":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/toilet.png"));
                break;
             
            //instance of teacher table.     
            case "ID98":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/teachertable1.png"));
                break;
                
            //instance of teacher table.    
            case "ID99":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/teachertable2.png"));
                break;
                
            //instance of teacher table.      
            case "ID100":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/teachertable3.png"));
                break;
                
            //instance of teacher chair.  
            case "ID101":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/teacherchair1.png"));
                break;
                
            //instance of teacher chair.  
            case "ID102":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/teacherchair2.png"));
                break;
                
            //instance of Energy drink. 
            case "ID103":
                itemlist.add(new EnergyDrink(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name)));
                break;
                
            ////instance of dinnertable.
            case "ID104":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/dinnertable1.png"));
                break;
                
            ////instance of dinnertable.     
            case "ID105":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/dinnertable2.png"));
                break;
                
            //instance of dinnertable. 
            case "ID106":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/dinnertable3.png"));
                break;
                
            //instance of dinnertable.
            case "ID107":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name), false, 0,
                        "/textures/dinnertable4.png"));
                break;

            //instance of tutor.
            case "ID108":
                studentlist.add(new Tutor(j, i, rm.getRoom(name),
                        "/textures/tutor1.png", this));
                break;

            //instance of tutor.
            case "ID109":
                studentlist.add(new Tutor(j, i, rm.getRoom(name),
                        "/textures/tutor2.png", this));
                break;

            //instance of tutor.
            case "ID110":
                studentlist.add(new Tutor(j, i, rm.getRoom(name),
                        "/textures/tutor3.png", this));
                break;
             
            //instance of gun.
            case "ID111":
                itemlist.add(new Gun(j, i, 64, 64, rm.getRoom(name)));
                break;

            // In case the ID is not recognized.
            default:
                System.out.println("Error. Entity ID   " + IDnum
                        + "   not defined.");
                break;
        }
    }

    /**
     * Senpai-edition. Instantiate entity objects based on ID number and add
     * them to their respective entity list.
     *
     * @param i int, y-coordinate of grid position.
     * @param j int, x-coordinate of grid position.
     * @param name String, name of room for entity to 'spawn' in.
     * @param playerName String, name of player.
     * @param IDnum String, ID of the type of entity to be created.
     */
    public void senpaiTypes(int i,
            int j,
            String name,
            String playerName,
            String IDnum) {

        // Switch cases for what type of entity it is
        switch (IDnum.toUpperCase()) {
            // Instance of peaceful kouhai (face covered by hair).
            case "ID51":
                studentlist.add(new Student(j, i, rm.getRoom(name), false,
                        "/textures/kouhai1.png", this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID52":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png", this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID53":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png", this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID54":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png", this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID55":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png", this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID56":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png", this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID57":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png", this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID58":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png", this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID59":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png", this));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID60":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png", this));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID61":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png", this));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID62":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png", this));
                break;

            /* In case the ID is not a special case, instantiate entity from
                the regular game edition. */
            default:
                entityTypes(i, j, name, playerName, IDnum);
                break;
        }
    }

    // LOAD & SAVE METHODS
    /**
     * Method for gathering the data that must be saved during saving.
     *
     * @return HashMap<String, ArrayList<ArrayList<String>>>, key is path to
     * file, value is data.
     */
    public HashMap parseForSave() {
        // Instantiate the HashMap.
        HashMap<String, ArrayList<ArrayList<String>>> savePackage = new HashMap<>();

        // Place collected data about students in HashMap.
        savePackage.put("\\Documents\\zuul\\SaveStudentTest.txt",
                saveStudents());

        // Place collected data about player in HashMap.
        savePackage.put("\\Documents\\zuul\\SavePlayersTest.txt",
                savePlayers());

        // Place collected data about inventory in HashMap.
        savePackage.put("\\Documents\\zuul\\SaveInventoryTest.txt",
                saveInventory());

        // Place collected data about items in HashMap.
        savePackage.put("\\Documents\\zuul\\SaveItemsTest.txt",
                saveItems());

        // Return the HashMap with all the data collections to be saved.
        return savePackage;
    }

    /**
     * Parse data for saving items in the game.
     *
     * @return ArrayList<ArrayList<String>>, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> saveItems() {
        System.out.println("itemlist" + itemlist);
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> itemsData = new ArrayList<>();
        // Iterate through all items in the game and gather their data.
        for (Item item : itemlist) {
            // Sub-list to store the individual item data.
            ArrayList<String> itemData = new ArrayList<>();
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
     * @return ArrayList<ArrayList<String>>, 2D list with save data.
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
     * @return ArrayList<ArrayList<String>>, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> savePlayers() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> playersData = new ArrayList<>();
        // Sub-list for holding the player's data.
        /* Not directly necessary, but this way it will work without making
           dedicated code for handling this type save data. */
        ArrayList<String> playerData = new ArrayList<>();

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
     * @return ArrayList<ArrayList<String>>, 2D list with save data.
     */
    public ArrayList<ArrayList<String>> saveStudents() {
        // 2D list to contain the data.
        ArrayList<ArrayList<String>> studentsData = new ArrayList<>();
        // Iterate through all the students and collect the data.
        for (Student student : studentlist) {
            // Sub-list instantiation.
            ArrayList<String> studData = new ArrayList<>();
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
     * @param loadPackage HashMap<String, ArrayList<ArrayList<String>>>, key is
     * file path, value is 2D list with data.
     */
    public void parseLoading(HashMap<String, ArrayList<ArrayList<String>>> loadPackage) {
        // Retrieve list with save file paths.
        ArrayList<String> saveFiles = getSaveFiles();

        // Call student load with student data as parameter.
        loadStudents(loadPackage.get(saveFiles.get(0)));

        // Call player load with player data as parameter.
        loadPlayers(loadPackage.get(saveFiles.get(1)));

        // Call inventory load with inventory data as parameter.
        loadInventory(loadPackage.get(saveFiles.get(2)));

        // Call item load with item data as parameter.
        loadItems(loadPackage.get(saveFiles.get(3)));
    }

    /**
     * Load items from collected data. Iterate through load data and instantiate
     * items based on item name and parameters.
     *
     * @param data ArrayList<ArrayList<String>>, 2D list with load data.
     */
    public void loadItems(ArrayList<ArrayList<String>> data) {
        System.out.println("before nullitems" + itemlist);
        nullItems();
        System.out.println("after nullitems" + itemlist);
        // Clear item list.
        itemlist.clear();
        System.out.println("after clear" + itemlist);

        // Iterate through data of all items for loading.
        for (ArrayList<String> itemData : data) {
            // If not empty, check for type of item.
            if (itemData.size() > 0) {
                // Switch case based on the name of the item.
                switch (itemData.get(0)) {
                    // If adderall, add item to item list.
                    case "Adderall":
                        Adderall d = new Adderall(
                                Integer.parseInt(itemData.get(1)),
                                Integer.parseInt(itemData.get(2)),
                                64,
                                64,
                                (Room) rm.getRoomlist().get(itemData.get(3)));
                        itemlist.add(d);
                        break;

                    // If coffee, add item to item list.
                    case "Coffee":
                        Coffee c = new Coffee(
                                Integer.parseInt(itemData.get(1)),
                                Integer.parseInt(itemData.get(2)),
                                64,
                                64,
                                (Room) rm.getRoomlist().get(itemData.get(3)));
                        itemlist.add(c);
                        break;

                    // If assignment, add item to item list.
                    case "Assignment":
                        Assignment a = new Assignment(
                                Integer.parseInt(itemData.get(1)),
                                Integer.parseInt(itemData.get(2)),
                                64,
                                64,
                                (Room) rm.getRoomlist().get(itemData.get(3)));
                        itemlist.add(a);
                        break;

                    // If key, add item to item list.
                    case "Key":
                        Key k = new Key(Integer.parseInt(itemData.get(1)),
                                Integer.parseInt(itemData.get(2)),
                                64,
                                64,
                                (Room) rm.getRoomlist().get(itemData.get(3)));
                        itemlist.add(k);
                        break;

                    // If energy drink, add item to item list.
                    case "EnergyDrink":
                        EnergyDrink e = new EnergyDrink(
                                Integer.parseInt(itemData.get(1)),
                                Integer.parseInt(itemData.get(2)),
                                64,
                                64,
                                rm.getCurrentRoom().getExit(itemData.get(3)));
                        itemlist.add(e);
                        break;

                    // Ignore anything else
                    default:
                        break;
                }

            }
        }
        // Make items show themself in the game.
        showItems();
    }

    /**
     * Load inventory from data collected from file. Iterate through the data
     * and instantiate items according to item name and parameters.
     *
     * @param data ArrayList<ArrayList<String>>, 2D list with load data.
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
                            Adderall d = new Adderall(0,
                                    0,
                                    64,
                                    64,
                                    null);
                            player.inventory().addItem(d);
                            break;

                        // If coffee, add to inventory list.
                        case "Coffee":
                            Coffee c = new Coffee(0,
                                    0,
                                    64,
                                    64,
                                    null);
                            player.inventory().addItem(c);
                            break;

                        // If assignment, add to inventory list.
                        case "Assignment":
                            Assignment a = new Assignment(
                                    0,
                                    0,
                                    64,
                                    64,
                                    null);
                            player.inventory().addItem(a);
                            break;

                        // If key, add to inventory list.
                        case "Key":
                            Key k = new Key(0,
                                    0,
                                    64,
                                    64,
                                    null);
                            player.inventory().addItem(k);
                            break;

                        // If energy drink, add to inventory list.
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(
                                    0,
                                    0,
                                    64,
                                    64,
                                    null);
                            player.inventory().addItem(e);
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
     * @param data ArrayList<ArrayList<String>>, 2D list with loaded data.
     */
    public void loadPlayers(ArrayList<ArrayList<String>> data) {
        // Iterate through the data.
        for (ArrayList<String> playerData : data) {
            if (playerData.size() > 0) {
                // Set X grid position.
                player.setX(Integer.parseInt(playerData.get(0)));
                // Set Y grid position.
                player.setY(Integer.parseInt(playerData.get(1)));
                // Set Player name.
                player.setName(playerData.get(2));
                // Set currently located in -room.
                player.setCurrentRoom((Room) rm.getRoomlist().get(
                        playerData.get(3)));
                // Set assignment progress.
                player.setAssignmentProgress(Integer.parseInt(
                        playerData.get(4)));
                // Set graded assignment count.
                player.setGradedAssignments(Integer.parseInt(
                        playerData.get(5)));
                // Set boolean for if player has key.
                player.setHasKey(Boolean.parseBoolean(playerData.get(6)));
                // Set player's energy level.
                player.setEnergy(Integer.parseInt(playerData.get(7)));
                // Set player's energy capacity.
                player.setEnergyCap(Integer.parseInt(playerData.get(8)));
                // Set player's remaining time.
                player.setTimeLeft(Integer.parseInt(playerData.get(9)));
            }
        }
        player.getCurrentRoom().setEntity(player);
        this.rm.setCurrentRoom(player.getCurrentRoom());
    }

    public void loadStudents(ArrayList<ArrayList<String>> data) {
        // Iterator int.
        int i = 0;
        // Iterate through data for each student.
        for (ArrayList<String> studentData : data) {
            if (studentData.size() > 0) {
                // Get student that will get new data.
                Student student = studentlist.get(i);
                // Remove student from old location in the game.
                student.getCurrentRoom().removeEntity(student);
                // Set X grid position.
                student.setX(Integer.parseInt(studentData.get(0)));
                // Set Y grid position.
                student.setY(Integer.parseInt(studentData.get(1)));
                // Set student's current room.
                student.setCurrentRoom((Room) (rm.getRoomlist().get(
                        studentData.get(2))));
                // Set student's boolean if he has question for player.
                student.setHasQuestionToPlayer(Boolean.parseBoolean(
                        studentData.get(3)));
                // Place student at his new position in the game.
                student.getCurrentRoom().setEntity(student);
                // Increment the iterator.
                i += 1;
            }
        }
        // Make the students appear in the game.
        showStudents();
    }

    /**
     * Retrieve list with save files's path.
     *
     * @return ArrayList<String>, list with save files.
     */
    public ArrayList<String> getSaveFiles() {
        ArrayList<String> saveFiles = new ArrayList<>();
        // The order of this list is important!
        // ParseLoading method follows the order.
        saveFiles.add("\\Documents\\zuul\\SaveStudentTest.txt");
        saveFiles.add("\\Documents\\zuul\\SavePlayersTest.txt");
        saveFiles.add("\\Documents\\zuul\\SaveInventoryTest.txt");
        saveFiles.add("\\Documents\\zuul\\SaveItemsTest.txt");
        // Return list with file paths.
        return saveFiles;
    }
}
