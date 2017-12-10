package Acq;

import javafx.collections.ObservableList;

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
    
    //save highscore
    void saveHighscore();
    
    //Load the XML-file highscore.xml
    void loadXML();
   
    // Move player.
    void playerMove(String direction);

    // Player interacts.
    void playerInteract();
  
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
    ObservableList<IItem> playerGetInventory();
    
    // Use item.
    boolean itemUse(IItem i);
    
    // Drop item from inventory.
    void itemDrop(IItem i);
    
    // Retrieve name of the room player is currently in.
    String playerCurrentRoomName();
    
    // Set game to initial state.
    void resetGame();
    
    // Activate instantiations in entity manager
    public void initGame(String playerName);
    
    // Retrive player's current energy level.
    int playerEnergy();
    
    // Retrieve the value of how many assignments have been graded.
    int amountOfGradedAssignments();
    
    // Get amount of seconds left.
    int getSeconds();
    
    // Set amount of seconds left.
    void setSeconds(int seconds);
    
    //check if item is an assignment
    boolean isAssignment(IItem item);
    
    // Retrieve path to minimap image 
    String minimapImage();
    
    // Retrieve the progress of grading an assignment
    int playerAssignmentProgress();
    
    // Check if player is grading an assignment
    boolean playerHasAssignment();
    
    // Retrieve the saved player name.
    String getLoadName();
}
