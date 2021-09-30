import iface.Executable;
import model.Human;
import model.Student;
import org.junit.jupiter.api.Test;
import service.ReflectionDemo;
import util.ArrayGenerator;
import util.Increment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestReflectionDemo {
    static final Human human1 = new Human("John", "Smith", 21);
    static final Human human2 = new Human("Александра", "Сергеевна", "Алексеева", 18);
    static final Student student1 = new Student("Joey", "Black", 22, "IT");
    static final Student student2 = new Student("Claire", "Windstorm", 20, "Chemistry");
    static Executable executable = new Increment();
    static Increment increment = new Increment();
    static ArrayGenerator arrayGenerator = new ArrayGenerator(3);
    
    
    @Test
    void testCountHuman() {
        assertAll(
                () -> assertEquals(0, ReflectionDemo.countHuman(Collections.emptyList())),
                () -> assertEquals(0, ReflectionDemo.countHuman(Arrays.asList("", ""))),
                () -> assertEquals(0, ReflectionDemo.countHuman(null)),
                () -> assertEquals(1, ReflectionDemo.countHuman(Collections.singletonList(human1))),
                () -> assertEquals(2, ReflectionDemo.countHuman(Arrays.asList("word", human1, human2))),
                () -> assertEquals(3, ReflectionDemo.countHuman(Arrays.asList(human1, human1, student1))),
                () -> assertEquals(4, ReflectionDemo.countHuman(Arrays.asList(
                        student2, human1, "", new int[0], human2, student1, Collections.emptyList())))
        );
    }
    
    
    @Test
    void testGetPublicMethodNames() {
        List<String> methodNames1 = ReflectionDemo.getPublicMethodNames(human1);
        List<String> methodNames2 = ReflectionDemo.getPublicMethodNames(student1);
        List<String> methodNames3 = ReflectionDemo.getPublicMethodNames(student2);
        List<String> methodNames4 = ReflectionDemo.getPublicMethodNames(Collections.emptyList());
        
        Collections.sort(methodNames1);
        Collections.sort(methodNames2);
        Collections.sort(methodNames3);
        Collections.sort(methodNames4);
        
        assertAll(
                () -> assertEquals(Arrays.asList(
                        "clone", "equals", "getAge", "getClass", "getFirstName", "getPatronymicName", "getSecondName",
                        "hashCode", "notify", "notifyAll", "setAge", "setFirstName", "setPatronymicName", "setSecondName",
                        "toString", "wait"
                ), methodNames1),
                () -> assertEquals(Arrays.asList(
                        "clone", "equals", "getAge", "getClass", "getDepartment",
                        "getFirstName", "getPatronymicName", "getSecondName", "hashCode", "notify", "notifyAll",
                        "setAge", "setDepartment", "setFirstName", "setPatronymicName", "setSecondName",
                        "toString", "wait"
                ), methodNames2),
                () -> assertEquals(methodNames2, methodNames3),
                () -> assertEquals(Arrays.asList(
                        "add", "addAll", "clear", "contains", "containsAll", "equals", "forEach", "get", "getClass",
                        "hashCode", "indexOf", "isEmpty", "iterator", "lastIndexOf", "listIterator", "notify", "notifyAll",
                        "parallelStream", "remove", "removeAll", "removeIf", "replaceAll", "retainAll", "set", "size",
                        "sort", "spliterator", "stream", "subList", "toArray", "toString", "wait"
                ), methodNames4),
                () -> assertEquals(Collections.emptyList(), ReflectionDemo.getPublicMethodNames(null))
        );
    }
    
    
    @Test
    void testGetSuperNames() {
        assertAll(
                () -> assertEquals(Arrays.asList("java.util.AbstractList", "java.util.AbstractCollection", "java.lang.Object"),
                        ReflectionDemo.getSuperNames(new ArrayList<>())),
                () -> assertEquals(Arrays.asList("model.Human", "java.lang.Object"), ReflectionDemo.getSuperNames(student1)),
                () -> assertEquals(Collections.singletonList("java.lang.Object"), ReflectionDemo.getSuperNames(human1)),
                () -> assertEquals(Collections.emptyList(), ReflectionDemo.getSuperNames(new Object())),
                () -> assertEquals(Collections.emptyList(), ReflectionDemo.getSuperNames(null))
        );
    }
    
    
    @Test
    void testExecuteExecutables() {
        List<Object> list1 = Arrays.asList(executable, "word");
        List<Object> list2 = Arrays.asList("", human1, executable, 8, increment, arrayGenerator, "?");
        
        assertAll(
                () -> assertEquals(0, ReflectionDemo.executeExecutables(Collections.emptyList())),
                () -> assertEquals(0, ReflectionDemo.executeExecutables(Arrays.asList("", human2, student1))),
                () -> assertEquals(0, ReflectionDemo.executeExecutables(null)),
                () -> assertEquals(1, ReflectionDemo.executeExecutables(list1)),
                () -> assertEquals(1, ((Increment) executable).getValue()),
                () -> assertEquals(3, ReflectionDemo.executeExecutables(list2)),
                () -> assertEquals(2, ((Increment) executable).getValue()),
                () -> assertEquals(1, increment.getValue()),
                () -> assertArrayEquals(new int[3], arrayGenerator.getArray())
        );
    }
    
    
    @Test
    void testGetSetterGetterNames() {
        List<String> names1 = ReflectionDemo.getSetterGetterNames(arrayGenerator);
        List<String> names2 = ReflectionDemo.getSetterGetterNames(human1);
        List<String> names3 = ReflectionDemo.getSetterGetterNames(student1);
        
        Collections.sort(names1);
        Collections.sort(names2);
        Collections.sort(names3);
        
        assertAll(
                () -> assertEquals(Arrays.asList("getArray", "getClass", "getSize", "setSize"), names1),
                () -> assertEquals(Arrays.asList(
                        "getAge", "getClass", "getFirstName", "getPatronymicName", "getSecondName",
                        "setAge", "setFirstName", "setPatronymicName", "setSecondName"
                ), names2),
                () -> assertEquals(Arrays.asList(
                        "getAge", "getClass", "getDepartment", "getFirstName", "getPatronymicName", "getSecondName",
                        "setAge", "setDepartment", "setFirstName", "setPatronymicName", "setSecondName"
                ), names3),
                () -> assertEquals(Arrays.asList("getBytes", "getClass"), ReflectionDemo.getSetterGetterNames("")),
                () -> assertEquals(Collections.emptyList(), ReflectionDemo.getSetterGetterNames(null))
        );
    }
}
