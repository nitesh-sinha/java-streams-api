package com.nitesh.java8.streams;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * We don't need a collection to work with
 * streams. Instead we can start off with a
 * stream of objects.
 */
public class StreamOrCollection {


    public static void main(String[] args) {

        // Start with Collection and convert to stream
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("*********");

        // Start with stream instead and work on it
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

    }
}

//  Output:
//  a1
//  *********
//  a1

