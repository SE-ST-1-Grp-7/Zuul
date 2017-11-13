package FX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import worldofzuul.Game;
import worldofzuul.Highscore.Highscore;

/**
 * FXML Controller class
 *
 * @author Søren Bendtsen
 */
public class FXMLController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private Button buttonSavePressed;
    @FXML
    private Button quitGameButton;
    @FXML
    private Button highscoreButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip tooltip = new Tooltip("Exit the game");
        Tooltip save = new Tooltip("Save the game");

        textArea.setText("Mit navn er Johammed");
        
        //Set text on exit button
        quitGameButton.setText("Exit");
        
        //Creating tooltips on buttons
        Tooltip.install(quitGameButton, tooltip);
        Tooltip.install(buttonSavePressed, save);

    }

    @FXML
    private void buttonSavePressed(ActionEvent event) {
        textArea.appendText("\nJEG KAN IKKE GEMME!!!");

    }

    @FXML
    private void quitGameButton(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void highscoreButtonPressed(ActionEvent event) {
        Highscore high = new Highscore();
        high.printHighscore();
    }

}
