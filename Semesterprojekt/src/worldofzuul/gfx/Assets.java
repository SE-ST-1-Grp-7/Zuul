package worldofzuul.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus Willer
 */
public class Assets {
    private static final int p_width = 40, p_height = 40;
    private static final int b_width = 256, b_height = 128;
    
    // Declare UI elements
    public static BufferedImage[] bt_start;
    
    // Declare arrays to contain animations for player
    public static BufferedImage[] player_down,
                                  player_up,
                                  player_right,
                                  player_left;
    
    public static void init() {
        SpriteSheet player_sheet = new SpriteSheet(
                ImageLoading.loadImage("/textures/player.png"));
        SpriteSheet ui_sheet = new SpriteSheet(
                ImageLoading.loadImage("/textures/ButtonUI.png"));
        
        bt_start = new BufferedImage[2];
        bt_start[0] = ui_sheet.crop(0, 0, b_width, b_height);
        bt_start[1] = ui_sheet.crop(0, b_height, b_width, b_height);
        
        // index is length of animation
        player_down     = new BufferedImage[4];
        player_up       = new BufferedImage[4];
        player_right    = new BufferedImage[4];
        player_left     = new BufferedImage[4];
        
        // Assign plaer crop for DOWN!
        player_down[0] = player_sheet.crop(0, 0, p_width, p_height);
        player_down[1] = player_sheet.crop(p_width * 1, 0, p_width, p_height);
        player_down[2] = player_sheet.crop(p_width * 2, 0, p_width, p_height);
        player_down[3] = player_sheet.crop(p_width * 3, 0, p_width, p_height);
        
        // Assign player crop for UP!
        player_up[0] = player_sheet.crop(0,
                                         p_height * 1,
                                         p_width,
                                         p_height);
        player_up[1] = player_sheet.crop(p_width * 1,
                                         p_height * 1,
                                         p_width,
                                         p_height);
        player_up[2] = player_sheet.crop(p_width * 2,
                                         p_height * 1,
                                         p_width,
                                         p_height);
        player_up[3] = player_sheet.crop(p_width * 3,
                                         p_height * 1,
                                         p_width,
                                         p_height);
        
        // Assign player crop for RIGHT!
        player_right[0] = player_sheet.crop(0,
                                            p_height * 2,
                                            p_width,
                                            p_height);
        player_right[1] = player_sheet.crop(p_width * 1,
                                            p_height * 2,
                                            p_width,
                                            p_height);
        player_right[2] = player_sheet.crop(p_width * 2,
                                            p_height * 2,
                                            p_width,
                                            p_height);
        player_right[3] = player_sheet.crop(p_width * 3,
                                            p_height * 2,
                                            p_width,
                                            p_height);
        
        // Assign player crop for LEFT!
        player_left[0] = player_sheet.crop(0,
                                           p_height * 3,
                                           p_width,
                                           p_height);
        player_left[1] = player_sheet.crop(p_width * 1,
                                           p_height * 3,
                                           p_width,
                                           p_height);
        player_left[2] = player_sheet.crop(p_width * 2,
                                           p_height * 3,
                                           p_width,
                                           p_height);
        player_left[3] = player_sheet.crop(p_width * 3,
                                           p_height * 3,
                                           p_width,
                                           p_height);
    }
}
