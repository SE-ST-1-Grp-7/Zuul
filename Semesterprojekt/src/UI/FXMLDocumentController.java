/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Acq.IBusiness;
import Acq.IUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author J
 */
public class FXMLDocumentController implements Initializable, IUI {

    // business facade
    private IBusiness ib;
    private final int X = 64;
    private final int Y = 64;
    private Pane pane;
    private Canvas c;
    private GridPane gp;
    @FXML
    private Canvas canvasId;

    private void handleButtonAction(ActionEvent event) {
        GraphicsContext gc = canvasId.getGraphicsContext2D();
        // set focus on canvas
        gp.setFocusTraversable(true);
        // set keylistener
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
                    case SPACE:
                        ib.playerInteract("idk");
                        break;
                }
            }
        });

        // current time in nano time
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
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
    }

    public void drawImages(GraphicsContext gc) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // render floor
                Image tile = new Image("testSquare.png");
                gc.drawImage(tile, X * j, Y * i);
                // render entities
                gc.drawImage(choosePic(i,j), X * j, Y * i);

            }
        }

    }

    public Image choosePic(int row, int col) {
        // magic
        return ib.entityGetImage(row, col);
    }


    @Override
    public void injectBusiness(IBusiness businessFacade) {
        this.ib = businessFacade;
    }

    @Override
    public void openUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
