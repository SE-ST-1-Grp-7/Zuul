package UI;

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
import javafx.scene.control.Button;
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
 *
 * @author J
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
    private Button tryAgainButton;
    @FXML
    private TextField nameField;

    private EventHandler<KeyEvent> movementhandler = (KeyEvent event) -> {
        switch (event.getCode()) {
            case D:
                ib.playerMove("right");
                break;
            case A:
                ib.playerMove("left");
                break;
            case W:
                ib.playerMove("up");
                break;
            case S:
                ib.playerMove("down");
                break;
            case Z:
                ib.playerInteract();
                break;
            case DIGIT1:
                listView.getSelectionModel().clearAndSelect(0);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
            case DIGIT2:
                listView.getSelectionModel().clearAndSelect(1);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
            case DIGIT3:
                listView.getSelectionModel().clearAndSelect(2);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
            case DIGIT4:
                listView.getSelectionModel().clearAndSelect(3);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
            case DIGIT5:
                listView.getSelectionModel().clearAndSelect(4);
                useItem((IItem) listView.getSelectionModel().getSelectedItem());
                break;
        }
    };

    @FXML
    private ImageView minimapViewer;


    /**
     *
     *
     * @param event
     */
    @FXML
    private void newGameButton(ActionEvent event) {
        if (nameField.getText().length() <= 12 && nameField.getText().length() >= 4) {
            //ib.playerSetName(nameField.getText());
            tempPlayerName = nameField.getText();
            nameField.setVisible(false);
            game();
        } else {
            bottomTextArea.appendText("Your name must be shorter than or equal to 12 characters\n"
                    + "and more than or equal to 4 characters ");
        }

    }

    private void game() {

        loop.stop();
        ib.resetGame();
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
     * fills a 2d ImageView array with images and then adds them to the gridpane
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ib = UI.getUI().getBusiness();
        Tooltip exittip = new Tooltip("Exit the game");
        Tooltip savetip = new Tooltip("Save the game");
        Tooltip loadtip = new Tooltip("Load the game");
        Tooltip highscoretip = new Tooltip("Get all highscores");
        Tooltip newGametip = new Tooltip("Start game");
        Tooltip dropItem = new Tooltip("drop the item");
        Tooltip useItem = new Tooltip("use the item");
        //Creating tooltips on buttons
        Tooltip.install(exitButton, exittip);
        Tooltip.install(saveButton, savetip);
        Tooltip.install(loadButton, loadtip);
        Tooltip.install(newGameButton, newGametip);
        Tooltip.install(highscoreButton, highscoretip);
        Tooltip.install(dropButton, dropItem);
        Tooltip.install(useButton, useItem);
        canvasId.getGraphicsContext2D().drawImage(new Image("assets/start1.png"), 0, 0);
        timeLabel.setText("TIME LEFT");
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
                            + Integer.toString(minutes)
                            + ":" + Integer.toString(seconds));

                    //   timeLabel.setText("TIME LEFT: " +
                    //           Integer.toString(seconds));
                    // calls gameloop
                    ib.loop();
                    prevNanoTime = currentNanoTime;
                }
                // draw room 60 times per second
                canvasId.getGraphicsContext2D().clearRect(0, 0, 640, 640);
                drawImages(canvasId.getGraphicsContext2D());
                roomViewer.setText("Current Room: " + ib.playerCurrentRoomName());
                energyViewer.setText("Energy: " + ib.playerEnergy());
                gradedAssignmentViewer.setText(ib.amountOfGradedAssignments()
                        + "/10 assignments graded");
                minimapViewer.setImage(new Image(ib.minimapImage()));
                if (ib.isGameOver()) { // checks if gameOver
                    canvasId.getGraphicsContext2D().drawImage(new Image("assets/gameOver.png"), 0, 0); // draw gameover image
                    loop.stop();
                    bottomTextArea.appendText("You've lost the game");
                }
                if (wincodition() == true) {
                    loop.stop();
                }
            }

        };

        minimapViewer.setImage(minimap);
    }

    /**
     *
     *
     * @param gc
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
     * returns an image depending on entity image string
     *
     * @param row
     * @param col
     * @return
     */
    public Image choosePic(int row, int col) {
        // This will cause all entity images to be loaded repeatedly for now.
        Image image = new Image(ib.entityGetImage(row, col));
        return image;
    }

    /**
     * exits game
     *
     * @param event
     */
    @FXML
    private void exitButton(ActionEvent event) {
        Platform.exit(); //exit the application
    }

    /**
     * Load data from files if there is any previous save data.
     *
     * @param event ActionEvent, when button is pushed.
     */
    @FXML
    private void loadButton(ActionEvent event) {
        game();
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
    }

    /**
     * saves the game
     *
     * @param event
     */
    @FXML
    private void saveButton(ActionEvent event) {
        bottomTextArea.appendText("The game is now saved.\n");
        ib.saveGame();
    }

    /**
     * displays highscore
     *
     * @param event
     */
    @FXML
    private void highscoreButton(ActionEvent event) {
        ib.loadXML();
        bottomTextArea.appendText("\nThe highscore list for World of SDU\n");
        bottomTextArea.appendText("---------------------------------\n");
        bottomTextArea.appendText("NO.\t\tNAME\t\t SCORE\n");
        bottomTextArea.appendText(ib.displayHighscore());
        
    }

    /**
     * uses the item selected in the listview
     *
     * @param event
     */
    @FXML
    private void useButton(ActionEvent event) {
        // If nothing, do nothing.
        if (listView.getSelectionModel().getSelectedItem() == null) {
            bottomTextArea.appendText("You have not selected a item to use" + "\n");
            // If false, use item.
        } else {
            useItem((IItem) listView.getSelectionModel().getSelectedItem());
        }
    }


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
                    bottomTextArea.appendText("You don't have enough energy" + "\n");
                } else {
                    bottomTextArea.appendText("You're not in the teacher's room" + "\n");
                }
            }
        }
    }

    /**
     * drops the item selected in the listview
     *
     * @param event
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
     *
     *
     * @return
     */
    private boolean wincodition() {
        if (ib.amountOfGradedAssignments() >= 10) {
            bottomTextArea.clear();
            bottomTextArea.appendText("You have won the game, you are the "
                    + "best professer around" + "\n");
            canvasId.getGraphicsContext2D().drawImage(new Image("assets/win2.png"), 0, 0); // draw win screen image
            bottomTextArea.appendText("The highscore list for World of SDU\n");
            bottomTextArea.appendText("---------------------------------\n");
            bottomTextArea.appendText("NO.\t\tNAME\t\t SCORE\n");
            bottomTextArea.appendText(ib.displayHighscore());
            return true;
        }
        return false;
    }

    @FXML
    private void nameFieldClick(MouseEvent event) {
        nameField.clear();
    }
}
