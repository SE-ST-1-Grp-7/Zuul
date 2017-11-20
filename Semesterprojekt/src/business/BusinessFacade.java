/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import Acq.IBusiness;
import javafx.scene.image.Image;

/**
 *
 * @author J
 */
public class BusinessFacade implements IBusiness {
    private EntityManager entityManager;
    private RoomManager roomManager;
    
    
    /**
     * zero-arg constructor
     * assigns values to EntityManager & RoomManager
     */
    public BusinessFacade() {
        roomManager = new RoomManager();
        entityManager = new EntityManager(roomManager);
    }
    /**
     * calls .move on player
     * @param direction 
     */
    @Override
    public void playerMove(String direction) {
        // player currently saved in an arraylist - might/should be changed
        entityManager.getPlayer().move(direction);
    }
    /**
     * calls .interact on player
     * player interacts with the object in x direction
     * @param direction 
     */
    @Override
    public void playerInteract(String direction) {
        entityManager.getPlayer().interact();
    }
    /**
     * calls .dropItem on player's inventory
     * drops the item located at index i
     * @param index 
     */
    @Override
    public void playerDropItem(int index) {
        // note to self: move dropItem to player
         entityManager.getPlayer().inventory().dropItem(index, entityManager.getPlayer());
    }
    /**
     * gets the image of an entity placed at row, col
     * if no entity is present -> return an empty square
     * @param row
     * @param col
     * @return 
     */
    @Override
    public Image entityGetImage(int row, int col) {
        if(roomManager.getCurrentRoom().entityArray[row][col] != null) {
            return roomManager.getCurrentRoom().entityArray[row][col].getEntityImage();
        } else {
            Image i = new Image("testSquare.png");
            return i;
        }
    }

    @Override
    public void saveGame() {
        entityManager.saveGame();
    }

    @Override
    public void loadGame() {
        entityManager.loadGame();
    }
    
    
}
