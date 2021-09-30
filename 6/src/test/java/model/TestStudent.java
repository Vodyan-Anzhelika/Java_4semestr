package model;


import org.junit.jupiter.api.Test;

import static error.IllegalArgumentMessage.NULL_DEPARTMENT;
import static org.junit.jupiter.api.Assertions.*;


public class TestStudent {
    @Test
    void testStudent() {
        Student student1 = new Student("Александр", "Алексеевич", "Иванов", 0, "ИМИТ");
        Student student2 = new Student("Catherina", "Black", 19, "Economics");
        
        assertAll(
                () -> assertEquals("Александр", student1.getFirstName()),
                () -> assertEquals("Алексеевич", student1.getPatronymicName()),
                () -> assertEquals("Иванов", student1.getSecondName()),
                () -> assertEquals(0, student1.getAge()),
                () -> assertEquals("ИМИТ", student1.getDepartment()),
                () -> assertEquals("Catherina", student2.getFirstName()),
                () -> assertNull(student2.getPatronymicName()),
                () -> assertEquals("Black", student2.getSecondName()),
                () -> assertEquals(19, student2.getAge()),
                () -> assertEquals("Economics", student2.getDepartment())
        );
    }
    
    
    @Test
    void testSetDepartmentException() {
        try {
            Student student1 = new Student("Анастасия", "Андреевна", "Петрова", 17, null);
            fail("student1");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DEPARTMENT, e.getMessage());
        }
        
        try {
            Student student2 = new Student("Rafael", "Strike", 32, "");
            fail("student2");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_DEPARTMENT, e.getMessage());
        }
    }
    
    
    @Test
    void testEquals() {
        Student student1 = new Student("Xenia", "Olsson", 21, "Architecture");
        Student student2 = new Student("Xenia", "Olsson", 21, "Architecture");
        Student student3 = new Student("Linn", "Olsson", 21, "Architecture");
        Student student4 = new Student("Xenia", "Jönsson", 21, "Architecture");
        Student student5 = new Student("Xenia", "Olsson", 41, "Architecture");
        Student student6 = new Student("Xenia", "Olsson", 21, "Mathematics");
        
        assertAll(
                () -> assertEquals(student1, student1),
                () -> assertEquals(student1, student2),
                () -> assertEquals(student2, student1),
                () -> assertNotEquals(student1, ""),
                () -> assertNotEquals(student1, student3),
                () -> assertNotEquals(student1, student4),
                () -> assertNotEquals(student1, student5),
                () -> assertNotEquals(student1, student6),
                () -> assertNotEquals(student4, student6)
        );
    }
}
