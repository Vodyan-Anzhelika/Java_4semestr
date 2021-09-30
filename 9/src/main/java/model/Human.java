package model;


import error.IllegalArgumentMessage;

import java.util.Objects;


public class Human {
    private String firstName;
    private String patronymicName;
    private String secondName;
    private int age;
    private Sex sex;
    
    
    public Human(String firstName, String patronymicName, String secondName, int age, Sex sex) {
        setFirstName(firstName);
        setPatronymicName(patronymicName);
        setSecondName(secondName);
        setAge(age);
        setSex(sex);
    }
    
    
    public Human(String firstName, String secondName, int age, Sex sex) {
        this(firstName, null, secondName, age, sex);
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
    
    
    public void setSex(Sex sex) {
        if (sex == null)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_SEX);
        
        this.sex = sex;
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
    
    
    public Sex getSex() {
        return sex;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age &&
                firstName.equals(human.firstName) &&
                Objects.equals(patronymicName, human.patronymicName) &&
                secondName.equals(human.secondName) &&
                sex == human.sex;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(firstName, patronymicName, secondName, age, sex);
    }
}
