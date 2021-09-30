package model;


import error.IllegalArgumentMessage;

import java.util.Objects;


public class Student extends Human {
    private String department;
    
    
    public Student(String firstName, String patronymicName, String secondName, int age, String department) {
        super(firstName, patronymicName, secondName, age);
        setDepartment(department);
    }
    
    
    public Student(String firstName, String secondName, int age, String department) {
        super(firstName, secondName, age);
        setDepartment(department);
    }
    
    
    public void setDepartment(String department) {
        if (department == null || department.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_DEPARTMENT);
        
        this.department = department;
    }
    
    
    public String getDepartment() {
        return department;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return department.equals(student.department);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
