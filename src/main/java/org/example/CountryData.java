package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CountryData {
    public static void main(String[] args){
        // Process the stream to find out

        String[] countries = new String[]{"Singapore","India","Japan","Russia","Netherlands","Ukraine","France","Italy"};
//        int minimum = Arrays.stream(countries).min(Comparator.comparing(String::length)).get().length();

        int minimum = Arrays.stream(countries).mapToInt(String::length).min().getAsInt();
        List<String> filteredCountries = Arrays.stream(countries).filter(string -> string.length() <= minimum).collect(Collectors.toList());

        // 1. Count of countries with minimum characters
        System.out.println("Number of minimum size "+filteredCountries.size());
        // 2. Display the countries which have upper case that has minimum characters
        filteredCountries.stream().map(String::toUpperCase).forEach(System.out::println);
        // 3. Calculate the max using reduce() method.
        System.out.println("Max length is:" + Arrays.stream(countries).map(String::length).reduce((i,i2)->{
        return i > i2 ? i: i2;
        }).get());
    }
}
