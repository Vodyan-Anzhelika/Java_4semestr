package service;


import iface.AgeChecker;
import model.Human;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;


public class LambdaRunner {
    public static <T, U> U runFunction(Function<T, U> function, T param) {
        return function.apply(param);
    }
    
    
    public static <T> boolean runPredicate(Predicate<T> predicate, T param) {
        return predicate.test(param);
    }
    
    
    public static <T> boolean runBiPredicate(BiPredicate<T, T> predicate, T param1, T param2) {
        return predicate.test(param1, param2);
    }
    
    
    public static boolean runAgeChecker(AgeChecker ageChecker, int age, Human human1, Human human2, Human human3) {
        return ageChecker.check(age, human1, human2, human3);
    }
}
