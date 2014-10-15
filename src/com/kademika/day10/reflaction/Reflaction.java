package com.kademika.day10.reflaction;

/**
 * Created by kurakinaleksandr on 06.07.14.
 */
public class Reflaction {
    public static void main(String[] args) {
        Class classes[] = Number.class.getClasses();
        System.out.println(classes.length);
//        for (int i = 0; i<= classes.length; i++){
//            System.out.println(classes[i].getSimpleName());
//        }
    }
}
