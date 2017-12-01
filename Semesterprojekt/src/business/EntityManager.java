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
     * @param rm RoomManager, used to assign entities to a specific room.
     */
    public EntityManager(RoomManager rm) {
        this.rm = rm;                   // Assign room manager object to class.
        loadPresetEntities();           // Load entity IDs from CSV file.
        String playerName = "Pete";    // Temp define for player name.
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
     * @return ArrayList<Student>, student list to retrieve.
     */
    public ArrayList<Student> getStudentList() {
        return studentlist;
    }

    /**
     * Replace student list.
     *
     * @param studentlist ArrayList<Student>, student list to assign.
     */
    public void setStudentList(ArrayList<Student> studentlist) {
        this.studentlist = studentlist;
    }

    /**
     * Get furniture list.
     *
     * @return ArrayList<Furniture>, furniture list to retrieve.
     */
    public ArrayList<Furniture> getFurnitureList() {
        return furniturelist;
    }

    /**
     * Replace furniture list.
     *
     * @param furniturelist ArrayList<Student>, furniture list to assign.
     */
    public void setFurnitureList(ArrayList<Furniture> furniturelist) {
        this.furniturelist = furniturelist;
    }

    /**
     * Get item list.
     *
     * @return ArrayList<Item>, item list to retrieve.
     */
    public ArrayList<Item> getItemList() {
        return itemlist;
    }

    /**
     * Replace item list.
     *
     * @param itemlist ArrayList<Item>, item list to assign.
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

                }
            }
        }
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
                        rm.getRoom(name)));
                break;

            // Coffee item.
            case "ID83":
                itemlist.add(new Coffee(j, i, 64, 64, rm.getRoom(name)));
                break;
                
            // Adderall item.
            case "ID84":
                itemlist.add(new Adderall(j, i, 64, 64, rm.getRoom(name)));
                break;

            // Bench facing west, top end part.
            case "ID96":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench6.png"));
                break;

            // Bench facing west, bottom end part.
            case "ID97":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench5.png"));
                break;

            // Bench facing east, bottom end part.
            case "ID98":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench4.png"));
                break;

            // Bench facing east, top end part.
            case "ID99":
                furniturelist.add(new Chair(j,
                        i,
                        64,
                        64,
                        rm.getRoom(name),
                        "/textures/bench3.png"));
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
     * Method for gathering the data that must be saved during saving.
     * 
     * @return  HashMap<String, ArrayList<ArrayList<String>>>,
     *          key is path to file, value is data.
     */
    public HashMap parseForSave() {
        // Instantiate the HashMap.
        HashMap<String, ArrayList<ArrayList<String>>>
                savePackage = new HashMap<>();
        
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
    
    public ArrayList<ArrayList<String>> saveItems() {
        ArrayList<ArrayList<String>> itemsData = new ArrayList<>();
        for (Item item : itemlist) {
            ArrayList<String> itemData = new ArrayList<>();
            itemData.add(item.getName());
            itemData.add(String.valueOf(item.getX()));
            itemData.add(String.valueOf(item.getY()));
            itemData.add(item.getCurrentRoom().getName());
            itemsData.add(itemData);
        }
        return itemsData;
    }
    
    public ArrayList<ArrayList<String>> saveInventory() {
        ArrayList<ArrayList<String>> invenData = new ArrayList<>();
        for (Item item : player.inventory().getInventory()) {
            ArrayList<String> itemData = new ArrayList<>();
            itemData.add(item.getName());
            invenData.add(itemData);
        }
        return invenData;
    }
    
    public ArrayList<ArrayList<String>> savePlayers() {
        ArrayList<ArrayList<String>> playersData = new ArrayList<>();
        ArrayList<String> playerData = new ArrayList<>();
        playerData.add(String.valueOf(player.getX()));
        playerData.add(String.valueOf(player.getY()));
        playerData.add(player.getName());
        playerData.add(String.valueOf(player.getCurrentRoom().getName()));
        playerData.add(String.valueOf(player.getAssignmentProgress()));
        playerData.add(String.valueOf(player.getGradedAssignments()));
        playerData.add(String.valueOf(player.getHasKey()));
        playerData.add(String.valueOf(player.getEnergy()));
        playerData.add(String.valueOf(player.getEnergyCap()));
        playersData.add(playerData);
        return playersData;
    }
    
    public ArrayList<ArrayList<String>> saveStudents() {
        ArrayList<ArrayList<String>> studentsData = new ArrayList<>();
        for (Student student : studentlist) {
            ArrayList<String> studData = new ArrayList<>();
            studData.add(String.valueOf(student.getX()));
            studData.add(String.valueOf(student.getY()));
            studData.add(student.getCurrentRoom().getName());
            studData.add(String.valueOf(student.getHasQuestionToPlayer()));
            studentsData.add(studData);
        }
        return studentsData;
    }
    
    /**
     * Directing loading data to the different entity load methods.
     * 
     * @param loadPackage   HashMap<String, ArrayList<ArrayList<String>>>,
     *                      key is file path, value is 2D list with data.
     */
    public void parseLoading(HashMap<String, ArrayList<ArrayList<String>>> loadPackage) {
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
    
    public void loadItems(ArrayList<ArrayList<String>> data) {
        // Clear item list.
        itemlist.clear();

        // Iterate through data of all items for loading.
        for (ArrayList<String> itemData: data) {
            // If not empty, check for type of item.
            if (itemData.size() > 0) {
                // Switch case based on the name of the item.
                switch (itemData.get(0)) {
                    // If adderall, add item to item list.
                    case "Adderal":
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
        showItems();
    }
    
    public void loadInventory(ArrayList<ArrayList<String>> data) {
        // Clear inventory list.
        player.inventory().getInventory().clear();

        for (ArrayList<String> invenData: data) {
            if (invenData.size() > 0) {
                switch (invenData.get(0)) {
                    // If adderall, add to inventory list.
                    case "Adderal":
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
    }
    
    public void loadPlayers(ArrayList<ArrayList<String>> data) {
        for (ArrayList<String> playerData: data) {
            if (playerData.size() > 0) {
                player.setX(Integer.parseInt(playerData.get(0)));
                player.setY(Integer.parseInt(playerData.get(1)));
                player.setName(playerData.get(2));
                player.setCurrentRoom((Room) rm.getRoomlist().get(playerData.get(3)));
                player.setAssignmentProgress(Integer.parseInt(playerData.get(4)));
                player.setGradedAssignments(Integer.parseInt(playerData.get(5)));
                player.setEnergyCap(Integer.parseInt(playerData.get(8)));
                player.setEnergy(Integer.parseInt(playerData.get(7)));
                player.setHasKey(Boolean.parseBoolean(playerData.get(6)));
            }
        }
    }
    
    public void loadStudents(ArrayList<ArrayList<String>> data) {
        int i = 0;
        for (ArrayList<String> studentData: data) {
            if (studentData.size() > 0) {
                Student student = studentlist.get(i);
                student.getCurrentRoom().removeEntity(student);
                student.setCurrentRoom((Room) (rm.getRoomlist().get(studentData.get(2))));
                student.setX(Integer.parseInt(studentData.get(0)));
                student.setY(Integer.parseInt(studentData.get(1)));
                student.setCurrentRoom((Room) (rm.getRoomlist().get(studentData.get(2))));
                student.setHasQuestionToPlayer(Boolean.parseBoolean(studentData.get(3)));
                student.getCurrentRoom().setEntity(student);
                i += 1;
            }
        }
        showStudents();
    }
    
    public ArrayList<String> getSaveFiles() {
        ArrayList<String> saveFiles = new ArrayList<>();
        // The order of this list is important!
        // ParseLoading method follows the order.
        saveFiles.add("\\Documents\\zuul\\SaveStudentTest.txt");
        saveFiles.add("\\Documents\\zuul\\SavePlayersTest.txt");
        saveFiles.add("\\Documents\\zuul\\SaveInventoryTest.txt");
        saveFiles.add("\\Documents\\zuul\\SaveItemsTest.txt");
        return saveFiles;
    }
}
