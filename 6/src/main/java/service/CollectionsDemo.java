package service;


import model.Human;

import java.util.*;


public class CollectionsDemo {
    public static int countStringsStartingWithChar(List<String> strings, char c) {
        int counter = 0;
        
        for (String str: strings)
            if (str.length() > 0 && str.charAt(0) == c)
                counter++;
        
        return counter;
    }

    public static List<Human> getPeopleWithPersonSecondName(List<Human> people, Human person) {
        List<Human> peopleWithPersonSecondName = new ArrayList<>();
        String secondName = person.getSecondName();
        
        for (Human e: people)
            if (e.getSecondName().equals(secondName))
                peopleWithPersonSecondName.add(e);
        
        return peopleWithPersonSecondName;
    }

    public static List<Human> getListWithoutPerson(List<Human> people, Human person) throws CloneNotSupportedException {
        List<Human> listWithoutPerson = new ArrayList<>();
        
        for (Human p: people)
            if (!p.equals(person))
                listWithoutPerson.add(p.clone());
        
        return listWithoutPerson;
    }
    
    
    public static List<Set<Integer>> getNotIntersecting(List<Set<Integer>> sets, Set<Integer> set) {
        List<Set<Integer>> notIntersecting = new ArrayList<>();
        
        for (Set<Integer> s: sets) {
            Set<Integer> intersection = new HashSet<>(s);
            intersection.retainAll(set);
            
            if (intersection.size() == 0)
                notIntersecting.add(s);
        }
        
        return notIntersecting;
    }

    public static <T extends Human> Set<T> getMaxAged(List<T> people) {
        Set<T> maxAged = new HashSet<>();
        int maxAge = 0;
        
        for (T e: people) {
            int age = e.getAge();
            
            if (age == maxAge)
                maxAged.add(e);
            
            else if (age > maxAge) {
                maxAge = age;
                maxAged.clear();
                maxAged.add(e);
            }
        }
        
        return maxAged;
    }
    
    public static Set<Human> filterPeopleByIds(Map<Integer, Human> people, Set<Integer> ids) {
        Set<Human> filteredSet = new HashSet<>();
        
        for (Integer id: ids) {
            Human person = people.get(id);
            
            if (person != null)
                filteredSet.add(person);
        }
        
        return filteredSet;
    }
    
    
    public static List<Integer> getAgedOver18Ids(Map<Integer, Human> people) {
        List<Integer> agedOver18 = new ArrayList<>();
        
        for (Integer id: people.keySet())
            if (people.get(id).getAge() >= 18)
                agedOver18.add(id);
        
        return agedOver18;
    }

    public static Map<Integer, Integer> getAges(Map<Integer, Human> people) {
        Map<Integer, Integer> ages = new HashMap<>(people.size());
        
        for (Integer id: people.keySet())
            ages.put(id, people.get(id).getAge());
        
        return ages;
    }
    public static Map<Integer, List<Human>> getPeopleByAges(Set<Human> people) {
        Map<Integer, List<Human>> peopleByAges = new HashMap<>();

        for (Human person: people) {
            int age = person.getAge();

            if (peopleByAges.containsKey(age))
                peopleByAges.get(age).add(person);

            else {
                List<Human> list = new LinkedList<>();

                list.add(person);
                peopleByAges.put(age, list);
            }
        }

        return peopleByAges;
    }
}
