package service;


import iface.Executable;
import model.Human;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ReflectionDemo {
    public static int countHuman(List<Object> objects) {
        int counter = 0;
        if (objects != null)
            for (Object o: objects)
                if (o instanceof Human)
                    counter++;
        
        return counter;
    }

    public static List<String> getPublicMethodNames(Object object) {
        Set<String> methodNames = new HashSet<>();
        if (object != null)
            for (Method m: object.getClass().getMethods())
                methodNames.add(m.getName());
        
        return new ArrayList<>(methodNames);
    }

    public static List<String> getSuperNames(Object object) {
        List<String> superNames = new ArrayList<>();
        if (object != null) {
            Class<?> c = object.getClass();
            while (c != Object.class) {
                c = c.getSuperclass();
                superNames.add(c.getCanonicalName());
            }
        }
        
        return superNames;
    }

    public static int executeExecutables(List<?> objects) {
        int counter = 0;
        if (objects != null)
            for (Object o: objects)
                if (o instanceof Executable) {
                    ((Executable) o).execute();
                    counter++;
                }
        return counter;
    }

    public static List<String> getSetterGetterNames(Object object) {
        List<String> names = new ArrayList<>();
        
        if (object != null) {
            Method[] methods = object.getClass().getMethods();
            
            for (Method m: methods) {
                int modifiers = m.getModifiers();
                String name = m.getName();
                
                if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) &&
                        ((m.getParameterCount() == 0 && m.getReturnType() != void.class && name.startsWith("get")) ||
                                (m.getParameterCount() == 1 && m.getReturnType() == void.class && name.startsWith("set"))))
                    names.add(name);
            }
            
        }
        
        return names;
    }
}
