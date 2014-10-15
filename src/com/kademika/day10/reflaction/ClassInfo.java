package com.kademika.day10.reflaction;

import com.kademika.day10.fr2.Apple;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by kurakinaleksandr on 06.07.14.
 */
public class ClassInfo {

    public static void main(String[] args) {
//    Apple ap = new Apple();
//        System.out.println(Fruit.class.getSuperclass());
//    printClassInfo(ap);
        System.out.println();
//       printClassMethods(ap);
        printClassFields(Apple.class);
    }

    public static void printClassInfo(Object a){
        Class s = a.getClass();
        System.out.println("Class " + s.getName() + " extends:");
    for (int i = 0; s.getSuperclass() != null; i++){
        System.out.println(s.getSuperclass().getName());
        s = s.getSuperclass();
    }
    }

    public static void printClassMethods(Object a){
        Class s = a.getClass();

        do {
            if (s.getSuperclass() != null ){
                System.out.println("Class " + s.getName() + " extends:");
                System.out.println(s.getSuperclass().getName());
            } else System.out.println("Class " + s.getName() + " has no more parents");
            System.out.println("Class " + s.getName() + " has methods:");
            Method methods [] = s.getMethods();
            for (Method m: methods) System.out.println(m.getName());
            s = s.getSuperclass();
        } while (s != null);
    }

    public static void printClassFields(Class a){
//        Class s = a.getClass();

        do {
            if (a.getSuperclass() != null ){
                System.out.println("Class " + a.getName() + " extends:");
                System.out.println(a.getSuperclass().getName());
            } else System.out.println("Class " + a.getName() + " has no more parents");
            System.out.println("Class " + a.getName() + " has fields:");
            Field fields [] = a.getDeclaredFields();
            for (Field m: fields) System.out.println(m.getName());
            a = a.getSuperclass();
        } while (a != null);
    }

//    public <T> T initClass(Class<T> aClass, Map<String, Object> data) throws IllegalAccessException,
//            InstantiationException {
//        Class tmp = clazz.getClass();
//
//        return newObject;
//
//    }

}
