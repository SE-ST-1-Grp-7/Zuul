package worldofzuul.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus Willer
 */
public class Assets {
    private static final int P_WIDTH = 40, P_HEIGHT = 40;
    
    
    public static BufferedImage player;
    
    public static void init() {
        SpriteSheet player_sheet = new SpriteSheet(
                ImageLoading.loadImage("/textures/player.png"));
        
        player = player_sheet.crop(0, 0, P_WIDTH, P_HEIGHT);
    }

    public static BufferedImage getPlayer() {
        return player;
    }

    public static void setPlayer(BufferedImage player) {
        Assets.player = player;
    }
}
