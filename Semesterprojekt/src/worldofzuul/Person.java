package worldofzuul;

import java.util.Scanner;

/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public abstract class Person {
    private String personName;
    private int personAge;
    private String personGender;
    private int speed;

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return this.personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public String getPersonGender() {
        return this.personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
    
//    private Inventory inventory;
//    
//    /**
//     * One-arg constructor with personName String, personAge int and personGender String.
//     * 
//     * @param personName, personAge and personGender Passed string and int argument providing room description.
//     */
//    public Person(String personName, int personAge, String personGender, Inventory inventory) {
//        this.personName = personName;
//        this.personAge = personAge;
//        this.personGender = personGender;
//        this.inventory = inventory;
//    } 
    
}

        
