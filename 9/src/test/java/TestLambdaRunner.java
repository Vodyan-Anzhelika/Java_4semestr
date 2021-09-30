import model.Human;
import model.Sex;
import org.junit.jupiter.api.Test;
import service.LambdaDemo;
import service.LambdaRunner;

import static org.junit.jupiter.api.Assertions.*;


public class TestLambdaRunner {
    @Test
    void testRunFunction() {
        Human olderHuman1 = new Human("Иван", "Иванович", "Петров", 19, Sex.MALE);
        
        assertAll(
                () -> assertNull(LambdaRunner.runFunction(LambdaDemo.stringLength, null)),
                () -> assertEquals(9, LambdaRunner.runFunction(LambdaDemo.stringLength, "something")),
                () -> assertEquals('S', LambdaRunner.runFunction(LambdaDemo.firstChar, "Some words абв")),
                () -> assertEquals(3, LambdaRunner.runFunction(LambdaDemo.countCommaSeparatedWords, "one, two, три")),
                () -> assertEquals(18, LambdaRunner.runFunction(LambdaDemo.age, Humans.student1)),
                () -> assertEquals("Петров Иван Иванович", LambdaRunner.runFunction(LambdaDemo.joinNames, Humans.human1)),
                () -> assertEquals(olderHuman1, LambdaRunner.runFunction(LambdaDemo.makeOlder, Humans.human1))
        );
    }
    
    
    @Test
    void testRunPredicate() {
        assertAll(
                () -> assertTrue(LambdaRunner.runPredicate(LambdaDemo.noSpaces, null)),
                () -> assertTrue(LambdaRunner.runPredicate(LambdaDemo.noSpaces, "")),
                () -> assertFalse(LambdaRunner.runPredicate(LambdaDemo.noSpaces, "Hello world"))
        );
    }
    
    
    @Test
    void testRunBiPredicate() {
        assertAll(
                () -> assertFalse(LambdaRunner.runBiPredicate(LambdaDemo.sameSecondName, null, Humans.human2)),
                () -> assertTrue(LambdaRunner.runBiPredicate(LambdaDemo.sameSecondName, Humans.human1, Humans.human2)),
                () -> assertFalse(LambdaRunner.runBiPredicate(LambdaDemo.sameSecondName, Humans.human1, Humans.student1))
        );
    }
    
    
    @Test
    void testRunAgeChecker() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> LambdaRunner.runAgeChecker(
                        LambdaDemo.checkYoungerThan, 100, null, Humans.human1, Humans.human3)),
                () -> assertTrue(LambdaRunner.runAgeChecker(LambdaDemo.checkYoungerThan,
                        100, Humans.human1, Humans.human2, Humans.human4)),
                () -> assertFalse(LambdaRunner.runAgeChecker(LambdaDemo.checkYoungerThan,
                        25, Humans.human1, Humans.human2, Humans.human3))
        );
    }
}
