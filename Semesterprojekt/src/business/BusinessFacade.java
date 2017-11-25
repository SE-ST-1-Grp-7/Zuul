package business;

import Acq.IBusiness;

/**
 *
 * @author J
 */
public class BusinessFacade implements IBusiness {

    private EntityManager entityManager;
    private RoomManager roomManager;

    /**
     * zero-arg constructor assigns values to EntityManager & RoomManager
     */
    public BusinessFacade() {
        roomManager = new RoomManager();
        entityManager = new EntityManager(roomManager);
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
     * calls .dropItem on player's inventory drops the item located at index i
     *
     * @param index
     */
    @Override
    public void playerDropItem(int index) {
        // note to self: move dropItem to player
        entityManager.getPlayer().inventory().dropItem(index, entityManager.getPlayer());
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
            if(roomManager.getCurrentRoom().getEntities()[row][col] instanceof Student){
            return entityManager.getStudentList().get(0).getEntityImage(); //get(0) has to be changed to match the csv file to show the right student
            //return roomManager.getCurrentRoom().getEntities()[row][col].getEntityImage();
//            
            } else{
                return "/textures/player.png"; 
            }
        } else {
            return "testSquare.png";
        }
    }

    @Override
    public void saveGame() {
        entityManager.saveGame();
    }

    @Override
    public void loadGame() {
        entityManager.getPlayer().getCurrentRoom().entityArray[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()]=null;
        entityManager.loadGame();
        entityManager.getPlayer().getCurrentRoom().entityArray[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()]=entityManager.getPlayer();
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
        //pseudo logic:
        //Door
        //setplayer
        //set roomManager
        //onInteract
        roomManager.getCurrentRoom().entityArray[entityManager.getPlayer().getY()][entityManager.getPlayer().getX()].onInteract();
    }
}
