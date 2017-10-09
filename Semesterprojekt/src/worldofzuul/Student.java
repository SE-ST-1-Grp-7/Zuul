/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Robin
 */
public class Student extends Person {
    boolean hasQuestionToPlayer;
    
    
    /**
     * This constructor makes a student that has a question for the Player
     * the student also has a name, age, gender and speed
     */
    public Student(String name, int age, String gender, int speed){
        hasQuestionToPlayer = true;
        super.setPersonName(name);
        super.setPersonAge(age);
        super.setPersonGender(gender);
        super.setSpeed(speed);
    }
}
