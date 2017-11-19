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
    // GETTERS && SETTERS
    public RoomManager getRoomManager() {
        return this.roomManager;
    }
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
    /**
     * calls .move on player
     * @param direction 
     */
    public void movePlayer(String direction) {
        // player currently saved in an arraylist - might/should be changed
        entityManager.getPlayer().get(0).move(direction);
    }
    
}
