package com.nitesh.java8.streams;

import com.nitesh.java8.streams.models.Bar;
import com.nitesh.java8.streams.models.Foo;
import com.nitesh.java8.streams.models.Outer;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Use flatMap as opposed to Map for hierarchical objects
 * flatMap transforms one object into zero or more objects
 */
public class FlatMapStream {

    public static void main(String[] args) {
        List<Foo> foos = new ArrayList<>();

        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("foo" + i)));
        foos.forEach(f ->
            IntStream.range(1,4).forEach(i -> f.bars.add(new Bar("bar"+i+"<-"+f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.getName()));
        /**
         * Output:

         bar1<-foo1
         bar2<-foo1
         bar3<-foo1
         bar1<-foo2
         bar2<-foo2
         bar3<-foo2
         bar1<-foo3
         bar2<-foo3
         bar3<-foo3
         */



        /**
         * Use of Optional class with flatMap()
         * to avoid null pointer exceptions
         */
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.name))
                .ifPresent(System.out::println);

    }
}
