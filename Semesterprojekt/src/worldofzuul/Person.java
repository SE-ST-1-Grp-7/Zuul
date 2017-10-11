package worldofzuul;


/** Person class - superclass of Student & Player
 *
 * @author Gruppe 7
 */
public abstract class Person {
    private String name;
    private int age;
    private String gender;
    private int speed;
    //private Inventory inventory;
    
    
    /**
     * getter for the name
     * @return 
     */
    public String getName() {
        return this.name;
    }
    /**
     * setter for the name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * getter for the age
     * @return 
     */
    public int getAge() {
        return this.age;
    }
    /**
     * setter for the age
     * @param age 
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * getter for the gender
     * @return 
     */
    public String getGender() {
        return this.gender;
    }
    /**
     * setter for the gender
     * @param gender 
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * getter for the speed
     * @return 
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * setter for the speed
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
    
         
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

        
