package com.udacity.gradle.jokes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Joker {
    public String getJoke() {
        return randomElement();
    }
    private String randomElement() {
        List<String> givenList = Arrays.asList(
                "This is a funny joke",
                "This is totally a funny joke",
                "This is totally a very funny joke");
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }
}
