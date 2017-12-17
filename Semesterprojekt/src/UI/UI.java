package UI;

// IMPORTS

import Acq.IBusiness;
import Acq.IUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * User Interface facade.
 * 
 * @author Jonas Bjørnstorp
 * @author Niclas Johansen
 */
public class UI extends Application implements IUI {
    private IBusiness ib;
    private static UI ui;
    
    /**
     * Retrieve UI.
     * 
     * @return      UI, user interface to get.
     */
    public static UI getUI() {
        return ui;
    }
    
    /**
     * Retrieve business interface.
     * 
     * @return      IBusiness, business interface to get.
     */
    public IBusiness getBusiness() {
        return this.ib;
    }

    /**
     * Override; load FXML and create GUI.
     * 
     * @param primaryStage      Stage, GUI stage.
     * @throws IOException      throws, if fxml file is missing.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "FXMLDocument.fxml")); 
        Parent p = loader.load();

        Scene scene = new Scene(p);
        
        // Loads the font Bangers
        Font.loadFont(getClass().getResourceAsStream("/assets/Bangers-Regular.ttf"), 16);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    /**
     * Override; inject business interface to UI.
     * 
     * @param businessFacade    IBusiness, business access point.
     */
    @Override
    public void injectBusiness(IBusiness businessFacade) {
        this.ib = businessFacade;
    }

    /**
     * Override; launch GUI.
     */
    @Override
    public void openUI() {
        ui = this;
        launch();
    }
}
