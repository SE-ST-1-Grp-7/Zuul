/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author niclasjohansen
 */
public class test {
    public static void main (String[] args){
        Highscore h = new Highscore();
        h.loadXML();
        h.printHighscore();
    }
}
