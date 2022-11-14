package com.demo.model;

public class Actor {
    private String name;
    private Integer movieCount;

    public Actor(String name, Integer movieCount){
         this.name = name;
         this.movieCount = movieCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(Integer movieCount) {
        this.movieCount = movieCount;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", movieCount=" + movieCount +
                '}';
    }
}
