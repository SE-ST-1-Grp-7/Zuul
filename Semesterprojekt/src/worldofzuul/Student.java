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
    boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    
    /**
     * Constructor that gives a random name
     * @param age
     * @param gender
     * @param speed 
     */
    public Student(int age, String gender, int speed){
        hasQuestionToPlayer = true;
        super.setName(getRandomName());
        super.setAge(age);
        super.setGender(gender);
        super.setSpeed(speed);

    }
    
    
    /**
     * This constructor makes a student that has a question for the Player
     * the student also has a name, age, gender and speed
     * @param name
     * @param age
     * @param gender
     * @param speed
     */
    public Student(String name, int age, String gender, int speed){
        hasQuestionToPlayer = true;
        super.setName(name);
        super.setAge(age);
        super.setGender(gender);
        super.setSpeed(speed);
    }
     /**
     * Randomly create a (predefined)name for the student
     *
     * @return 
     */
    public String getRandomName(){
        String[] names = {"Niclas", "Rasmus", "Søren", "Robin","Jonas","Magnus","Frederik"};
        int index = (int) (Math.random() * names.length);
        return names[index];
    }
       
}
