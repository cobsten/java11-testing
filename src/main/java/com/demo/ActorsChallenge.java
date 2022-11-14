package com.demo;

public class ActorsChallenge {
/*
    public static void main(String... args) {
        System.out.println("THE ACTORS DASHBOARD!");
        System.out.println("**********************");
        //1. CREATE THE Actor MODEL IN Actor.java

        ActorsChallenge app = new ActorsChallenge();
        List<Actor> actors = new ArrayList<>();

        String fileName = "actors.txt";
        try (URI resource = ActorsChallenge.class.getResource(fileName).toURI();
             // 2. CODE TO READ FROM actors.txt
             BufferedReader reader = Files.newBufferedReader(Paths.get(resource.toURI()));
             Stream<String> stream = reader.lines();
            ) {
            //3. MAP EACH LINE TO ACTOR AND PRINT ON CONSOLE
            stream.map(string ->{
                List<String> rows = Arrays.stream(string.split("\n")).collect(Collectors.toList());
                rows.forEach(row -> {
                    String[] entry = row.split(" ");
                    actors.add(new Actor(entry[0],Integer.valueOf(entry[1]));
                });
            });

        }catch(URISyntaxException | IOException ioe){
            System.out.println(ioe);
        }

            System.out.println("**********************");
            //4. GET ACTORS WITH LEAST ACTED MOVIES
//        Optional<Actor> opt =
//        <<CODE HERE>>
//        System.out.println("Actor with least movies acted : " + opt.get());

            //5. GET ACTORS WITH MOST ACTED MOVIES

//        Optional<Actor> opt2 =
//        <<CODE HERE>>
//        System.out.println("Actor with most movies acted : " + opt.get());
            System.out.println("**********************");

            //6. MAP AND PRINT THE MOVIES ACTED AND ACTORS.
//        Map<Integer, String> map =
//        <<CODE HERE>>

//        System.out.println("Moives acted and actors : " + map);
        }
    }

 */
}