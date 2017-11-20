package zuulgui;

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
import worldofzuul.business.BusinessFacade;

/**
 *
 * @author Søren Bendtsen
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent p = loader.load();
        IBusiness business = new BusinessFacade();
	IUI controller = (IUI)loader.getController();
	controller.injectBusiness(business);
        Scene scene = new Scene(p);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
