package com.kademika.day10.reflaction;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Map;


public class InitClass {



    public static void main(String[] args) {
    }
    public <T> T initClass(Class c, Map<String, Object> map) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T inst = null;
        Object [] mapValuesArr = map.values().toArray();
        String [] mapArgsArr = (String[]) map.keySet().toArray();
        Constructor[] classConstructors = c.getConstructors();
        for(Constructor cnstr: classConstructors){
            if (stringTypeArrayEquals(mapArgsArr, cnstr.getGenericParameterTypes())){
                inst = (T) cnstr.newInstance(mapValuesArr);
            } else throw new NoSuchConstructorException("There are no such constructor in this Class");

        }

        return inst;
    }
//
    private boolean stringTypeArrayEquals (String[] a1, Type[] a2){
        Boolean result = false;
        int cntr = 0;
        if(a1.length ==a2.length) {
            for (int i = 0; i < a1.length; i++) {
                if (a1[i].equals(a2[i].getClass().getName())) {
                    cntr++;
                }
            }
            if (a1.length == cntr) {
                result = true;
            }
        }
        return result;
    }
}
