
package worldofzuul.items;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class Coffee extends Item {
    
    private String itemName;
    private String itemDescription;
    private int weight;
    
    public Coffee() {
        itemName = "Coffee";
        itemDescription = "A cup of coffee";
    }
    public String getName() {
        return this.itemName;
    }
    public String getDescription() {
        return this.itemDescription;
    }
    public int getWeight() {
        return this.weight;
    }
}
