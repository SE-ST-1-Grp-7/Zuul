package starter;

import Acq.IBusiness;
import Acq.IUI;
import worldofzuul.business.BusinessFacade;
import zuulgui.NewFXMain;


/**
 *
 * @author J
 */
public class glueCode {
    public static void main(String[] args) {
        IBusiness business = new BusinessFacade();
        IUI ui = new NewFXMain();
        ui.openUI();
        

    }
}
