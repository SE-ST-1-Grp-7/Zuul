package starter;

import Acq.IBusiness;
import Acq.IData;
import Acq.IUI;
import business.BusinessFacade;
import UI.UI;
import data.DataFacade;


/**
 *
 * @author J
 */
public class Starter {
    public static void main(String[] args) {
        IBusiness business = new BusinessFacade();
        IData data = new DataFacade();
        IUI ui = new UI();
        business.injectData(data);
        ui.injectBusiness(business);
        ui.openUI();
        

    }
}
