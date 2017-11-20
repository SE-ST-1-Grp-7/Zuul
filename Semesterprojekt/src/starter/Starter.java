package starter;

import Acq.IBusiness;
import Acq.IUI;
import business.BusinessFacade;
import UI.UI;


/**
 *
 * @author J
 */
public class Starter {
    public static void main(String[] args) {
        IBusiness business = new BusinessFacade();
        IUI ui = new UI();
        ui.injectBusiness(business);
        ui.openUI();
        

    }
}
