import model.Human;
import model.Student;
import org.junit.jupiter.api.Test;
import service.StreamApiDemo;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestStreamApiDemo {
    @Test
    void testDelNulls() {
        List<Object> list1 = Arrays.asList(Humans.human1, null, Humans.human2);
        List<Object> list2 = Arrays.asList(null, null);
        List<Object> list3 = Arrays.asList(null, Humans.human1, 1, Humans.student1, null, "");
        
        assertAll(
                () -> assertEquals(Arrays.asList(Humans.human1, Humans.human2), StreamApiDemo.delNulls.apply(list1)),
                () -> assertEquals(Collections.emptyList(), StreamApiDemo.delNulls.apply(list2)),
                () -> assertEquals(Arrays.asList(Humans.human1, 1, Humans.student1, ""), StreamApiDemo.delNulls.apply(list3))
        );
    }
    
    
    @Test
    void testCountPositive() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(-1, -9));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, null, -2, 3));
        
        assertAll(
                () -> assertEquals(3, StreamApiDemo.countPositive.apply(set1)),
                () -> assertEquals(0, StreamApiDemo.countPositive.apply(set2)),
                () -> assertEquals(2, StreamApiDemo.countPositive.apply(set3))
        );
    }
    
    
    @Test
    void testLastThree() {
        List<Object> list1 = Arrays.asList(null, Humans.human1, 1, Humans.student1, null, "");
        List<Object> list2 = Arrays.asList(Humans.human1, null, Humans.human2);
        List<Object> list3 = Arrays.asList(null, "something");
        
        assertAll(
                () -> assertEquals(Arrays.asList(Humans.student1, null, ""), StreamApiDemo.lastThree.apply(list1)),
                () -> assertEquals(list2, StreamApiDemo.lastThree.apply(list2)),
                () -> assertThrows(IllegalArgumentException.class, () -> StreamApiDemo.lastThree.apply(list3))
        );
    }
    
    
    @Test
    void testFirstEven() {
        List<Integer> list1 = Arrays.asList(-3, -2, -1, 0, 1, 2, 3);
        List<Integer> list2 = Arrays.asList(-1, -9, 1);
        List<Integer> list3 = Arrays.asList(-3, null, 1, null, 8, 10, 3);
        
        assertAll(
                () -> assertEquals(-2, StreamApiDemo.firstEven.apply(list1)),
                () -> assertNull(StreamApiDemo.firstEven.apply(list2)),
                () -> assertEquals(8, StreamApiDemo.firstEven.apply(list3))
        );
    }
    
    
    @Test
    void testDistinctSquares() {
        Integer[] array1 = new Integer[]{-3, -2, -1, 0, 1, 1, 3};
        Integer[] array2 = new Integer[]{-1, -9, 1};
        Integer[] array3 = new Integer[]{-3, null, 1, null, 8, 10, 3};
        
        assertAll(
                () -> assertEquals(Arrays.asList(9, 4, 1, 0), StreamApiDemo.distinctSquares.apply(array1)),
                () -> assertEquals(Arrays.asList(1, 81), StreamApiDemo.distinctSquares.apply(array2)),
                () -> assertEquals(Arrays.asList(9, 1, 64, 100), StreamApiDemo.distinctSquares.apply(array3))
        );
    }
    
    
    @Test
    void testSortNonEmptyStrings() {
        List<String> list1 = Arrays.asList("bbz", "aa", "a", "ba", "ab", "bb", "bbb");
        List<String> list2 = Arrays.asList("", "", "a", "a", "", "bb", "ba");
        List<String> list3 = Arrays.asList("", null, "b", "ab", null);
        
        assertAll(
                () -> assertEquals(Arrays.asList("a", "aa", "ab", "ba", "bb", "bbb", "bbz"),
                        StreamApiDemo.sortNonEmptyStrings.apply(list1)),
                () -> assertEquals(Arrays.asList("a", "a", "ba", "bb"),
                        StreamApiDemo.sortNonEmptyStrings.apply(list2)),
                () -> assertEquals(Arrays.asList("ab", "b"),
                        StreamApiDemo.sortNonEmptyStrings.apply(list3))
        );
    }
    
    
    @Test
    void testSortStringsReversed() {
        Set<String> set1 = new HashSet<>(Arrays.asList("bbz", "aa", "a", "ba", "ab", "bb", "bbb"));
        Set<String> set2 = new HashSet<>(Arrays.asList("a", "", "bb", "ba"));
        Set<String> set3 = new HashSet<>(Arrays.asList("", "b", "ab", null));
        
        assertAll(
                () -> assertEquals(Arrays.asList("bbz", "bbb", "bb", "ba", "ab", "aa", "a"),
                        StreamApiDemo.sortStringsReversed.apply(set1)),
                () -> assertEquals(Arrays.asList("bb", "ba", "a", ""),
                        StreamApiDemo.sortStringsReversed.apply(set2)),
                () -> assertEquals(Arrays.asList("b", "ab", ""),
                        StreamApiDemo.sortStringsReversed.apply(set3))
        );
    }
    
    
    @Test
    void testSquareSum() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(-3, -2, -1, 0, 1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(-1, -9));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, null, -2, 5));
    
        assertAll(
                () -> assertEquals(28, StreamApiDemo.squareSum.apply(set1)),
                () -> assertEquals(82, StreamApiDemo.squareSum.apply(set2)),
                () -> assertEquals(30, StreamApiDemo.squareSum.apply(set3))
        );
    }
    
    
    @Test
    void testGreatestAge() {
        List<Human> people1 = Arrays.asList(Humans.human1, Humans.human3);
        List<Human> people2 = Arrays.asList(Humans.human1, Humans.student1);
        Set<Student> people3 = new HashSet<>(Arrays.asList(Humans.student1, Humans.student2));
        Set<Human> people4 = new HashSet<>(Arrays.asList(Humans.human1, Humans.human2, Humans.student2));
        
        assertAll(
                () -> assertEquals(25, StreamApiDemo.greatestAge.apply(people1)),
                () -> assertEquals(18, StreamApiDemo.greatestAge.apply(people2)),
                () -> assertEquals(22, StreamApiDemo.greatestAge.apply(people3)),
                () -> assertEquals(22, StreamApiDemo.greatestAge.apply(people4)),
                () -> assertEquals(-1, StreamApiDemo.greatestAge.apply(Collections.emptyList()))
        );
    }
    
    
    @Test
    void testSortBySexAndAge() {
        List<Human> people1 = Arrays.asList(Humans.human2, Humans.human3, Humans.human1);
        List<Student> people2 = Arrays.asList(Humans.student1, Humans.student2, Humans.student1);
        Set<Human> people3 = new HashSet<>(Arrays.asList(Humans.human1, Humans.human2, Humans.human3, Humans.human4,
                Humans.student1, null, Humans.student2));
        
        assertAll(
                () -> assertEquals(Arrays.asList(Humans.human1, Humans.human2, Humans.human3),
                        StreamApiDemo.sortBySexAndAge.apply(people1)),
                () -> assertEquals(Arrays.asList(Humans.student1, Humans.student1, Humans.student2),
                        StreamApiDemo.sortBySexAndAge.apply(people2)),
                () -> assertEquals(Arrays.asList(Humans.human1, Humans.human2, Humans.human3, Humans.student1,
                        Humans.student2, Humans.human4), StreamApiDemo.sortBySexAndAge.apply(people3)),
                () -> assertEquals(Collections.emptyList(),
                        StreamApiDemo.sortBySexAndAge.apply(Collections.emptyList()))
        );
    }
}
