/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zuulgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import worldofzuul.Game;
import worldofzuul.People.Player;
import worldofzuul.items.Coffee;
import worldofzuul.items.Item;
import worldofzuul.mapAndRooms.RoomManager;
import worldofzuul.userCommand.Command;
import worldofzuul.userCommand.CommandWord;

/**
 *
 * @author J
 */
public class FXMLDocumentController implements Initializable {

    private Player g;
    private RoomManager rm;
    private final int X = 64;
    private final int Y = 64;
    private Pane pane;
    @FXML
    private Button buttonSavePressed;
    @FXML
    private Button highscoreButton;
    @FXML
    private Button quitGameButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Canvas c;
    @FXML
    private GridPane gp;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        GraphicsContext gc = c.getGraphicsContext2D();
        // set focus on canvas
        gp.setFocusTraversable(true);
        // set keylistener
        gp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case D:
                        g.move(new Command(CommandWord.MOVE, "right"));
                        System.out.println("hello");
                        break;
                    case A:
                        g.move(new Command(CommandWord.MOVE, "left"));
                        break;
                    case W:
                        g.move(new Command(CommandWord.MOVE, "up"));
                        break;
                    case S:
                        g.move(new Command(CommandWord.MOVE, "down"));
                        break;
                    case SPACE:
                        // do stuff
                        break;
                }
            }
        });

        // current time in nano time
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // draw room 60 times per second
                c.getGraphicsContext2D().clearRect(0, 0, 640, 640); // good guy rasmus
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
        g = new Player(0,0, "mango");
        rm = new RoomManager();
        g.setCurrentRoom(rm.getCurrentRoom());
        g.getCurrentRoom().roomArray[0][0] = g;
        Coffee a = new Coffee(0,1,64,64);
        Coffee b = new Coffee(1,2,64,64);
        Coffee c = new Coffee(2,3,64,64);
        Coffee d = new Coffee(3,4,64,64);
        Coffee e = new Coffee(4,5,64,64);
        Coffee f = new Coffee(5,6,64,64);
        g.getCurrentRoom().roomArray[a.getY()][a.getX()] = a;
        g.getCurrentRoom().roomArray[b.getY()][b.getX()] = b;
        g.getCurrentRoom().roomArray[c.getY()][c.getX()] = c;
        g.getCurrentRoom().roomArray[d.getY()][d.getX()] = d;
        g.getCurrentRoom().roomArray[e.getY()][e.getX()] = e;
        g.getCurrentRoom().roomArray[f.getY()][f.getX()] = f;
        System.out.println("hello");
    }

    public void drawImages(GraphicsContext gc) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // render floor
                Image tile = new Image("testSquare.png");
                gc.drawImage(tile, X * j, Y * i);
                // render entities
                if (g.getCurrentRoom().roomArray[i][j] != null) {
                    Image entity = new Image(choosePic(i, j));
                    gc.drawImage(entity, X * j, Y * i);
                }
            }
        }

    }

    public String choosePic(int row, int col) {
        // magic

        if (g.getCurrentRoom().roomArray[row][col] == null) {
            return "testSquare.png";
        } else if (g.getCurrentRoom().roomArray[row][col] instanceof Item) {
            return "500.png";
        } else {
            return "boi.png";
        }
    }

    @FXML
    private void highscoreButtonPressed(ActionEvent event) {
    }

    @FXML
    private void quitGameButton(ActionEvent event) {
    }

}
