package com.nitesh.java8.streams;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 */
public class PrimitiveStream {

    public static void main(String[] args) {

        // Example of IntStream as a primitiveStream
        String longName = IntStream.rangeClosed(1,10)
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining());

        System.out.println(longName);           // 12345678910


        // Use Arrays.stream() to create an IntStream
        Arrays.stream(new int[]{1,2,3,4,5})
                .map(x -> 2*x)
                .average()
                .ifPresent(System.out::println); // 6.0


        Stream.of("a1", "a2a", "a3aa")
                .map(x -> x.lastIndexOf("a"))
                .forEach(System.out::println);
        //      Output:
        //        0
        //        2
        //        3


        // Transforming object stream to primitive stream
        Stream.of("a1","a2","a3")
                .map(x -> x.substring(1))
                .mapToInt(Integer::parseInt)
                .average().ifPresent(System.out::println);  // 2.0 (average of 1,2,3)


        // Transform doubleStream to intStream and then to ObjectStream
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "x"+i)
                .forEach(System.out::print); // x1x2x3
    }
}

