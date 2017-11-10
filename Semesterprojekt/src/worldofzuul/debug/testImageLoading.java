package worldofzuul.debug;

import java.awt.image.BufferedImage;
import worldofzuul.gfx.ImageLoading;

/**
 * Debugging image loading without thread or GUI.
 * @author Rasmus Willer
 */
public class testImageLoading {
    public static void main(String[] args) {
        BufferedImage testImage;
        testImage = ImageLoading.loadImage("/textures/player.png");
    }
}
