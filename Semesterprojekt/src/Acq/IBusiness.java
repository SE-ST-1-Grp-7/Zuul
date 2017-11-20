/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acq;

import javafx.scene.image.Image;

/**
 *
 * @author J
 */
public interface IBusiness {
    void playerMove(String direction);
    void playerInteract(String direction);
    void playerDropItem(int index);
    Image entityGetImage(int row, int col);
    void print();
}
