package UI;

import Acq.IBusiness;
import Acq.IUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Søren Bendtsen & Niclas Johansen
 */
public class UI extends Application implements IUI {
    private IBusiness ib;
    private static UI ui;
    
    /**
     * 
     * 
     * @return 
     */
    public static UI getUI() {
        return ui;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public IBusiness getBusiness() {
        return this.ib;
    }

    /**
     * 
     * 
     * @param primaryStage
     * @throws IOException 
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "FXMLDocument.fxml")); 
        Parent p = loader.load();

        Scene scene = new Scene(p);
        scene.getStylesheets().add(
                "https://fonts.googleapis.com/css?family=Bangers");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    /**
     * 
     * 
     * @param businessFacade 
     */
    @Override
    public void injectBusiness(IBusiness businessFacade) {
        this.ib = businessFacade;
    }

    /**
     * 
     */
    @Override
    public void openUI() {
        ui = this;
        launch();
    }
}
