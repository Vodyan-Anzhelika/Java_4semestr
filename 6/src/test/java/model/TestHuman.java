package model;


import org.junit.jupiter.api.Test;

import static error.IllegalArgumentMessage.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestHuman {
    @Test
    void testHuman() {
        Human person1 = new Human("Александр", "Алексеевич", "Иванов", 0);
        Human person2 = new Human("Catherina", "Black", 19);
        
        assertAll(
                () -> assertEquals("Александр", person1.getFirstName()),
                () -> assertEquals("Алексеевич", person1.getPatronymicName()),
                () -> assertEquals("Иванов", person1.getSecondName()),
                () -> assertEquals(0, person1.getAge()),
                () -> assertEquals("Catherina", person2.getFirstName()),
                () -> assertNull(person2.getPatronymicName()),
                () -> assertEquals("Black", person2.getSecondName()),
                () -> assertEquals(19, person2.getAge())
        );
    }
    
    
    @Test
    void testSetException() {
        try {
            Human person1 = new Human(null, "Андреевна", "Петрова", 32);
            fail("person1");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_FIRST_NAME, e.getMessage());
        }
        
        try {
            Human person2 = new Human("", "Андреевна", "Петрова", 32);
            fail("person2");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_FIRST_NAME, e.getMessage());
        }
        
        assertDoesNotThrow(() -> new Human("Анна", null, "Петрова", 35));
        
        try {
            Human person3 = new Human("Manuel", null, 21);
            fail("person3");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_SECOND_NAME, e.getMessage());
        }
        
        try {
            Human person4 = new Human("Kate", "", 16);
            fail("person4");
        } catch (IllegalArgumentException e) {
            assertEquals(NULL_SECOND_NAME, e.getMessage());
        }
        
        try {
            Human person5 = new Human("Josh", "Northwest", -1);
            fail("person5");
        } catch (IllegalArgumentException e) {
            assertEquals(NEGATIVE_AGE, e.getMessage());
        }
    }
    
    
    @Test
    void testEquals() {
        Human person1 = new Human("Александр", "Алексеевич", "Иванов", 39);
        Human person2 = new Human("Александр", "Алексеевич", "Иванов", 39);
        Human person3 = new Human("Не Александр", "Алексеевич", "Иванов", 39);
        Human person4 = new Human("Александр", "Сергеевич", "Иванов", 39);
        Human person5 = new Human("Александр", "Алексеевич", "Петров", 39);
        Human person6 = new Human("Александр", "Алексеевич", "Иванов", 30);
        
        assertAll(
                () -> assertEquals(person1, person1),
                () -> assertEquals(person1, person2),
                () -> assertEquals(person2, person1),
                () -> assertNotEquals(person1, ""),
                () -> assertNotEquals(person1, person3),
                () -> assertNotEquals(person1, person4),
                () -> assertNotEquals(person1, person5),
                () -> assertNotEquals(person1, person6),
                () -> assertNotEquals(person3, person5)
        );
    }
}
