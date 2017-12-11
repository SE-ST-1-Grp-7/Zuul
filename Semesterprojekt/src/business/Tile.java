package business;

/**
 * Tile class, contain all info for a single tile in a room.
 * 
 * @author Rasmus Willer
 */
class Tile {
    private boolean solid = false;
    private String image;
    
    /**
     * Constructor.
     * 
     * @param imgPath   String, file path of texture.
     */
    public Tile(String imgPath) {
        this.image = imgPath;
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

    public String getImage() {
        return image;
    }

    public void setImage(String imgPath) {
        this.image = imgPath;
    }
    
    
}
