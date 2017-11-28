package Acq;

import business.Inventory;
import javafx.scene.control.ListView;

/**
 * IBusiness interface.
 * 
 * @author J & Rasmus Willer
 */
public interface IBusiness {
    // Move player.
    void playerMove(String direction);
    // Player interacts.
    void playerInteract();
    // Get texture path for entity.
    String entityGetImage(int row, int col);
    // Save game and data.
    void saveGame();
    // Load game from memory.
    void loadGame();
    // Get tiles
    String getTileImage(int row, int col);
    
    void goThroughDoor();
    void loop();
    Inventory playerGetInventory();
    void itemUse(Object o);
    void itemDrop(Object o);
}
