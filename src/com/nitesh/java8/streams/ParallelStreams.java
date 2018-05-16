package com.nitesh.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by nitesinh on 9/21/17.
 */
public class ParallelStreams {

    public static void main(String[] args) {
        Stream<String> intStream = IntStream.range(0, 200).mapToObj(v -> String.valueOf(v));
        testParallelStreams(intStream.parallel());
    }

    private static void testParallelStreams(Stream<String> objects) {
        //System.out.println("Allocating a new strList");
        List<String> strList = new ArrayList<>(10); // shared data structure


        objects.forEach(obj -> {
            strList.add(obj.toString());
            if(strList.size()==10) {
                //strList.forEach(s -> System.out.println(s));
                //System.out.println("max size reached. clearing the list");
                strList.clear();
            }}
        );
//        objects.sequential().forEach(obj -> {
//            strList.add(obj.toString());
//            if(strList.size()==10) {
//                strList.forEach(s -> System.out.println(s));
//                System.out.println("max size reached. clearing the list");
//                strList.clear();
//            }}
//        );
    }
}
