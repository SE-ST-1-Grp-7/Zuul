package worldofzuul.mapAndRooms;

/**
 *
 * @author Niclas Johansen & Rasmus WIller & Søren Bendtsen
 */
public class Map {
    MapCoord mapcords = new MapCoord();
    private int item;
    private int exit;
    private String description;
    private boolean locked;
    
    public Map(){
        
    }

    public int item() {
        return this.item;
    }

    public int exit() {
        return this.exit;
    }

    public String description() {
        return this.description;
    }

    public boolean locked() {
        return this.locked;

    }
}
