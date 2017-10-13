/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import worldofzuul.items.Item;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jonas Bjørstorp & Frederik Bauer
 */
public class Inventory {

    private final int CAPACITY = 10;
    private ArrayList<Item> items = new ArrayList<>(CAPACITY);

/** 
 * adds an item to the inventory
 * @param item 
 */
    public void addItem(Item item) {
        if (items.size() < CAPACITY) {
            items.add(item);
        } else {
            System.out.println("Not enough space");
        }
    }
    public Item getItem(int index) {
        return items.get(index);  
    }
    public void removeItem(int index) {
        items.remove(index);     
    }

}
