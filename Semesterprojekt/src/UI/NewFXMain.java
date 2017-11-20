package UI;

import Acq.IBusiness;
import Acq.IUI;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import business.BusinessFacade;

/**
 *
 * @author Søren Bendtsen
 */
public class NewFXMain extends Application implements IUI {
    private IBusiness ib;
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent p = loader.load();
	IUI controller = (IUI)loader.getController();
	controller.injectBusiness(ib);
        Scene scene = new Scene(p);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void injectBusiness(IBusiness businessFacade) {
        ib = businessFacade;
    }

    @Override
    public void openUI() {
        launch();
    }
    
}
