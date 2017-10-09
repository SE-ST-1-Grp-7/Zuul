package worldofzuul;

import java.util.Scanner;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public class Person {
    private String personName;
    private int personAge;
    private String personGender;
    private Inventory inventory;
    
    /**
     * One-arg constructor with personName String, personAge int and personGender String.
     * 
     * @param personName, personAge and personGender Passed string and int argument providing room description.
     */
    public Person(String personName, int personAge, String personGender, Inventory inventory) {
        this.personName = personName;
        this.personAge = personAge;
        this.personGender = personGender;
        this.inventory = inventory;
    } 
    
}

        
