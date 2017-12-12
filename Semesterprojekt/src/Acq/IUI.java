package Acq;

/**
 * Interface for User Interface layer.
 * 
 * @author J & Niclas Johansen
 */
public interface IUI {
    // Set business interface.
    void injectBusiness(IBusiness businessFacade);
    // Launch GUI.
    void openUI();
}