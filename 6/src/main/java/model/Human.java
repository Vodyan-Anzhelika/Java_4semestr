package model;


import error.IllegalArgumentMessage;

import java.util.Objects;


public class Human implements Cloneable {
    private String firstName;
    private String patronymicName;
    private String secondName;
    private int age;
    
    
    public Human(String firstName, String patronymicName, String secondName, int age) {
        setFirstName(firstName);
        setPatronymicName(patronymicName);
        setSecondName(secondName);
        setAge(age);
    }
    
    
    public Human(String firstName, String secondName, int age) {
        this(firstName, null, secondName, age);
    }
    
    
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_FIRST_NAME);
        
        this.firstName = firstName;
    }
    
    
    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }
    
    
    public void setSecondName(String secondName) {
        if (secondName == null || secondName.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_SECOND_NAME);
        
        this.secondName = secondName;
    }
    
    
    public void setAge(int age) {
        if (age < 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NEGATIVE_AGE);
        
        this.age = age;
    }
    
    
    public String getFirstName() {
        return firstName;
    }
    
    
    public String getPatronymicName() {
        return patronymicName;
    }
    
    
    public String getSecondName() {
        return secondName;
    }
    
    
    public int getAge() {
        return age;
    }
    
    
    @Override
    public Human clone() throws CloneNotSupportedException {
        return (Human) super.clone();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return age == human.age &&
                firstName.equals(human.firstName) &&
                Objects.equals(patronymicName, human.patronymicName) &&
                secondName.equals(human.secondName);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(firstName, patronymicName, secondName, age);
    }
}
