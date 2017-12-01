package business;

import Acq.IBusiness;
import Acq.IData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Niclas Johansen & J & Rasmus Willer
 */
public class BusinessFacade implements IBusiness {

    private EntityManager entityManager;
    private RoomManager roomManager;
    private IData data;

    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }

    /**
     * zero-arg constructor assigns values to EntityManager & RoomManager
     */
    public BusinessFacade() {
        roomManager = new RoomManager();
        entityManager = new EntityManager(roomManager);
        for (int i = 0; i < entityManager.getFurnitureList().size(); i++) {
            if (entityManager.getFurnitureList().get(i) instanceof Door) {
                Door d = (Door) entityManager.getFurnitureList().get(i);
                d.setRoomManager(roomManager);
            }
        }
    }

    /**
     * calls .move on player
     *
     * @param direction
     */
    @Override
    public void playerMove(String direction) {
        // player currently saved in an arraylist - might/should be changed
        entityManager.getPlayer().move(direction);
    }

    @Override
    public void resetGame() {
        roomManager = new RoomManager();
        entityManager = new EntityManager(roomManager);
        for (int i = 0; i < entityManager.getFurnitureList().size(); i++) {
            if (entityManager.getFurnitureList().get(i) instanceof Door) {
                Door d = (Door) entityManager.getFurnitureList().get(i);
                d.setRoomManager(roomManager);
            }
        }
    }

    /**
     * calls .interact on player player interacts with the object in x direction
     *
     * @param direction
     */
    @Override
    public void playerInteract() {
        entityManager.getPlayer().interact();
    }

    /**
     * gets the image of an entity placed at row, col if no entity is present ->
     * return an empty square
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public String entityGetImage(int row, int col) {
        if (roomManager.getCurrentRoom().getEntities()[row][col] != null) {
            return roomManager.getCurrentRoom().getEntities()[row][col].getEntityImage();
        } else {
            return "testSquare.png";
        }
    }

    /**
     * Override, upon save game from the business layer. The parsed data to be
     * saved is fetched from the entity manager, grouped so the data has an
     * associated file path where they are destined to end up. Each save to file
     * process is iterated through.
     */
    @Override
    public void saveGame() {
        // Get parsed data.
        HashMap savePackage = entityManager.parseForSave();
        // Get file paths in a set (also assures no saving to the same file).
        Set<String> paths = savePackage.keySet();
        // Iterate through the file paths and call the data layer.
        for (String path : paths) {
            data.saveGame(path,
                    (ArrayList<ArrayList<String>>) savePackage.get(path));
        }
    }

    /**
     * Override, upon load game from the business layer. Player is temporarily
     * removed from the map, but placed again after the data collection is
     * complete and new values can be assigned. Data is collected file after
     * file and when all done, passed to entity manager for parsing and
     * implementing.
     */
    @Override
    public void loadGame() {
        // Set old player's entity location to null.
        entityManager.getPlayer().getCurrentRoom().getEntities()[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()] = null;

        // Instantiate the load package that will contain all the loaded data.
        HashMap<String, ArrayList<ArrayList<String>>> loadPackage = new HashMap<>();

        // Retrieve the file paths for the save files.
        ArrayList<String> saveFiles = entityManager.getSaveFiles();
        /* Iterate through file paths, collect data from those files, and put
           the data along with the file path into the HashMap. */
        for (String file : saveFiles) {
            ArrayList<ArrayList<String>> dataSet = data.loadGame(file);
            loadPackage.put(file, dataSet);
        }
        // Call the parsing of the loaded data in entity manager.
        entityManager.parseLoading(loadPackage);

        // Place the player with the newly updated data.
        entityManager.getPlayer().getCurrentRoom().getEntities()[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()] = entityManager.getPlayer();
        // Update player's currently located at -room.
        roomManager.setCurrentRoom(entityManager.getPlayer().getCurrentRoom());
    }

    @Override
    public String displayHighscore() {
        data.displayHighscore();
        return data.displayHighscore();
    }

    @Override
    public void loadXML() {
        data.loadXML();
    }

    @Override
    public String getTileImage(int row, int col) {
        if (roomManager.getCurrentRoom().getTiles()[row][col] != null) {
            return roomManager.getCurrentRoom().getTiles()[row][col].getImage();
        } else {
            return "testSquare.png";
        }
    }

    @Override
    public void goThroughDoor() {
//        roomManager.getCurrentRoom().getEntities()[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()].onInteract();
    }

    /**
     * the game loop - responsible for moving students and managing energy
     */
    @Override
    public void loop() {

        // call idleMove on all students in the game
        for (Student s : entityManager.getStudentList()) {
            s.idleMove();
        }
        // reduce player's current energy by 1
        entityManager.getPlayer().setEnergy(entityManager.getPlayer().getEnergy() - 1);
        System.out.println(entityManager.getPlayer().getEnergy());
    }

    @Override
    public Inventory playerGetInventory() {
        return entityManager.getPlayer().inventory();
    }

    /**
     * uses a given item
     *
     * @param o
     */
    @Override
    public void itemUse(Object o) {
        Item toUse = (Item) o;
        toUse.use(entityManager.getPlayer());
        entityManager.getPlayer().inventory().removeItem(toUse);
    }

    /**
     * drops a given item
     *
     * @param o
     */
    @Override
    public void itemDrop(Object o) {
        entityManager.getPlayer().inventory().dropItem((Item) o, entityManager.getPlayer());
    }

    @Override
    public void playerDropItem(int index) {
    }

    @Override
    public String playerCurrentRoom() {
        return entityManager.getPlayer().getCurrentRoom().getName();
    }

}
