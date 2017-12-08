package business;

// IMPORTS

import Acq.IBusiness;
import Acq.IData;
import Acq.IItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.collections.ObservableList;

/**
 *
 * @author Niclas Johansen & J & Rasmus Willer
 */
public class BusinessFacade implements IBusiness {

    private boolean gameOver = false;
    private EntityManager entityManager;
    private RoomManager roomManager;
    private IData data;
    private int startSeconds = 300;
    // amount of seconds you have left
    private int seconds = startSeconds;

    /**
     * No-arg constructor; call game reset, to start game from fresh.
     */
    public BusinessFacade() {
    }
    
    /**
     * Override; reassign data layer.
     * 
     * @param dataLayer     IData, new data layer object.
     */
    @Override
    public void injectData(IData dataLayer) {
        data = dataLayer;
    }
    
    /**
     * Override; upon player move, move according to string directions.
     *
     * @param direction     String, direction to move.
     */
    @Override
    public void playerMove(String direction) {
        // Call move method in player object.
        entityManager.getPlayer().move(direction);
    }
    
    /**
     * Override; set the game to New Game state. Instantiating new room- and 
     * entity-managers and update all doors in the game, along with setting
     * gameOver to false.
     * 
     * @param playerName    String, name of player.
     */
    @Override
    public void resetGame(String playerName) {
        roomManager = new RoomManager();
        entityManager = new EntityManager(roomManager, playerName);
        gameOver = false;
        this.seconds = startSeconds;
        
        // Iterate through the length of furniture list.
        for (int i = 0; i < entityManager.getFurnitureList().size(); i++) {
            // If entity is a door, update its room manager.
            if (entityManager.getFurnitureList().get(i) instanceof Door) {
                Door d = (Door) entityManager.getFurnitureList().get(i);
                d.setRoomManager(roomManager);
            }
        }
    }

    /**
     * Override; upon interaction with player.
     */
    @Override
    public void playerInteract() {
        entityManager.getPlayer().interact();
    }

    /**
     * Override; gets the image of an entity placed at (row, col).
     * If no entity is present return an empty test square.
     *
     * @param row   int, X grid position in room.
     * @param col   int, Y grid position in room.
     * @return      String, file-name of image source.
     */
    @Override
    public String entityGetImage(int row, int col) {
        // If there is an entity, get its image file path, otherwise get test.
        if (roomManager.getCurrentRoom().getEntities()[row][col] != null) {
            return roomManager.getCurrentRoom().getEntities()[row][col].
                    getEntityImage();
        
        /* After game is out of testing phase, this is not intended to be
           reached, but if it is, then it will work as a graphics safeguard. */
        } else {
            return "testSquare.png";
        }
    }

    /**
     * Override; upon save game. The parsed data to be saved is fetched from the
     * entity manager, grouped so the data has an associated file path where
     * they are destined to end up.
     * Each save to file process is iterated through.
     */
    @Override
    public void saveGame() {
        entityManager.getPlayer().setTimeLeft(seconds);
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
     * Override; upon load game from the business layer. Player is temporarily
     * removed from the map, but placed again after the data collection is
     * complete and new values can be assigned. Data is collected file after
     * file and when all done, passed to entity manager for parsing and
     * implementing.
     */
    @Override
    public boolean loadGame() {
        boolean status = true;
        // Instantiate the load package that will contain all the loaded data.
        HashMap<String, ArrayList<ArrayList<String>>> loadPackage = new
         HashMap<>();

        // Retrieve the file paths for the save files.
        ArrayList<String> saveFiles = entityManager.getSaveFiles();
        /* Iterate through file paths, collect data from those files, and put
           the data along with the file path into the HashMap, unless there's no
           data. */
        for (String file : saveFiles) {
            ArrayList<ArrayList<String>> dataSet = data.loadGame(file);
            if (!dataSet.isEmpty()) {
                loadPackage.put(file, dataSet);
            }
        }
        
        // If the load package is not empty, otherwise do nothing.
        if (!loadPackage.isEmpty()) {
            // Set old player's entity location to null.
            entityManager.getPlayer().getCurrentRoom().getEntities()
                    [entityManager.getPlayer().getY()]
                    [entityManager.getPlayer().getX()] = null;
            
            // Call the parsing of the loaded data in entity manager.
            entityManager.parseLoading(loadPackage);

            // Place the player with the newly updated data.
            entityManager.getPlayer().getCurrentRoom().getEntities()
                    [entityManager.getPlayer().getY()]
                    [entityManager.getPlayer().getX()] = 
                    entityManager.getPlayer();
            // Update player's currently located at -room.
            roomManager.setCurrentRoom(
                    entityManager.getPlayer().getCurrentRoom());
            // Refresh time remaining with loaded value saved under player.
            seconds = entityManager.getPlayer().getTimeLeft();
        } else {
            status = false;
        }
        
        return status;
    }
    
    /**
     * Override; get highscore from data layer and return it to where it was
     * called.
     * 
     * @return  String, contains the highscore data.
     */
    @Override
    public String displayHighscore() {
        return data.displayHighscore();
    }
    
    /**
     * Override; load XML from data layer.
     */
    @Override
    public void loadXML() {
        data.loadXML();
    }
    
    @Override
    public void saveHighscore(){
        data.saveHighscore(entityManager.getPlayer().getName(), seconds);
    }
    
    /**
     * Override; gets the image of a tile placed at (row, col).
     * If no tile is present, return an empty test square.
     *
     * @param row   int, X grid position in room.
     * @param col   int, Y grid position in room.
     * @return      String, file-name of image source.
     */
    @Override
    public String getTileImage(int row, int col) {
        // If there is an entity, get its image file path, otherwise get test.
        if (roomManager.getCurrentRoom().getTiles()[row][col] != null) {
            return roomManager.getCurrentRoom().getTiles()[row][col].getImage();
            
        /* After game is out of testing phase, this is not intended to be
           reached, but if it is, then it will work as a graphics safeguard. */
        } else {
            return "testSquare.png";
        }
    }
    /**
     * Override: Checks if the player has lost the game.
     * @return Boolean, whether or not the game is over.
     */
    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Override; The game loop. Responsible for moving students and managing
     * energy.
     */
    @Override
    public void loop() {
        // If energy reached zero, exit game.
        if(entityManager.getPlayer().getEnergy() <= 0 || seconds <= 0) {
            gameOver = true;
            return;
        }
        
        // For all students, call check idleMove.
        for (Student s : entityManager.getStudentList()) {
            s.idleMove();
        }
        if(entityManager.getPlayer().getCurrentAssignment() != null) {
            entityManager.getPlayer().getCurrentAssignment().tick(entityManager.getPlayer());
        }
        // Reduce player's current energy by 1 each second.
        entityManager.getPlayer().setEnergy(
                entityManager.getPlayer().getEnergy() - 1);         
        // Reduce seconds by 1
        seconds--;
        
        if(entityManager.getPlayer().getGradedAssignments() >= 10){
        data.saveHighscore(entityManager.getPlayer().getName(), seconds);
        }
    }
    
    /**
     * Override; retrieve player inventory object.
     * 
     * @return      Inventory, inventory object to retrieve.
     */
    @Override
    public ObservableList<IItem> playerGetInventory() {
        return entityManager.getPlayer().inventory().getInventory();
    }

    /**
     * Override; upon item use. Call any effects to player and remove item from
     * the game.
     *
     * @param i IItem, item to be used
     */
    @Override
    public boolean itemUse(IItem i) {
        if (((Item)i).use(entityManager.getPlayer())) {
        entityManager.getPlayer().inventory().removeItem((Item) i);
        return true;
    } else {
    return false;
    }
}

    /**
     * Override; upon item drop. Drops a chosen item from player's inventory.
     *
     * @param i     IItem, item to be dropped
     */
    @Override
    public void itemDrop(IItem i) {
        // Call dropItem method in inventory.
        entityManager.getPlayer().inventory().dropItem(
                (Item) i, entityManager.getPlayer());
    }

    @Override
    public void playerDropItem(int index) {
    }

    /**
     * Override; retrieve player's current room location.
     * 
     * @return      String, name of the room, the player is in.
     */
    @Override
    public String playerCurrentRoomName() {
        return entityManager.getPlayer().getCurrentRoom().getName();
    }
    
    /**
     * Override; retrieve player's energy level.
     * 
     * @return      int, value of energy level.
     */
    @Override
    public int playerEnergy() {
        return entityManager.getPlayer().getEnergy();
    }
    
    /**
     * Override; retrieve number of graded assignments.
     * 
     * @return      int, number of assignments graded.
     */
    @Override
    public int amountOfGradedAssignments() {
        return entityManager.getPlayer().getGradedAssignments();
    }
 /**
  * Override; set player's name
  * 
  * @param name     String, desired name of player
  */
    @Override
    public void playerSetName(String name) {
        entityManager.getPlayer().setName(name);
    }

    /**
     * Override; retrieve seconds left after loading previous save game.
     * 
     * @return      int, time left in seconds.
     */
    @Override
    public int getSeconds() {
        return this.seconds;
    }

    /**
     * Override; set the seconds remaining for saving process.
     * 
     * @param seconds   int, time left in seconds.
     */
    @Override
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean isAssignment(IItem item) {
       return item instanceof Assignment;
    }

    @Override
    public String minimapImage(){
        return entityManager.getPlayer().getCurrentRoom().getMinimapPath();
    }
    
    @Override
    public int playerAssignmentProgress() {
        return this.entityManager.getPlayer().getAssignmentProgress();
    }
    
    @Override
    public boolean playerHasAssignment() {
        if(entityManager.getPlayer().getCurrentAssignment() != null) {
            return true;
        } else {
            return false;
        }
    }

}
