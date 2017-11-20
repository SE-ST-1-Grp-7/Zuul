package business;

import javafx.scene.image.Image;

/**
 *
 * @author Rasmus Willer
 */
class Tile {
    private boolean solid = false;
    private Image image;
    
    public Tile(String imgPath) {
        this.image = new Image(imgPath);
    }
    
    public Tile(String imgPath, boolean solid) {
        this(imgPath);
        this.solid = solid;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String imgPath) {
        this.image = new Image(imgPath);
    }
    
    
}
