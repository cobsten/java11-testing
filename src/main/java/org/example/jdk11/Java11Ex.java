package org.example.jdk11;


import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java11Ex {
    /*
:one: String : Implement any 5 new methods of String class in java 11
:two: File Handling : Implement any 2 method of Files class.
:three: Functional Programming : Implement Predicate.not with String.isBlank
:four: Type Inference : Use local variables in lambda
:five: Collections API : Convert collection to an array using toArray method
 */
    public static void main(String[] args){
        stringImpl();
        fileChecker();
    }

    //String : Implement any 5 new methods of String class in java 11
    private static void stringImpl(){
        String str = "  ksdjf lksdf lk lskj ldsjkk s ksjld jsk lksdjf lkjslkfj slkf jalj ";
        System.out.println("Original String: "+ str);
        System.out.println("Stripped String: "+str.strip());
        System.out.println("Stripped leading spaces: "+str.stripLeading());
        int count = 2;
        System.out.println("Repeated String "+count+" times: " + str.repeat(count));
        System.out.println("Check if String is blank:"+str.isBlank());

        String multiLineStr = "line1 \nline2\n   \r\n line3 ";
        System.out.println("Original multiline string:\n"+ multiLineStr);
        multiLineStr.lines().forEach(line ->{
            System.out.println("("+ line+") is Blank? " + line.isBlank());
        });

        String[] result = multiLineStr.lines()
                //Functional Programming : Implement Predicate.not with String.isBlank
                //Type Inference : Use local variables in lambda
                .filter(Predicate.not((source) -> source.isBlank()))
                //Collections API : Convert collection to an array using toArray method
                .collect(Collectors.toList()).toArray(new String[0]);
        System.out.println(Arrays.toString(result));


    }

    //File Handling : Implement any 2 method of Files class.
    private static void fileChecker(){
        String fileName = "/actors.txt";
        Path result;
        try {
            URL resource = Java11Ex.class.getResource(fileName);
            result = Paths.get(resource.toURI());
            System.out.println(Files.exists(result, LinkOption.NOFOLLOW_LINKS));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (
                // 2. CODE TO READ FROM actors.txt
                BufferedReader reader = Files.newBufferedReader(result);
                Stream<String> stream = reader.lines();
        ) {
            stream.forEach(line-> System.out.println(line));

        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
}
