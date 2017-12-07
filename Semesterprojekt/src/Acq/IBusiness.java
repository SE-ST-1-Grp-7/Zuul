package Acq;

import business.Inventory;

/**
 * IBusiness interface.
 *
 * @author J, Rasmus Willer & Niclas Johansen
 */
public interface IBusiness {
    // sets name of player
    void playerSetName(String name);
    
    // Gets value of gameOver
    boolean isGameOver();
    
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
    boolean loadGame();

    // Get tile image file name.
    String getTileImage(int row, int col);

    // Game loop.
    void loop();
    
    // Retrieve player inventory.
    Inventory playerGetInventory();
    
    // Use item.
    void itemUse(Object o);
    
    // Drop item from inventory.
    void itemDrop(Object o);
    
    // Retrieve name of the room player is currently in.
    String playerCurrentRoom();
    
    // Set game to initial state.
    void resetGame();
    
    // Retrive player's current energy level.
    int playerEnergy();
    
    // Retrieve the value of how many assignments have been graded.
    int amountOfGradedAssignments();
    
    // Get amount of seconds left
    int getSeconds();
    
    boolean isAssignment(Object o);
    
}
