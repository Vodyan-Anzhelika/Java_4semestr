import model.Human;
import model.Sex;
import org.junit.jupiter.api.Test;
import service.LambdaDemo;

import static org.junit.jupiter.api.Assertions.*;


public class TestLambdaDemo {
    @Test
    void testStringLength() {
        assertAll(
                () -> assertNull(LambdaDemo.stringLength.apply(null)),
                () -> assertEquals(0, LambdaDemo.stringLength.apply("")),
                () -> assertEquals(9, LambdaDemo.stringLength.apply("something")),
                () -> assertEquals(12, LambdaDemo.stringLength.apply("абв ABC 123."))
        );
    }
    
    
    @Test
    void testFirstChar() {
        assertAll(
                () -> assertNull(LambdaDemo.firstChar.apply(null)),
                () -> assertNull(LambdaDemo.firstChar.apply("")),
                () -> assertEquals('1', LambdaDemo.firstChar.apply("1")),
                () -> assertEquals('S', LambdaDemo.firstChar.apply("Some words абв"))
        );
    }
    
    
    @Test
    void testNoSpaces() {
        assertAll(
                () -> assertTrue(LambdaDemo.noSpaces.test(null)),
                () -> assertTrue(LambdaDemo.noSpaces.test("")),
                () -> assertTrue(LambdaDemo.noSpaces.test("123f,d78fa;")),
                () -> assertFalse(LambdaDemo.noSpaces.test("Some words абв"))
        );
    }
    
    
    @Test
    void testCountCommaSeparatedWords() {
        assertAll(
                () -> assertNull(LambdaDemo.firstChar.apply(null)),
                () -> assertEquals(0, LambdaDemo.countCommaSeparatedWords.apply("")),
                () -> assertEquals(1, LambdaDemo.countCommaSeparatedWords.apply("1")),
                () -> assertEquals(3, LambdaDemo.countCommaSeparatedWords.apply("Some, wo&rds,аб.в")),
                () -> assertEquals(2, LambdaDemo.countCommaSeparatedWords.apply("1,,2")),
                () -> assertEquals(2, LambdaDemo.countCommaSeparatedWords.apply("1,,,,,2")),
                () -> assertEquals(2, LambdaDemo.countCommaSeparatedWords.apply(",1,,2")),
                () -> assertEquals(2, LambdaDemo.countCommaSeparatedWords.apply(",1,,2,")),
                () -> assertEquals(0, LambdaDemo.countCommaSeparatedWords.apply(",,,"))
        );
    }
    
    
    @Test
    void testAge() {
        assertAll(
                () -> assertNull(LambdaDemo.age.apply(null)),
                () -> assertEquals(18, LambdaDemo.age.apply(Humans.human1)),
                () -> assertEquals(75, LambdaDemo.age.apply(Humans.human4)),
                () -> assertEquals(22, LambdaDemo.age.apply(Humans.student2))
        );
    }
    
    
    @Test
    void testSameSecondName() {
        assertAll(
                () -> assertFalse(LambdaDemo.sameSecondName.test(null, null)),
                () -> assertFalse(LambdaDemo.sameSecondName.test(Humans.human1, null)),
                () -> assertFalse(LambdaDemo.sameSecondName.test(null, Humans.human2)),
                () -> assertTrue(LambdaDemo.sameSecondName.test(Humans.human1, Humans.human2)),
                () -> assertFalse(LambdaDemo.sameSecondName.test(Humans.human1, Humans.student1))
        );
    }
    
    
    @Test
    void testJoinNames() {
        assertAll(
                () -> assertNull(LambdaDemo.joinNames.apply(null)),
                () -> assertEquals("Петров Иван Иванович", LambdaDemo.joinNames.apply(Humans.human1)),
                () -> assertEquals("White Andrew", LambdaDemo.joinNames.apply(Humans.human3)),
                () -> assertEquals("Алексеева Елена Алексеевна", LambdaDemo.joinNames.apply(Humans.student1))
        );
    }
    
    
    @Test
    void testMakeOlder() {
        Human olderHuman1 = new Human("Иван", "Иванович", "Петров", 19, Sex.MALE);
        Human olderHuman4 = new Human("Ann", "Field", 76, Sex.FEMALE);
        
        assertAll(
                () -> assertNull(LambdaDemo.makeOlder.apply(null)),
                () -> assertEquals(olderHuman1, LambdaDemo.makeOlder.apply(Humans.human1)),
                () -> assertEquals(olderHuman4, LambdaDemo.makeOlder.apply(Humans.human4)),
                () -> assertEquals(18, Humans.human1.getAge()),
                () -> assertEquals(75, Humans.human4.getAge())
        );
    }
    
    
    @Test
    void testCheckYoungerThan() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> LambdaDemo.checkYoungerThan.check(100,
                        null, null, null)),
                () -> assertThrows(NullPointerException.class, () -> LambdaDemo.checkYoungerThan.check(100,
                        Humans.human1, null, null)),
                () -> assertThrows(NullPointerException.class, () -> LambdaDemo.checkYoungerThan.check(100,
                        null, Humans.human1, Humans.human3)),
                () -> assertTrue(LambdaDemo.checkYoungerThan.check(100,
                        Humans.human1, Humans.human2, Humans.human4)),
                () -> assertTrue(LambdaDemo.checkYoungerThan.check(26,
                        Humans.human1, Humans.human2, Humans.human3)),
                () -> assertFalse(LambdaDemo.checkYoungerThan.check(25,
                        Humans.human1, Humans.human2, Humans.human3)),
                () -> assertTrue(LambdaDemo.checkYoungerThan.check(23,
                        Humans.human1, Humans.student1, Humans.student2))
        );
    }
}
