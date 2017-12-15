package Acq;

/**
 * Interface for User Interface layer.
 * 
 * @author Jonas Bjørnstorp & Niclas Johansen
 */
public interface IUI {
    /**
     * Set business interface to UI.
     * 
     * @param businessFacade    IBusiness, business access point.
     */
    void injectBusiness(IBusiness businessFacade);

    /**
     * Launch GUI.
     */
    void openUI();
}