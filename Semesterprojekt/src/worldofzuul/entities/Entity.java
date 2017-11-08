package worldofzuul.entities;

import java.awt.Graphics;
import worldofzuul.Link;

/**
 *
 * @author Group 7
 */
public abstract class Entity {
    protected Link link;
    protected float x, y;
    protected int width, height;
    
    public Entity(Link link, float x, float y, int width, int height) {
        this.link = link;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    // GETTERS & SETTERS
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
