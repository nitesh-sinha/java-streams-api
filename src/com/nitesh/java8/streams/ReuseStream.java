package com.nitesh.java8.streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Stream is closed as soon as a terminal operation
 * is performed on the stream. To reuse streams, use
 * a Supplier
 */
public class ReuseStream {

    public static void main(String[] args) {

        /**
         * Stream closed upon terminal operation anyMatch()
         * processing
         */
        Stream<Integer> s = Stream.of(1,2,3,4,5);
        boolean b1 = s.anyMatch(x -> (x<=3));
        System.out.println("anyMatch returned: " + b1); // anyMatch returned: true

        // This returns an exception "java.lang.IllegalStateException: stream has already been operated upon or closed"
        // boolean b2 = s.noneMatch(y -> (y<=3));
        // System.out.println("noneMatch returned: " + b2);

        /**
         * Use Supplier to reuse streams
         */
        Supplier<Stream<Integer>> streamSup = new Supplier<Stream<Integer>>() {
            @Override
            public Stream<Integer> get() {
                return Stream.of(1,2,3,4,5);
            }
        };

        b1 = streamSup.get().anyMatch(x -> (x<=3));
        System.out.println("anyMatch returned: " + b1); // anyMatch returned: true

        boolean b2 = streamSup.get().noneMatch(y -> (y<=3));
        System.out.println("noneMatch returned: " + b2); // noneMatch returned: false
    }
}
