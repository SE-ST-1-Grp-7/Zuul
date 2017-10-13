/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.People;

/**
 *
 * @author Robin & Niclas
 */
public class Student extends Person {
    private boolean hasQuestionToPlayer; //this variable is true if the student has a question for the player
    
    /**
     * Constructor that gives a random name
     * @param gender
     * @param speed 
     */
    public Student(String gender, int speed){
        hasQuestionToPlayer = true;
        super.setName(getRandomName());
        super.setGender(gender);
        super.setSpeed(speed);

    }
    
    
    /**
     * This constructor makes a student that has a question for the Player
     * the student also has a name, age, gender and speed
     * @param name
     * @param gender
     * @param speed
     */
    public Student(String name, String gender, int speed){
        hasQuestionToPlayer = true;
        super.setName(name);
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
    
    /**
     * getter for hasQusetionForPlayer
     * @return 
     */
    public boolean getHasQuestionToPlayer() {
        return this.hasQuestionToPlayer;
    }
    /**
     * setter for hasQusetionForPlayer
     * @param hasQuestionToPlayer 
     */
    public void setHasQuestionToPlayer(boolean hasQuestionToPlayer) {
        this.hasQuestionToPlayer = hasQuestionToPlayer;
    }
    
    
    
       
}
