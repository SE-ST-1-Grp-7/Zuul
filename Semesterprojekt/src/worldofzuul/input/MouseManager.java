package worldofzuul.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import worldofzuul.ui.UIManager;

/**
 *
 * @author Rasmus Willer
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;
    
    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) // left mouse button
            leftPressed = true;
        else if (e.getButton() == MouseEvent.BUTTON3) // right mouse button
            rightPressed = true;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) // left mouse button
            leftPressed = false;
        else if (e.getButton() == MouseEvent.BUTTON3) // right mouse button
            rightPressed = false;
        
        if (uiManager != null)
            uiManager.onMouseRelease(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        if (uiManager != null)
            uiManager.onMouseMove(e);
    }
    
    
    // GETTERS
    
    public boolean isLeftPressed() {
        return leftPressed;
    }
    
    public boolean isRightPressed() {
        return rightPressed;
    }
    
    public int getMouseX() {
        return mouseX;
    }
    
    public int getMouseY() {
        return mouseY;
    }
    
    
    // OVERRIDEN CLASSES NOT USED
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }
    
    
    
    
}
