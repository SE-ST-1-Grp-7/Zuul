package worldofzuul.states;

import java.awt.Graphics;
import worldofzuul.Link;

/**
 *
 * @author Rasmus Willer
 */
public abstract class State {
    // This could be placed in a separate class called StateManager
    // and doesn't have to be abstract (that's only for tick and render methods)
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState = state;
    }
    
    public static State getState() {
        return currentState;
    }
    
    //CLASS
    
    protected Link link;
    
    public State(Link link) {
        this.link = link;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
}
