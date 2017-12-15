package Acq;

import javafx.collections.ObservableList;

/**
 * IBusiness interface.
 *
 * @author Jonas Bjørstorp, Magnus Mortensen, Søren Bendtsen, Rasmus Willer & Niclas Johansen
 */
public interface IBusiness {
    /**
     * Set player's name.
     * 
     * @param name     String, desired name of player.
     */
    void playerSetName(String name);

    /**
     * Checks if the player has lost the game.
     * 
     * @return      boolean, whether or not the game is over.
     */
    boolean isGameOver();

    /**
     * Inject data facade.
     * 
     * @param dataLayer     IData, new data layer object.
     */
    void injectData(IData dataLayer);
    
    /**
     * Get highscore from data layer and return it to where it was called.
     * 
     * @return  String, contains the highscore data.
     */
    String displayHighscore();

    /**
     * Save highscore with player name and seconds it took 
     * to beat the game.
     */
    void saveHighscore();

    /**
     * Load XML from data layer.
     */
    void loadXML();

    /**
     * Upon player move, move according to string parsed direction.
     *
     * @param direction     String, direction to move.
     */
    void playerMove(String direction);

    /**
     * Upon interaction with player, call interaction method.
     */
    void playerInteract();

    /**
     * Gets the image of an entity placed at (row, col).
     *
     * @param row   int, X grid position in room.
     * @param col   int, Y grid position in room.
     * @return      String, file-name of image source.
     */
    String entityGetImage(int row, int col);

    /**
     * Save game progress.
     */
    void saveGame();

    /**
     * Load game progress from previous save.
     * 
     * @return      boolean, if true, there was save data, otherwise false and
     *              no stored save data.
     */
    boolean loadGame();

    /**
     * Get image of a tile placed at (row, col).
     * If no tile is present, return an empty test square.
     *
     * @param row   int, X grid position in room.
     * @param col   int, Y grid position in room.
     * @return      String, file-name of image source.
     */
    String getTileImage(int row, int col);

    /**
     * The game loop. Responsible for moving students and managing
     * energy.
     */
    void loop();
    
    /**
     * Retrieve player's inventory object.
     * 
     * @return      Inventory, inventory object to retrieve.
     */
    ObservableList<IItem> playerGetInventory();
    
    /**
     * Upon item use. Call any effects to player and remove item from
     * the game.
     * 
     * @param i     IItem, item to be used.
     * @return      boolean, true if there was a defined IItem, otherwise false
     *              and the object was null.
     */
    boolean itemUse(IItem i);
    
    /**
     * Upon item drop. Drops a chosen item from player's inventory.
     *
     * @param i     IItem, item to be dropped.
     */
    void itemDrop(IItem i);

    /**
     * Retrieve the room, player is currently located in.
     * 
     * @return      String, name of the room, the player is in.
     */
    String playerCurrentRoomName();
    
    /**
     * Set the game to New Game state.
     */
    void resetGame();
    
    /**
     * Initialize game. Main focus is entities.
     * 
     * @param playerName    String, selected name of player.
     */
    public void initGame(String playerName);
    
    /**
     * Retrieve player's energy level.
     * 
     * @return      int, value of energy level.
     */
    int playerEnergy();
    
    /**
     * Retrieve number of graded assignments.
     * 
     * @return      int, number of assignments graded.
     */
    int amountOfGradedAssignments();

    /**
     * Retrieve seconds left after loading previous save game.
     * 
     * @return      int, time left in seconds before game over.
     */
    int getSeconds();
    
    /**
     * Check if item is an assignment.
     * 
     * @param item      IItem, item to check.
     * @return          boolean, return true if item is a instanceof Assignment.
     */
    boolean isAssignment(IItem item);
    
    /**
     * Retrieve path to get the minimapImage.
     * 
     * @return      String, path of minimap image.
     */
    String minimapImage();
    
    /**
     * Get the individuel progress of each assignment when the player is grading
     * it.
     * 
     * @return      int, progress of assignment.
     */
    int playerAssignmentProgress();
    
    // Check if player is grading an assignment.

    /**
     * Check if player is grading an assignment.
     * 
     * @return      boolean, true if player is grading a assignment, 
     *              if not return false.
     */
    boolean playerHasAssignment();
    
    /**
     * Retrieve status of interaction happened.
     * 
     * @return      boolean, status if interaction.
     */
    boolean getInteractionHappend();
    
    /**
     * Set interaction status.
     * 
     * @param b     boolean, status to set.
     */
    void setInteractionHappend(boolean b);
    
    /**
     * Get status for player has asked student.
     * 
     * @return      boolean, true if a student was asked, otherwise false.
     */
    boolean getPlayerAskedStudent();
    
    /**
     * Set status for player has asked student.
     * 
     * @param playerAskedStudent    boolean, status to set.
     */
    void setPlayerAskedStudent(boolean playerAskedStudent);
    
    /**
     * Get status for if player asked tutor.
     * 
     * @return      boolean, true if a tutor was asked, otherwise false.
     */
    boolean getPlayerAskedTutor();
    
    /**
     * Set status for player has asked tutor.
     * 
     * @param playerAskedTutor  boolean, status to set.
     */
    void setPlayerAskedTutor(boolean playerAskedTutor);

    /**
     * Retrieve the saved player name.
     * 
     * @return      String, loaded player name.
     */
    String getLoadName();
}