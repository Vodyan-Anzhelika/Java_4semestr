package service;


import iface.AgeChecker;
import model.Human;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


public class LambdaDemo {
    public static final Function<String, Integer> stringLength = s -> s == null? null : s.length();
    
    
    public static final Function<String, Character> firstChar = s -> (s == null || s.isEmpty())? null : s.charAt(0);
    
    
    public static final Predicate<String> noSpaces = s -> s == null || !s.contains(" ");

    public static final Function<String, Long> countCommaSeparatedWords = s ->
            s == null? null : Arrays.stream(s.split(",")).filter(word -> !word.isEmpty()).count();
    
    
    public static final Function<Human, Integer> age = human -> human == null? null : human.getAge();
    
    
    public static final BiPredicate<Human, Human> sameSecondName = (human1, human2) -> human1 != null && human2 != null &&
            human1.getSecondName().equals(human2.getSecondName());
    
    
    public static final Function<Human, String> joinNames = human -> human == null? null :
            String.format("%s %s%s", human.getSecondName(), human.getFirstName(),
                    human.getPatronymicName() == null? "" : " " + human.getPatronymicName());
    
    
    public static final UnaryOperator<Human> makeOlder = human -> human == null? null :
            new Human(
                    human.getFirstName(),
                    human.getPatronymicName(),
                    human.getSecondName(),
                    human.getAge() + 1,
                    human.getSex()
            );
    
    
    public static final AgeChecker checkYoungerThan = (maxAge, human1, human2, human3) ->
            human1.getAge() < maxAge && human2.getAge() < maxAge && human3.getAge() < maxAge;
}
