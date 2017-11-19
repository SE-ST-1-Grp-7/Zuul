/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.business;

import worldofzuul.entities.EntityManager;
import worldofzuul.mapAndRooms.RoomManager;

/**
 *
 * @author J
 */
public class BusinessFacade {
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
    public void playerMove(String direction) {
        // player currently saved in an arraylist - might/should be changed
        entityManager.getPlayer().get(0).move(direction);
    }
    /**
     * calls .interact on player
     * player interacts with the object in x direction
     * @param direction 
     */
    public void playerInteract(String direction) {
        entityManager.getPlayer().get(0).interact(direction);
    }
    /**
     * calls .dropItem on player's inventory
     * drops the item located at index i
     * @param index 
     */
    public void playerDropItem(int index) {
        // note to self: move dropItem to player
         entityManager.getPlayer().get(0).inventory().dropItem(index, entityManager.getPlayer().get(0));
    }
    
}
