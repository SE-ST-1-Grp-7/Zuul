package business;

import Acq.IBusiness;
import Acq.IData;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


/**
 *
 * @author Niclas Johansen & J
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

    @Override
    public void saveGame() {
        // entityManager.saveGame();
        data.saveGame();
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
    public void loadGame() {
        entityManager.getPlayer().getCurrentRoom().getEntities()[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()] = null;
        entityManager.loadGame();
        entityManager.getPlayer().getCurrentRoom().getEntities()[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()] = entityManager.getPlayer();
        roomManager.setCurrentRoom(entityManager.getPlayer().getCurrentRoom());
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
        for(Student s : entityManager.getStudentList()) {
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

}
