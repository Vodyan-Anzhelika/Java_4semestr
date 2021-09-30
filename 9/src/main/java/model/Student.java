package model;


import error.IllegalArgumentMessage;

import java.util.Objects;


public class Student extends Human {
    private String university;
    private String department;
    private String speciality;
    
    
    public Student(String firstName, String patronymicName, String secondName, int age, Sex sex,
                   String university, String department, String speciality) {
        super(firstName, patronymicName, secondName, age, sex);
        setUniversity(university);
        setDepartment(department);
        setSpeciality(speciality);
    }
    
    
    public Student(String firstName, String secondName, int age, Sex sex,
                   String university, String department, String speciality) {
        super(firstName, secondName, age, sex);
        setUniversity(university);
        setDepartment(department);
        setSpeciality(speciality);
    }
    
    
    public void setUniversity(String university) {
        if (university == null || university.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_UNIVERSITY);
        
        this.university = university;
    }
    
    
    public void setDepartment(String department) {
        if (department == null || department.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_DEPARTMENT);
        
        this.department = department;
    }
    
    
    public void setSpeciality(String speciality) {
        if (speciality == null || speciality.length() == 0)
            throw new IllegalArgumentException(IllegalArgumentMessage.NULL_SPECIALITY);
        
        this.speciality = speciality;
    }
    
    
    public String getUniversity() {
        return university;
    }
    
    
    public String getDepartment() {
        return department;
    }
    
    
    public String getSpeciality() {
        return speciality;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return university.equals(student.university) &&
                department.equals(student.department) &&
                speciality.equals(student.speciality);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), university, department, speciality);
    }
}
