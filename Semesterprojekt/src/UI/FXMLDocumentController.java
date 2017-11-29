package UI;

import Acq.IBusiness;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
    private long diff = 0;
    private Pane pane;
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
    private void newGameButton(ActionEvent event) {
        GraphicsContext gc = canvasId.getGraphicsContext2D();
        //link the listView to the inventory
        listView.setItems(ib.playerGetInventory().getInventory());

        // We have to move to to another place cause it is movement
        // set focus on canvas
        gp.setFocusTraversable(true);
        //set keylistener
        gp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                }
            }
        });
        // current time in nano time
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // ensures that the loop only gets called once per second
                diff = currentNanoTime - prevNanoTime;
                if (diff >= 1000000000) {
                    // calls gameloop
                    ib.loop();
                    prevNanoTime = currentNanoTime;
                }
                // draw room 60 times per second
                canvasId.getGraphicsContext2D().clearRect(0, 0, 640, 640); // good guy rasmus
                drawImages(gc);
            }
        }.start();

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

    }

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
     * loads save files
     *
     * @param event
     */
    @FXML
    private void loadButton(ActionEvent event) {
        ib.loadGame();
    }

    /**
     * saves the game
     *
     * @param event
     */
    @FXML
    private void saveButton(ActionEvent event) {
        ib.saveGame();
    }

    /**
     * displays highscore
     *
     * @param event
     */
    @FXML
    private void highscoreButton(ActionEvent event) {
    }

    /**
     * uses the item selected in the listview
     *
     * @param event
     */
    @FXML
    private void useButton(ActionEvent event) {
        ib.itemUse(listView.getSelectionModel().getSelectedItem());

    }

    /**
     * drops the item selected in the listview
     *
     * @param event
     */
    @FXML
    private void dropButton(ActionEvent event) {
        if ((listView.getSelectionModel().getSelectedItem() != null)) {
            ib.itemDrop(listView.getSelectionModel().getSelectedItem());
        }
    }

}
