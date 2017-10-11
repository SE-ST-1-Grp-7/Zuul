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
        super.setName(name);
        super.setAge(age);
        super.setGender(gender);
        super.setSpeed(speed);
    }
     /**
     * Randomly create a name for the student
     *
     */
        String[] names = {"Niclas", "Rasmus", "Søren", "Robin","Jonas","Magnus","Frederik"};
        int index = (int) (Math.random() * names.length);
        String name = names[index];
        
       
}
