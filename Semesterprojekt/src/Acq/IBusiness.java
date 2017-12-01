package Acq;

import business.Inventory;

/**
 * IBusiness interface.
 *
 * @author J, Rasmus Willer & Niclas Johansen
 */
public interface IBusiness {

    // Inject Data Facade
    void injectData(IData dataLayer);
    
    //display highscore
    String displayHighscore();
    
    //Load the XML-file highscore.xml
    void loadXML();
   
    // Move player.
    void playerMove(String direction);

    // Player interacts.
    void playerInteract();

    // Drop item from player.
    void playerDropItem(int index);
  
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
    
    String playerCurrentRoom();

    void resetGame();
  
    int playerEnergy();
}
