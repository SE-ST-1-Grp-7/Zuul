package worldofzuul.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus Willer
 */
public class testImageLoading {
    public static void main(String[] args) {
        BufferedImage testImage;
        testImage = ImageLoading.loadImage("/textures/player.png");
    }
}
