package iface;


import model.Human;


@FunctionalInterface
public interface AgeChecker {
    boolean check(int age, Human human1, Human human2, Human human3);
}
