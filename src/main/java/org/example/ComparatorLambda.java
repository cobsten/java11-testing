package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambda {

    public static void main(String... args) {

        List<String> list = Arrays.asList("java", "angular","python","perl");
//1. Create anonymous class object for Comparator Interface.
        Comparator<String> comparator1 = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        };
//2. Create lambda expression for Comparator Interface.
        Comparator<String> comparator2 = (o1, o2) -> {return o1.compareTo(o2);};

//3. Sort the list using anonymous class object and print the sorted values
        list.sort(comparator1);
        list.forEach(System.out::println);
        
//4. Sort the list using  lambda expression and print the sorted values
//Tip:  Use Collections.sort method.
        Collections.sort(list, (o1,o2)-> o2.compareTo(o1));

// Use below to print the sorted values
        for(String s : list) {
            System.out.println(s);
        }
    }
}
