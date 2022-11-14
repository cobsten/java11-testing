package org.example;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
          Greeting g = (message) -> System.out.println(message);
          g.greet("Welcome");
        Stream<Integer> fdf = Stream.of(1,2,3,4,5);
    }
}