package worldofzuul;

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
}
