package worldofzuul.states;

import worldofzuul.gfx.Assets;
import java.awt.Graphics;
import worldofzuul.Link;
import worldofzuul.ui.ClickListener;
import worldofzuul.ui.UIImageButton;
import worldofzuul.ui.UIManager;

/**
 *
 * @author Rasmus Willer
 */
public class MainMenuState extends State {
    private UIManager uiManager;
    
    public MainMenuState(Link link) {
        super(link);
        uiManager = new UIManager(link);
        link.getMouseManager().setUIManager(uiManager);
        
        uiManager.addObject(new UIImageButton(200,
                                              200,
                                              256,
                                              128,
                                              Assets.bt_start,
                                              new ClickListener(){
            @Override
            public void onClick() {
                link.getMouseManager().setUIManager(null);
                State.setState(link.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
    
}
