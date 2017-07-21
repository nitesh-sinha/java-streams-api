package com.nitesh.java8.streams;


import java.util.Arrays;
import java.util.List;

/**
 * A sequential stream operation pipeline
 */
public class streamPlay {

    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "a2", "a3", "a4", "b1", "b2", "c1" );

        myList.stream()
                .filter(x -> x.startsWith("a"))
                .map(String::toUpperCase)
                .sorted((o1, o2) -> o2.compareTo(o1))
                .forEach(System.out::println);
    }
}

// Output:
//    A4
//    A3
//    A2
//    A1