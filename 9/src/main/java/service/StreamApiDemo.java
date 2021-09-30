package service;


import model.Human;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


public class StreamApiDemo extends LambdaDemo {
    public static final UnaryOperator<List<Object>> delNulls = list ->
            list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    
    
    public static final Function<Set<Integer>, Long> countPositive = set ->
            set.stream().filter(Objects::nonNull).filter(n -> n > 0).count();
    
    
    public static final UnaryOperator<List<Object>> lastThree = list ->
            list.stream().skip(list.size() - 3).collect(Collectors.toList());
    
    
    public static final Function<List<Integer>, Integer> firstEven = list ->
            list.stream().filter(Objects::nonNull).filter(n -> n % 2 == 0).findFirst().orElse(null);
    
    
    public static final Function<Integer[], List<Integer>> distinctSquares = array ->
            Arrays.stream(array).filter(Objects::nonNull).map(n -> n * n).distinct().collect(Collectors.toList());
    
    
    public static final UnaryOperator<List<String>> sortNonEmptyStrings = list ->
            list.stream().filter(s -> s != null && !s.isEmpty()).sorted().collect(Collectors.toList());
    
    
    public static final Function<Set<String>, List<String>> sortStringsReversed = set ->
            set.stream().filter(Objects::nonNull).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    
    
    public static final Function<Set<Integer>, Long> squareSum = set ->
            set.stream().filter(Objects::nonNull).mapToLong(n -> n * n).sum();
    
    
    public static final Function<Collection<? extends Human>, Integer> greatestAge = collection ->
            collection.stream().filter(Objects::nonNull).mapToInt(Human::getAge).max().orElse(-1);
    
    
    public static final Function<Collection<? extends Human>, List<? extends Human>> sortBySexAndAge = collection ->
            collection.stream().filter(Objects::nonNull).
                    sorted(Comparator.comparing(Human::getSex).thenComparing(Human::getAge)).collect(Collectors.toList());
}
