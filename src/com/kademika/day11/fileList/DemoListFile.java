package com.kademika.day11.fileList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by kurakinaleksandr on 07.09.14.
 */
public class DemoListFile {
    public static void main(String[] args) {
        SimpleListFile simpleListFile = new SimpleListFile();

        // add demo
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        simpleListFile.add("this is new element, added at " + dateFormat.format(date));
        System.out.println();

        // get demo
        int index = 2;
        System.out.println("The element with index " + (index) + "  in collection is: " + simpleListFile.get(index).toString());
        System.out.println();

        // remove demo
        simpleListFile.remove(5);

        // contains demo
        String testString1 = "two";
        if (simpleListFile.contains(testString1)) {
            System.out.println("Yes, we have: " + testString1);
        } else System.out.println("No, we don't have " + testString1);
        System.out.println();

        // size demo
        System.out.println("SimpleListFile has " + simpleListFile.size() + " elements");
        System.out.println();

        // Iterator demo 1
        Iterator iteratorLF = simpleListFile.iterator();
        while (iteratorLF.hasNext()) {
            String str = (String) iteratorLF.next();
            System.out.println("Call with Iterator 1 " + (str));
//            iteratorLF.remove();
        }
        System.out.println();

        //Iterator demo 2
        for(Object str : simpleListFile){
            System.out.println("Call with Iterator 2 " + (str));
        }
        System.out.println();

        // size demo
        System.out.println("SimpleListFile has " + simpleListFile.size() + " elements");
        System.out.println();
    }
}
