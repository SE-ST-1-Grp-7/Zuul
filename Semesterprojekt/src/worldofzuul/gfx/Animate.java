package worldofzuul.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Rasmus Willer
 */
public class Animate {
    public int speed, index;
    private long timeThen, timer;
    public BufferedImage[] frames;
    
    public Animate(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        // Time difference starts at zero.
        timer = 0;
        // Assign initial time value to timeThen.
        timeThen = System.currentTimeMillis();
    }
    
    public void tick() {
        // add time that has passed since last tick method call
        timer += System.currentTimeMillis() - timeThen;
        // Update the lastTime variable to this current time spent
        timeThen = System.currentTimeMillis();
        
        if (timer > speed) {
            index++;
            timer = 0;
            // avoid index going out of bounds, reset to 0
            if (index >= frames.length)
                index = 0;
        }
    }
    
    // GETTERS & SETTERS
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
