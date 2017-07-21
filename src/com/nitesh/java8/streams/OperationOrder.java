package com.nitesh.java8.streams;

import java.util.stream.Stream;

/**
 * Notice that the stream operations
 * are applied on every element of the stream
 * vertically i.e. each element of the stream
 * is passed through all intermediate operations
 * until the terminal operation. The second
 * element follows the same order and so on
 */
public class OperationOrder {

    public static void main(String[] args) {

        Stream.of("d2", "a2", "b2", "c3", "x3")
                .sorted()
                .filter(x -> {
                    System.out.println("filter: " + x);
                    return false;
                })
                .forEach(y -> System.out.println("forEach: " + y));
        /**
         * Nothing gets printed since filter predicate returns FALSE
           and so the stream elements get filtered out
          */



        Stream.of("d2", "a2", "b2", "c3", "x3")
                .sorted()
                .filter(x -> {
                    System.out.println("filter: " + x);
                    return true;                // filter takes a predicate which should return a boolean value
                })
                .forEach(y -> System.out.println("forEach: " + y));
        /**
         * Output:

         filter: a2
         forEach: a2
         filter: b2
         forEach: b2
         filter: c3
         forEach: c3
         filter: d2
         forEach: d2
         filter: x3
         forEach: x3
         */


        System.out.println("******************");

        Stream.of("a1", "b1", "c2", "d2", "e3")
                .map(x -> {
                    System.out.println("filter: " + x);
                    return x.toUpperCase();
                })
                .anyMatch(y -> {
                    System.out.println("anyMatch: " + y);
                    return y.contains("B");
                });
        /**
         * Only the necessary elements are evaluated by anyMatch()
         * Processing stops as soon as anyMatch() returns true.
         * So, map() function is only evaluated twice in this case.
         * Note that anyMatch() is a terminal operation.

         Output:

         filter: a1
         anyMatch: A1
         filter: b1
         anyMatch: B1
         */

        System.out.println("******************");

        // sorted() is a special stream operation which is called
        // horizontally(i.e. on all stream elements before they can
        // be passed down to the next operation)
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sorting: %s, %s \n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(x -> {
                    System.out.println("filter: " + x);
                    return x.startsWith("a");
                })
                .map(y -> {
                    System.out.println("map: " + y);
                    return y.toUpperCase();
                })
                .forEach(z -> System.out.println("forEach: " + z));
        /**
         * Output:

         sorting: a2, d2
         sorting: b1, a2
         sorting: b1, d2
         sorting: b1, a2
         sorting: b3, b1
         sorting: b3, d2
         sorting: c, b3
         sorting: c, d2
         filter: a2
         map: a2
         forEach: A2
         filter: b1
         filter: b3
         filter: c
         filter: d2
         */

        System.out.println("******************");

        // sorted() is not called since the final collection size is one
        // Order of operations really affects performance for larger collections
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(x -> {
                    System.out.println("filter: " + x);
                    return x.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sorting: %s, %s \n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(y -> {
                    System.out.println("map: " + y);
                    return y.toUpperCase();
                })
                .forEach(z -> System.out.println("forEach: " + z));
        /**
         Output:

         filter: d2
         filter: a2
         filter: b1
         filter: b3
         filter: c
         map: a2
         forEach: A2
         */
    }
}
