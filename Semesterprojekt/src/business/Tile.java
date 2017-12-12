package business;

/**
 * Tile class, contain all info for a single tile in a room.
 * 
 * @author Rasmus Willer
 */
class Tile {
    /* Attribute if true, means persons in the game will collide with tile.
       otherwise be able to walk over tile. */
    private boolean solid = false;
    private String image;
    
    /**
     * Constructor without solid status. Upon instantiation to an object,
     * assign texture file path to local attribute.
     * 
     * @param imgPath   String, file path of texture.
     */
    public Tile(String imgPath) {
        this.image = imgPath;
    }
    
    /**
     * Constructor with solid status. Upon instantiation to an object, assign
     * assign texture file path and solid status to local attributes.
     * 
     * @param imgPath
     * @param solid 
     */
    public Tile(String imgPath, boolean solid) {
        this(imgPath);
        this.solid = solid;
    }

    /**
     * Retrieve status on if tile is solid.
     * 
     * @return      boolean, if true, impassable for person, otherwise false
     *              and person can walk over tile.
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * Retrieve file path of tile texture.
     * 
     * @return      String, file path of used texture for tile.
     */
    public String getImage() {
        return image;
    }
}