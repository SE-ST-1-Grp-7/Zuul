package starter;

// IMPORTS

import Acq.IBusiness;
import Acq.IData;
import Acq.IUI;
import business.BusinessFacade;
import UI.UI;
import data.DataFacade;


/**
 * Starting point of the project. The starter class instantiates the
 * architecture layers and initialize the game.
 * 
 * @author Jonas, Niclas & Rasmus Willer
 */
public class Starter {
    public static void main(String[] args) {
        // Instantiate the three layers: data, business and UI.
        IData data = new DataFacade();
        IBusiness business = new BusinessFacade();
        IUI ui = new UI();
        // Data is injected into business layer.
        business.injectData(data);
        // Business is injecting into UI layer.
        ui.injectBusiness(business);
        // Game mechanics are loaded.
        business.resetGame();
        // Start GUI.
        ui.openUI();
    }
}