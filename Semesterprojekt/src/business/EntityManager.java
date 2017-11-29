package business;

// IMPORTS

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private static HashMap<String, String[][]> entityCSV = new HashMap<>();
    private static RoomManager rm;
    
    /**
     * Primary constructor for the EntityManager class.
     * 
     * @param rm    RoomManager, used to assign entities to a specific room.
     */
    public EntityManager(RoomManager rm) {
        this.rm = rm;                   // Assign room manager object to class.
        loadPresetEntities();           // Load entity IDs from CSV file.
        String playerName = "Pete";    // Temp define for player name.
        addEntitiesToRooms(playerName); // Instantiate entities defined in CSV.

    }

    // ENTITY MANAGMENT METHODS

    /**
     * Add initialized student to the student list.
     * 
     * @param s     Student, student to be added to studet list.
     */
    public void addStudent(Student s) {
        studentlist.add(s);
    }
    
    /**
     * Despawn student.
     * 
     * @param s     Student, student that need to be removed from the game.
     */
    public void removeStudent(Student s) {
        studentlist.remove(s);
    }

    
    /**
     * Spawn students in their respective active room, in the beginning of the 
     * game.
     */
    public void showStudents(){
        for(Student s : studentlist){
            s.getCurrentRoom().setEntity(s);
        }
    }
    
    /**
     * Spawn furnitures in their respective active room, in the beginning of the
     * game.
     */
    public void showFurniture(){
        for(Furniture d : furniturelist){
            d.getCurrentRoom().setEntity(d);

        }
    }
    
    /**
     * Spawn items in their respective active room, at the beginning of the
     * game.
     */
    public void showItems(){
        for(Item i : itemlist)
            i.getCurrentRoom().setEntity(i);
    }

    /**
     * Add initialized furniture to the furniture list.
     * 
     * @param f     Furniture, to be placed in the furniture list.
     */

    public void addFurniture(Furniture f) {
        furniturelist.add(f);
    }
    
    /**
     * Remove furniture object from furniture list.
     * 
     * @param f     Furniture, to be removed from furniture list.
     */
    public void removeFurniture(Furniture f) {
        furniturelist.remove(f);
    }
    
    /**
     * Add initializedd item to the item list.
     * 
     * @param i     Item, to be placed in the item list.
     */
    public void addItem(Item i) {
        itemlist.add(i);
    }
    
    /**
     * Remove item object from item list.
     * 
     * @param i     Item, to be removed from item list.
     */
    public void removeItem(Item i) {
        itemlist.remove(i);
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
     * Replace player object.
     * 
     * @param player    Player, the new player object to replace previous.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Get student list.
     * 
     * @return      ArrayList<Student>, student list to retrieve.
     */
    public ArrayList<Student> getStudentList() {
        return studentlist;
    }
    
    /**
     * Replace student list.
     * 
     * @param studentlist   ArrayList<Student>, student list to assign.
     */
    public void setStudentList(ArrayList<Student> studentlist) {
        this.studentlist = studentlist;
    }
    
    /**
     * Get furniture list.
     * 
     * @return      ArrayList<Furniture>, furniture list to retrieve.
     */
    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }
    
    /**
     * Replace furniture list.
     * 
     * @param furniturelist     ArrayList<Student>, furniture list to assign.
     */
    public void setFurnitureList(ArrayList<Furniture> furniturelist) {
        this.furniturelist = furniturelist;
    }
    
    /**
     * Get item list.
     * 
     * @return      ArrayList<Item>, item list to retrieve.
     */
    public ArrayList<Item> getItemList() {
        return itemlist;
    }
    
    /**
     * Replace item list.
     * 
     * @param itemlist      ArrayList<Item>, item list to assign.
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
                    if (playerName.equals("Peter")) {
                        senpaiTypes(i, j, name, playerName, IDnum);
                    } else {
                        entityTypes(i, j, name, playerName, IDnum);
                    }
                    // Place entities in their respective rooms.
                    showStudents();
                    showFurniture();
                    showItems();
                }
            }
        }
    }

    /**
     * Instantiate entity objects based on ID number and add them to their
     * respective entity list.
     *
     * @param i             int, y-coordinate of grid position.
     * @param j             int, x-coordinate of grid position.
     * @param name          String, name of room for entity to 'spawn' in.
     * @param playerName    String, name of player.
     * @param IDnum         String, ID of the type of entity to be created.
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
                        rm.getCurrentRoom());
                this.rm.getRoom(name).setEntity(this.player);
                break;

            // Instance of white t-shirt, brunette student.
            case "ID51":
                studentlist.add(new Student(j, i, rm.getRoom(name), false,
                        "/textures/student1.png"));
                break;

            // Instance of red t-shirt, brunette student.
            case "ID52":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student2.png"));
                break;

            // Instance of green t-shirt, brunette student.
            case "ID53":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student3.png"));
                break;

            // Instance of white t-shirt, blond student.
            case "ID54":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student4.png"));
                break;

            // Instance of red t-shirt, blond student.
            case "ID55":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student5.png"));
                break;

            // Instance of green t-shirt, blond student.
            case "ID56":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student6.png"));
                break;

            // Instance of white t-shirt, black student.
            case "ID57":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student7.png"));
                break;

            // Instance of red t-shirt, black student.
            case "ID58":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student8.png"));
                break;

            // Instance of green t-shirt, black student.
            case "ID59":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student9.png"));
                break;

            // Instance of white t-shirt, asian student.
            case "ID60":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student10.png"));
                break;

            // Instance of red t-shirt, asian student.
            case "ID61":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/student11.png"));
                break;

            // Instance of green t-shirt, asian student.
            case "ID62":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/student12.png"));
                break;
                
            
            case "chair1":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair1.png"));
                break;
                
            
            case "chair2":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair2.png"));
                
            // Door, inner, east, type 1.
            case "ID63": 
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "east",
                        rm.getRoom(name),
                        "/textures/door2.png",
                        false));
                break;
            
            // Door, inner, south, type 1.
            case "ID64":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "south",
                        rm.getRoom(name),
                        "/textures/door1.png",
                        false));
                break;
                
            // Door, inner, west, type 1.
            case "ID65":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "west",
                        rm.getRoom(name),
                        "/textures/door2.png",
                        false));
                break;
                
            // Door, inner, north, type 1.
            case "ID66":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "north",
                        rm.getRoom(name),
                        "/textures/door1.png",
                        false));
                break;
                
            case "ID63L": 
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "east",
                        rm.getRoom(name),
                        "/textures/door2.png",
                        true));
                break;
            
            case "ID64L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "south",
                        rm.getRoom(name),
                        "/textures/door1.png",
                        true));
                break;
                
            case "ID65L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "west",
                        rm.getRoom(name),
                        "/textures/door2.png",
                        true));
                break;
                
            case "ID66L":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "north",
                        rm.getRoom(name),
                        "/textures/door1.png",
                        true));
                break;

            // Door, outer, east, type 1.
            case "ID67": 
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "east",
                        rm.getRoom(name),
                        "/textures/door12.png", false));
                break;
                
            // Door, outer, east, type 2.
            case "ID68":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "east",
                        rm.getRoom(name),
                        "/textures/door11.png", false));
                break;
            
            // Door, outer, south, type 1.
            case "ID69":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "south",
                        rm.getRoom(name),
                        "/textures/door13.png", false));
                break;
                
            // Door, outer, south, type 2.
            case "ID70":
                furniturelist.add(new Door(j,
                        i,
                        64,
                        64,
                        "south",
                        rm.getRoom(name),
                        "/textures/door14.png", false));
                break;
                
            // Chair facing north.
            case "ID71":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair3.png"));
                break;
                
            // Chair facing east.
            case "ID72":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/chair4.png"));
                break;
                
            // Table furniture.
            case "ID73":
                furniturelist.add(new Table(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        false, 0));
                break;
                
            // Bookcase, left end, facing south.
            case "ID74":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase1.png"));
                break;
                
            // Bookcase, middle section, facing south, open book on top.
            case "ID75":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase2.png"));
                break;
                
            // Bookcase, right end, facing south.
            case "ID76":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase3.png"));
                break;
                
            // Bookcase, right end, facing north.
            case "ID77":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase4.png"));
                break;
                
            // Bookcase, middle section, facing north.
            case "ID78":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase5.png"));
                break;
                
            // Bookcase, left end, facing north.
            case "ID79":
                furniturelist.add(new Bookcase(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bookcase6.png"));
                break;
                
            // Plant, hedge.
            case "ID80":
                furniturelist.add(new Plant(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/hedge1.png"));
                break;
            
            // Plant, pot plant.
            case "ID81":
                furniturelist.add(new Plant(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/potplant.png"));
                break;
                
            // Key item, for unlocking doors.
            case "ID82":
                itemlist.add(new Key(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/key.png"));
                break;
                
            // Coffee item.
            case "ID83":
                itemlist.add(new Coffee(j, i, 64, 64, rm.getRoom(name)));
                break;

            // In case the ID is not recognized.
            default:
                System.out.println("Error. Entity ID   " + IDnum
                        + "   not defined.");
                break;
        }
    }
    
    /**
     * Senpai-edition.
     * Instantiate entity objects based on ID number and add them to their
     * respective entity list.
     * 
     * @param i             int, y-coordinate of grid position.
     * @param j             int, x-coordinate of grid position.
     * @param name          String, name of room for entity to 'spawn' in.
     * @param playerName    String, name of player.
     * @param IDnum         String, ID of the type of entity to be created.
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
                        "/textures/kouhai1.png"));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID52":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png"));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID53":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png"));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID54":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png"));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID55":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png"));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID56":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png"));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID57":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png"));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID58":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png"));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID59":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png"));
                break;

            // Instance of peaceful kouhai (face covered by hair).
            case "ID60":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai1.png"));
                break;

            // Instance of hostile kouhai (red eye).
            case "ID61":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        true,
                        "/textures/kouhai3.png"));
                break;

            // Instance of peaceful kouhai (black eye).
            case "ID62":
                studentlist.add(new Student(j,
                        i,
                        rm.getRoom(name),
                        false,
                        "/textures/kouhai2.png"));
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
     * Method for saving the game progress.
     */
    public void saveGame() {
        makeSaveFolder();   // Create folder if it does not exist.
        saveItems();        // Save item list to file.
        savePlayers();      // Save player to file.
        saveStudents();     // Save student list to file.
        saveFurniture();    // Save furniture list to file.
        saveInventory();    // Save inventory list to file.
    }
    
    /**
     * Method for loading the previously saved game progress.
     */
    public void loadGame() {
        loadItems();        // Load item list from file.
        loadPlayers();      // Load player from file.
        loadStudents();     // Load student list from file.
        loadFurniture();    // Load furniture list from file.
        loadInventory();    // Load inventory list from file.
    }

    
    /**
     * Create save folder if it does not exist.
     */
    public void makeSaveFolder(){
        File folder = new File(System.getProperty("user.home") +
                "\\Documents\\zuul");
        // If folder does not exist, create directory.
        if(!folder.exists()){
            folder.mkdirs();
        }
    }
    
    /**
     * Save item list to file.
     */
    public void saveItems() {
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveItemsTest.txt")));
            
            // Iterate through item list and write them to file.
            for (Item item : itemlist) {
                fileWriter.append(item.getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(item.getY()));
                fileWriter.append(",");
                fileWriter.append(item.getCurrentRoom().getName());
                fileWriter.append("\n");

                System.out.println("Saved items");
            }
            
            // Flush and then close file stream.
            fileWriter.close();
        } catch (IOException e) { // File write error print.
            System.err.println("BEEP BOOP, COULDNT SAVE ITEMS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Save inventory list to file.
     */
    public void saveInventory() {
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveInventoryTest.txt")));
            
            /* Iterate through player's inventory list and write the items to
               file. */
//            for (Item item : player.inventory().getInventory()) {
//                fileWriter.append(item.getName());
//            }
            fileWriter.append("\n");
            
            // Flush and then close file stream.
            fileWriter.close();
            
        } catch (IOException e) { // File write error print.
            System.err.println("BEEP BOOP, COULDNT SAVE INVENTORY... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Save player to file.
     */
    public void savePlayers() {
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SavePlayersTest.txt")));

            // Write player status to file.
            fileWriter.append(String.valueOf(player.getX()));
            fileWriter.append(",");
            fileWriter.append(String.valueOf(player.getY()));
            fileWriter.append(",");
            fileWriter.append(player.getName());
            fileWriter.append(",");
            fileWriter.append(player.getCurrentRoom().getName());
            fileWriter.append("\n");

            System.out.println("Saved Player");
            
            // Flush and then close file stream.
            fileWriter.close();
            
        } catch (IOException e) { // File IO error print.
            System.err.println("BEEP BOOP, COULDNT SAVE PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Save student list to file.
     */
    public void saveStudents() {
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveStudentTest.txt")));
            
            // Write status of students to file.
            for (Student student : studentlist) {
                fileWriter.append(String.valueOf(student.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(student.getY()));
                fileWriter.append(",");
                fileWriter.append(student.getCurrentRoom().getName());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(
                        student.getHasQuestionToPlayer()));
                fileWriter.append("\n");

                System.out.println("Saved Students");
            }
            
            // Flush and then close file stream.
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("BEEP BOOP, COULDNT SAVE STUDENTS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Save furniture list to file.
     */
    public void saveFurniture() {
        // File IO try/catch.
        try {
            // Buffer, writer, file-path.
            Writer fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(System.getProperty("user.home")
                            + "\\Documents\\zuul\\SaveFurnitureTest.txt")));
            
            // Write status of furniture to file.
            for (Furniture furniture : furniturelist) {
                fileWriter.append(String.valueOf(furniture.getX()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(furniture.getY()));
                fileWriter.append(",");
                fileWriter.append(furniture.getFurnitureName());
                fileWriter.append(",");
                fileWriter.append(furniture.getCurrentRoom().getName());
                fileWriter.append(",");
                fileWriter.append(furniture.getImagePath());
                fileWriter.append("\n");
                System.out.println("Saved Furniture");
            }
            
            // Flush and then close file stream.
            fileWriter.close();
            
        } catch (IOException e) { // File IO error print.
            System.err.println("BEEP BOOP, COULDNT SAVE FURNITURE... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Load item list from previously saved game file.
     */
    public void loadItems() {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveItemsTest.txt")));
            
            // Clear item list.
            itemlist.clear();
            
            String line;
            // Continue to iterate as long as there is file content.
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(",");
                // If not blank line.
                if (tokens.length > 0) {
                    // Check for first token in line.
                    switch (tokens[0]) {
                        // If adderall, add item to item list.
                        case "Adderal":
                            Adderall d = new Adderall(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(d);
                            break;
                            
                        // If coffee, add item to item list.
                        case "Coffee":
                            Coffee c = new Coffee(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(c);
                            break;
                            
                        // If assignment, add item to item list.
                        case "Assignment":
                            Assignment a = new Assignment(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            itemlist.add(a);
                            break;
                            
                        // If key, add item to item list.
                        case "Key":
                            Key k = new Key(Integer.parseInt(
                                    tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]),
                                    "/textures/key.png");
                            itemlist.add(k);
                            break;
                            
                        // If energy drink, add item to item list.
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    rm.getCurrentRoom().getExit(tokens[2]));
                            itemlist.add(e);
                            break;
                        
                        // Ignore anything else
                        default:
                            break;
                    }
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();

        } catch (IOException e) { // File read error print.
            System.err.println("BEEP BOOP, COULDNT LOAD ITEMS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Load inventory list from previously saved game file.
     */
    public void loadInventory() {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveInventoryTest.txt")));
            
            // Clear inventory list.
            player.inventory().getInventory().clear();
            
            String line;
            
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(",");
                // If line is not empty.
                if (tokens.length > 0) {
                    // Check for first token in line.
                    switch (tokens[0]) {
                        // If adderall, add to inventory list.
                        case "Adderal":
                            Adderall d = new Adderall(Integer.parseInt(
                                    tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(d);
                            break;
                            
                        // If coffee, add to inventory list.
                        case "Coffee":
                            Coffee c = new Coffee(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(c);
                            break;
                            
                        // If assignment, add to inventory list.
                        case "Assignment":
                            Assignment a = new Assignment(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]));
                            player.inventory().addItem(a);
                            break;
                            
                        // If key, add to inventory list.
                        case "Key":
                            Key k = new Key(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]),
                                    "/textures/key.png");
                            player.inventory().addItem(k);
                            break;
                            
                        // If energy drink, add to inventory list.
                        case "EnergyDrink":
                            EnergyDrink e = new EnergyDrink(
                                    Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    rm.getCurrentRoom().getExit(tokens[2]));
                            player.inventory().addItem(e);
                            break;
                            
                        // Ignore anything else.
                        default:
                            break;
                    }
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();
            
        } catch (IOException e) { // File read error print.
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Load player from previously saved game file.
     */
    public void loadPlayers() {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SavePlayersTest.txt")));
            // Set player to null.
            this.player = null;
            
            String line;
            // While file is not empty continue.
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line.
                String[] tokens = line.split(",");
                // If line is not empty.
                if (tokens.length > 0) {
                    // If player, instantiate player.
                    player = new Player(Integer.parseInt(tokens[0]),
                            Integer.parseInt(tokens[1]),
                            tokens[2],
                            (Room) rm.getRoomlist().get(tokens[3]));
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();
            
        } catch (IOException e) { // File IO error print.
            System.err.println("BEEP BOOP, COULDNT LOAD PLAYERS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Load student list from previously saved game file.
     */
    public void loadStudents() {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveStudentsTest.txt")));
            // Clear student list.
            studentlist.clear();
            
            String line;
            // While file in not empty, continue.
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line.
                String[] tokens = line.split(",");
                // If line is not empty.
                if (tokens.length > 0) {
                    // Instantiate Student with parameters taken from CSV file.
                    Student student = new Student(Integer.parseInt(tokens[0]),
                            Integer.parseInt(tokens[1]),
                            (Room) rm.getRoomlist().get(tokens[2]),
                            Boolean.parseBoolean(tokens[3]));
                    // Add student object to student list.
                    studentlist.add(student);
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();
            
        } catch (IOException e) { // File IO error print.
            System.err.println("BEEP BOOP, COULDNT LOAD STUDENTS... "
                    + "please check the save directory in the code.");
        }
    }
    
    /**
     * Load furniture list from previously saved game file.
     */
    public void loadFurniture() {
        // File IO try/catch.
        try {
            // Buffer, reader, file-path.
            BufferedReader fileReader = new BufferedReader(
                    new FileReader(System.getProperty("user.home")
                            + ("\\Documents\\zuul\\SaveItemsTest.txt")));
            // Clear student list.
            furniturelist.clear();
            
            String line;
            // While file in not empty, continue.
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line.
                String[] tokens = line.split(",");
                // If line is not empty.
                if (tokens.length > 0) {
                    // Switch case for what furniture the data is about.
                    switch (tokens[0]) {
                        /* If chair, instantiate furniture and add to furniture
                           list. */
                        case "Chair":
                            Chair c = new Chair(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]),
                                    tokens[3]);
                            furniturelist.add(c);
                            break;
                            
                        /* If table, instantiate furniture and add to furniture
                           list. */
                        case "Table":
                            Table t = new Table(Integer.parseInt(tokens[0]),
                                    Integer.parseInt(tokens[1]),
                                    64,
                                    64,
                                    (Room) rm.getRoomlist().get(tokens[2]),
                                    true,
                                    2);
                            furniturelist.add(t);
                            break;
                            
                        // If anything else, then ignore.
                        default:
                            break;
                    }
                }
            }
            
            // Flush and then close file stream.
            fileReader.close();
            
        } catch (IOException e) { // File IO error print.
            System.err.println("BEEP BOOP, COULDNT LOAD FURNITURE... "
                    + "please check the save directory in the code.");
        }
    }
}
