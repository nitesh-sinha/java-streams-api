package com.nitesh.java8.streams.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitesinh on 6/27/17.
 */
public class Foo {
    public String name;
    public List<Bar> bars = new ArrayList<>();

    public Foo(String n) {
        name = n;
    }
}
