package UI;

// IMPORTS

import Acq.IBusiness;
import Acq.IItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * GUI, event and handling management.
 * 
 * @author Niclas Johansen
 * @author Jonas Bjørstorp
 * @author Rasmus Willer
 * @author Magnus Mortensen
 * @author Robin Petersen
 */
public class FXMLDocumentController implements Initializable {
    // business facade
    private IBusiness ib;
    private final int X = 64;
    private final int Y = 64;
    // used for handling the gameloop
    private long prevNanoTime = System.nanoTime();
    private int seconds = 0;
    private int minutes = 0;
    private AnimationTimer loop;
    private long diff = 0;
    private Pane pane;
    private String tempPlayerName;

    private Canvas c;
    @FXML
    private GridPane gp;
    @FXML
    private Canvas canvasId;
    @FXML
    private Button exitButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button highscoreButton;
    @FXML
    private Button useButton;
    @FXML
    private Button dropButton;
    @FXML
    private ListView listView;
    @FXML
    private TextArea bottomTextArea;
    @FXML
    private Label timeLabel;
    @FXML
    private Label roomViewer;
    @FXML
    private Label energyViewer;
    @FXML
    private Label gradedAssignmentViewer;
    @FXML
    private TextField nameField;

    // Switch case for hotkeys.
    private EventHandler<KeyEvent> movementhandler = (KeyEvent event) -> {
        switch (event.getCode()) {
            // Move right.
            case D:
                ib.playerMove("right");
                break;
            
            // Move left.
            case A:
                ib.playerMove("left");
                break;
            
            // Move up.
            case W:
                ib.playerMove("up");
                break;
                
            // Move down.
            case S:
                ib.playerMove("down");
                break;
                
            // Interact with what the player is facing.
            case E:
                ib.playerInteract();
                break;
                
            // Use the 1st item in inventory.
            case DIGIT1:
                listView.getSelectionModel().clearAndSelect(0);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
                
            // Use the 2nd item in inventory.
            case DIGIT2:
                listView.getSelectionModel().clearAndSelect(1);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
                
            // Use the 3rd item in inventory.
            case DIGIT3:
                listView.getSelectionModel().clearAndSelect(2);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
                
            // Use the 4th item in inventory.
            case DIGIT4:
                listView.getSelectionModel().clearAndSelect(3);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
                
            // Use the 5th item in inventory.
            case DIGIT5:
                listView.getSelectionModel().clearAndSelect(4);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
        }
    };
    @FXML
    private ImageView minimapViewer;

    /**
     * New game button in GUI. Requires the entered name on screen to be
     * accepted before a new game is made.
     *
     * @param event     ActionEvent, upon button used.
     */
    @FXML
    private void newGameButton(ActionEvent event) {
        if(nameField.isVisible() == false){
            canvasId.getGraphicsContext2D().drawImage(
                new Image("assets/start1.png"), 0, 0);
            loop.stop();
            nameField.setVisible(true);
        }
        if (nameField.getText().length() <= 8 &&
                nameField.getText().length() >= 1) {
            //ib.playerSetName(nameField.getText());
            tempPlayerName = nameField.getText();
            nameField.setVisible(false);
            game(nameField.getText());
        } else {
            bottomTextArea.appendText(
                    "Your name must be shorter than or equal to 8 characters\n"
                    + "and more than or equal to 1 characters ");
        }
    }

    /**
     * Call for reset of game to new. 
     * 
     * @param playerName    String, player name.
     */
    private void game(String playerName) {
        loop.stop();
        ib.resetGame();
        // Instantiate entities in the game.
        ib.initGame(playerName);
        // Display welcome message.
        bottomTextArea.appendText("Welcome to the Professor Game!\n"
                + "One of the hardest games on SDU.\n");
        GraphicsContext gc = canvasId.getGraphicsContext2D();
        //link the listView to the inventory
        listView.setItems(ib.playerGetInventory());

        // We have to move to to another place cause it is movement
        // set focus on canvas
        gp.setFocusTraversable(true);
        //set keylistener
        gp.setOnKeyPressed(movementhandler);

        ib.playerSetName(tempPlayerName);
        // current time in nano time
        final long startNanoTime = System.nanoTime();
        loop.start();
    }

    /**
     * Override; initialize tooltips and game loop.
     *
     * @param url   URL, used to resolve relative path to root object.
     * @param rb    ResourceBoundle, used to localize root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ib = UI.getUI().getBusiness();
        // Instantiate the tooltips for the buttons.
        Tooltip exittip = new Tooltip("Exit the game");
        Tooltip savetip = new Tooltip("Save the game");
        Tooltip loadtip = new Tooltip("Load the game");
        Tooltip highscoretip = new Tooltip("Get all highscores");
        Tooltip newGametip = new Tooltip("Start game");
        Tooltip dropItem = new Tooltip("drop the item");
        Tooltip useItem = new Tooltip("use the item");
        // Set tooltips on buttons
        Tooltip.install(exitButton, exittip);
        Tooltip.install(saveButton, savetip);
        Tooltip.install(loadButton, loadtip);
        Tooltip.install(newGameButton, newGametip);
        Tooltip.install(highscoreButton, highscoretip);
        Tooltip.install(dropButton, dropItem);
        Tooltip.install(useButton, useItem);
        canvasId.getGraphicsContext2D().drawImage(
                new Image("assets/start1.png"), 0, 0);
        
        timeLabel.setText("TIME LEFT");
        
        // Game loop.
        loop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // check if player lost
                // ensures that the loop only gets called once per second
                diff = currentNanoTime - prevNanoTime;

                if (diff >= 1000000000) {
                    minutes = ib.getSeconds() / 60;
                    seconds = ib.getSeconds() % 60;
                    timeLabel.setText("Time LEFT: "
                            + String.format("%02d", minutes)
                            + ":" + String.format("%02d", seconds));

                    //   timeLabel.setText("TIME LEFT: " +
                    //           Integer.toString(seconds));

                    if(ib.playerHasAssignment()) {
                        bottomTextArea.appendText("Assignment progress: " +
                                ib.playerAssignmentProgress() + "%\n");
                    }
                    // calls gameloop
                    ib.loop();
                    prevNanoTime = currentNanoTime;
                }
                // draw room 60 times per second
                canvasId.getGraphicsContext2D().clearRect(0, 0, 640, 640);
                drawImages(canvasId.getGraphicsContext2D());
                roomViewer.setText("Current Room: " +
                        ib.playerCurrentRoomName());
                energyViewer.setText("Energy: " + ib.playerEnergy());
                gradedAssignmentViewer.setText(ib.amountOfGradedAssignments()
                        + "/10 assignments graded");
                minimapViewer.setImage(new Image(ib.minimapImage()));
                if (ib.isGameOver()) { // checks if gameOver
                    // draw game over image
                    canvasId.getGraphicsContext2D().drawImage(
                            new Image("assets/gameOver.png"), 0, 0);
                    loop.stop();
                    bottomTextArea.appendText("You've lost the game");
                }
                if (wincodition() == true) {
                    loop.stop();
                }
                // When student interacts with you write text and draw image.
                if (ib.getInteractionHappend()) {
                    canvasId.getGraphicsContext2D().drawImage(
                            new Image("assets/question-student.png"), 0, 0);
                    bottomTextArea.appendText("Me: Ouch!!\n");
                    bottomTextArea.appendText("Student: Professor, professor i "
                            + "got 10 questions for you!!\n");
                    ib.setInteractionHappend(false);
                }
                
                /* If the player interacts with a student, write text and set
                   playerAskedStudent back to false. */
                if(ib.getPlayerAskedStudent()){
                    bottomTextArea.appendText("Me: Hello student\n");
                    ib.setPlayerAskedStudent(false);
                }
                
                /* If the player interacts with a tutor, write text and set
                   playerAskedTutor back to false. */
                if(ib.getPlayerAskedTutor()){
                    bottomTextArea.appendText("Me: Hello tutor\n");
                    ib.setPlayerAskedTutor(false);
                }
            }
        };

        // Set minimap image.
        minimapViewer.setImage(new Image("/assets/Minimap/minimap.png"));
    }

    /**
     * Draw tiles and entities to GUI.
     *
     * @param gc    GraphicsContext, image creation.
     */
    public void drawImages(GraphicsContext gc) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // render floor
                Image tile = new Image(ib.getTileImage(i, j));
                gc.drawImage(tile, X * j, Y * i);
                // render entities
                if (!ib.entityGetImage(i, j).equals("testSquare.png")) {
                    gc.drawImage(choosePic(i, j), X * j, Y * i);
                }
            }
        }
    }

    /**
     * Returns an image depending on entity image string.
     *
     * @param row       int, row of grid position of entity.
     * @param col       int, column of grid position of entity.
     * @return          Image, the created image object.
     */
    public Image choosePic(int row, int col) {
        // This will cause all entity images to be loaded repeatedly for now.
        Image image = new Image(ib.entityGetImage(row, col));
        return image;
    }

    /**
     * Exit game button in GUI.
     *
     * @param event     ActionEvent, upon button used.
     */
    @FXML
    private void exitButton(ActionEvent event) {
        Platform.exit(); //exit the application
    }

    /**
     * Load game button in GUI. Load data from files if there is any previous
     * save data.
     *
     * @param event     ActionEvent, when button is pushed.
     */
    @FXML
    private void loadButton(ActionEvent event) {
        game(ib.getLoadName());
        nameField.setVisible(false);
        // Call load game and get a boolean back, true is successfull.
        boolean status = ib.loadGame();

        // If successfull print load message to screen.
        if (status) {
            bottomTextArea.appendText("The game is now loaded.\n");

            // Otherwise print there was no data.
        } else {
            bottomTextArea.appendText("There is no load data.\n");
        }
        listView.setItems(ib.playerGetInventory());
    }

    /**
     * Save game button in GUI. If game is not over calls for collection of game
     * data and save to file.
     *
     * @param event     ActionEvent, upon button is used.
     */
    @FXML
    private void saveButton(ActionEvent event) {
        // If game is over, ignore request.
        if(ib.isGameOver()) {
            bottomTextArea.appendText("\nu cant save ur ded lmao");
            return;
        }
        // Otherwise call save process.
        bottomTextArea.appendText("The game is now saved.\n");
        ib.saveGame();
    }

    /**
     * Displays highscore button in GUI.
     *
     * @param event     ActionEvent, upon button is used.
     */
    @FXML
    private void highscoreButton(ActionEvent event) {
        // Loads
        ib.loadXML();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm());
        dialogPane.getStyleClass().add("alertBox");

        alert.setGraphic(null);
        alert.setTitle("HIGHSCORES");
        alert.setHeaderText("WHO'S BEST?" );
        alert.setContentText( "The highscore list for Professor Game\n" +
                "---------------------------------\n" +
                "NO.\t\tNAME\t\t SCORE\n" +
                ib.displayHighscore());
        alert.getButtonTypes().remove(1);
        ButtonType buttonTypeClose = new ButtonType("CLOSE");

        alert.getButtonTypes().set(0, buttonTypeClose);

        alert.showAndWait();
    }

    /**
     * Use button in GUI. Uses the item selected in the listview.
     *
     * @param event     ActionEvent, upon button is used.
     */
    @FXML
    private void useButton(ActionEvent event) {
        // If nothing, do nothing.
        if (listView.getSelectionModel().getSelectedItem() == null) {
            bottomTextArea.appendText("You have not selected a item to use\n");
            // If false, use item.
        } else {
            useItem((IItem) listView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Use item selected in inventory.
     * 
     * @param item      IItem, the selected item in inventory.
     */
    private void useItem(IItem item) {
        if (item == null) {
            bottomTextArea.appendText("No item selected" + "\n");
            return;
        }

        if (ib.itemUse(item)) {
            bottomTextArea.appendText("You just used " + item.getName() + "\n");
        } else {
            if (ib.isAssignment(item)) {
                if (ib.playerEnergy() < 20) {
                    bottomTextArea.appendText("You don't have enough energy\n");
                    
                } else if(!ib.playerCurrentRoomName().equals("teacher room")) {
                    bottomTextArea.appendText("You're not in the teacher's room"
                            + "\n");
                    
                } else {
                    bottomTextArea.appendText("You're busy grading another " +
                            "assignment" + "\n");
                }
            }
        }
    }

    /**
     * Drop button in GUI. Drops the item selected in the listview.
     *
     * @param event     ActionEvent, upon button used.
     */
    @FXML
    private void dropButton(ActionEvent event) {
        if ((listView.getSelectionModel().getSelectedItem() != null)) {
            bottomTextArea.appendText("You just dropped "
                    + listView.getSelectionModel().getSelectedItem().toString()
                    + "\n");
            ib.itemDrop((IItem) listView.getSelectionModel().getSelectedItem());
            
        } else {
            bottomTextArea.appendText("You have no selected items to drop."
                    + "\n");
        }
    }

    /**
     * Condition for winning the game.
     *
     * @return      boolean, if true player has won the game, otherwise false.
     */
    private boolean wincodition() {
        if (ib.amountOfGradedAssignments() >= 10) {
            bottomTextArea.clear();
            ib.saveHighscore();
            bottomTextArea.appendText("You have won the game, you are the "
                    + "best professer around" + "\n");
            // draw win screen image
            canvasId.getGraphicsContext2D().drawImage(
                    new Image("assets/win2.png"), 0, 0);
            bottomTextArea.appendText("The highscore list for Professor Game\n");
            bottomTextArea.appendText("---------------------------------\n");
            bottomTextArea.appendText("NO.\t\tNAME\t\t SCORE\n");
            bottomTextArea.appendText(ib.displayHighscore());
            return true;
        }
        return false;
    }

    /**
     * Upon click on name field call clear.
     * 
     * @param event     MouseEvent, upon clicking in field.
     */
    @FXML
    private void nameFieldClick(MouseEvent event) {
        nameField.clear();
    }
}