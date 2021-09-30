package service;


import model.Human;
import model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static service.CollectionsDemo.getListWithoutPerson;


public class TestCollectionsDemo {
    private static final Human person1 = new Human("Александр", "Алексеевич", "Иванов", 20);
    private static final Human person2 = new Human("Catherina", "Black", 20);
    private static final Human person3 = new Human("Полина", "Артёмовна", "Петрова", 23);
    private static final Human person4 = new Human("Евгений", "Алексеевич", "Иванов", 16);
    private static final Human person5 = new Human("Евгений", "Алексеевич", "Иванов", 15);
    private static final Human person6 = new Human("Полина", "Сергеевна", "Петрова", 15);
    private static final Student student1 = new Student("Анастасия", "Андреевна", "Петрова", 17, "Химический");
    private static final Student student2 = new Student("Linn", "Olsson", 23, "Architecture");
    private static Map<Integer, Human> peopleMap = new HashMap<>(8);
    private static Set<Human> peopleSet = new HashSet<>(8);
    
    
    @BeforeAll
    static void initializePeopleMap() {
        peopleMap.put(1, person1);
        peopleMap.put(-2, person2);
        peopleMap.put(0, person3);
        peopleMap.put(756, person4);
        peopleMap.put(142, person5);
        peopleMap.put(234, person6);
        peopleMap.put(-93, student1);
        peopleMap.put(235, student2);
    }
    
    
    @BeforeAll
    static void initializePeopleSet() {
        peopleSet.add(person1);
        peopleSet.add(person2);
        peopleSet.add(person3);
        peopleSet.add(person4);
        peopleSet.add(person5);
        peopleSet.add(person6);
        peopleSet.add(student1);
        peopleSet.add(student2);
    }
    
    
    @Test
    void testCountStringsStartingWithChar() {
        List<String> list = Arrays.asList("Абв", "Абв123", "1", "STU", "Амл", "124r32", "skj");
        
        assertAll(
                () -> assertEquals(3, CollectionsDemo.countStringsStartingWithChar(list, 'А')),
                () -> assertEquals(2, CollectionsDemo.countStringsStartingWithChar(list, '1')),
                () -> assertEquals(1, CollectionsDemo.countStringsStartingWithChar(list, 's'))
        );
    }
    
    
    @Test
    void testGetPeopleWithPersonSecondName() {
        List<Human> people = Arrays.asList(person1, person2, person4, student1);
        
        assertAll(
                () -> assertEquals(Arrays.asList(person1, person4),
                        CollectionsDemo.getPeopleWithPersonSecondName(people, person1)),
                () -> assertEquals(Collections.singletonList(student1),
                        CollectionsDemo.getPeopleWithPersonSecondName(people, person3))
        );
    }
    
    
    @Test
    void testGetListWithoutPerson() throws CloneNotSupportedException {
        List<Human> people = Arrays.asList(person1, person2, person3, person2, student1, student1);
        
        assertAll(
                () -> assertEquals(Arrays.asList(person2, person3, person2, student1, student1),
                        getListWithoutPerson(people, person1)),
                () -> assertEquals(Arrays.asList(person1, person3, student1, student1),
                        getListWithoutPerson(people, person2)),
                () -> assertEquals(Arrays.asList(person1, person2, person3, person2),
                        getListWithoutPerson(people, student1))
        );

        List<Human> res = getListWithoutPerson(people, person1);
        
        person2.setAge(1555);
        
        assertNotEquals(Arrays.asList(person2, person3, person2, student1, student1), res);
    }
    
    
    @Test
    void testGetNotIntersecting() {
        Set<Integer> set0 = new HashSet<>();
        Set<Integer> set1 = new HashSet<>(Collections.singletonList(2));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 3));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(-1, 2, 3));
        Set<Integer> set4 = new HashSet<>(Arrays.asList(-5, 4, 21, 0));
        
        List<Set<Integer>> list = new ArrayList<>(Arrays.asList(set0, set1, set2, set3, set4));
        
        assertAll(
                () -> assertEquals(list, CollectionsDemo.getNotIntersecting(list, set0)),
                () -> assertEquals(Arrays.asList(set0, set2, set4), CollectionsDemo.getNotIntersecting(list, set1)),
                () -> assertEquals(Arrays.asList(set0, set4), CollectionsDemo.getNotIntersecting(list, set3)),
                () -> assertEquals(Arrays.asList(set0, set1, set2, set3), CollectionsDemo.getNotIntersecting(list, set4))
        );
    }
    
    
    @Test
    void testGetMaxAged() {
        List<Human> list1 = Arrays.asList(person1, person2, person4);
        List<Human> list2 = Arrays.asList(person1, person2, person3, person4);
        List<Human> list3 = Arrays.asList(person1, person2, person3, person4, student1, student2);
        List<Student> list4 = Arrays.asList(student1, student2);
        
        assertAll(
                () -> assertEquals(new HashSet<>(Arrays.asList(person1, person2)), CollectionsDemo.getMaxAged(list1)),
                () -> assertEquals(Collections.singleton(person3), CollectionsDemo.getMaxAged(list2)),
                () -> assertEquals(new HashSet<>(Arrays.asList(person3, student2)), CollectionsDemo.getMaxAged(list3)),
                () -> assertEquals(Collections.singleton(student2), CollectionsDemo.getMaxAged(list4))
        );
    }
    
    

    
    
    @Test
    void testFilterPeopleByIds() {
        Set<Integer> ids0 = new HashSet<>();
        Set<Integer> ids1 = new HashSet<>(Arrays.asList(1, 0, 756, -93));
        Set<Integer> ids2 = new HashSet<>(Arrays.asList(1, 0, 756, -93, 88888888));

        Set<Human> expected = new HashSet<>(Arrays.asList(person1, person3, person4, student1));
        
        assertAll(
                () -> assertEquals(new HashSet<>(), CollectionsDemo.filterPeopleByIds(peopleMap, ids0)),
                () -> assertEquals(expected, CollectionsDemo.filterPeopleByIds(peopleMap, ids1)),
                () -> assertEquals(expected, CollectionsDemo.filterPeopleByIds(peopleMap, ids2))
        );
    }
    
    
    @Test
    void testGetAgedOver18Ids() {
        List<Integer> expected = Arrays.asList(1, -2, 0, 235);
        List<Integer> actual = CollectionsDemo.getAgedOver18Ids(peopleMap);
        
        Collections.sort(expected);
        Collections.sort(actual);
        
        assertEquals(expected, actual);
    }
    
    
    @Test
    void testGetAges() {
        Map<Integer, Integer> expected = new HashMap<>(8);
        expected.put(1, 20);
        expected.put(-2, 20);
        expected.put(0, 23);
        expected.put(756, 16);
        expected.put(142, 15);
        expected.put(234, 15);
        expected.put(-93, 17);
        expected.put(235, 23);
        
        assertEquals(expected, CollectionsDemo.getAges(peopleMap));
    }
    @Test
    void testGetPeopleByAges() {
        Map<Integer, List<Human>> expected = new HashMap<>(5);
        expected.put(20, Arrays.asList(person2, person1));
        expected.put(23, Arrays.asList(student2, person3));
        expected.put(16, Collections.singletonList(person4));
        expected.put(15, Arrays.asList(person5, person6));
        expected.put(17, Collections.singletonList(student1));


    }

}
