/**
 * Copyright © 2016, Oracle and/or its affiliates. All rights reserved.
 *
 * JDK 8 MOOC Lesson 2 homework
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Speakjava (Simon Ritter)
 */
public class Lesson2 {
  private static final String WORD_REGEXP = "[- .:,]+";

  /**
   * Run the exercises to ensure we got the right answers
   *
   * @throws java.io.IOException
   */
  public void runExercises() throws IOException, URISyntaxException {
    System.out.println("JDK 8 Lambdas and Streams MOOC Lesson 2");
    System.out.println("Running exercise 1 solution...");
    exercise1();
    System.out.println("Running exercise 2 solution...");
    exercise2();
    System.out.println("Running exercise 3 solution...");
    exercise3();
    System.out.println("Running exercise 4 solution...");
    exercise4();
    System.out.println("Running exercise 5 solution...");
    exercise5();
    System.out.println("Running exercise 6 solution...");
    exercise6();
    System.out.println("Running exercise 7 solution...");
    exercise7();
  }

  /**
   * Exercise 1
   *
   * Create a new list with all the strings from original list converted to 
   * lower case and print them out.
   */
  private void exercise1() {
    List<String> list = Arrays.asList(
        "The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG");

    /* YOUR CODE HERE */
    List<String> output = list.stream().map(String::toUpperCase).collect(Collectors.toList());
    output.forEach(System.out::println);
  }

  /**
   * Exercise 2
   *
   * Modify exercise 1 so that the new list only contains strings that have an
   * odd length
   */
  private void exercise2() {
    List<String> list = Arrays.asList(
        "The", "Quick", "BROWN", "Fox", "Jumped", "Over", "The", "LAZY", "DOG");

    /* YOUR CODE HERE */
    List<String> output = list.stream()
            .filter(s-> s.length() % 2 != 0)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
    output.forEach(System.out::println);
  }

  /**
   * Exercise 3
   *
   * Join the second, third and forth strings of the list into a single string,
   * where each word is separated by a hyphen (-). Print the resulting string.
   */
  private void exercise3() {
    List<String> list = Arrays.asList(
        "The", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

    /* YOUR CODE HERE */

    
    System.out.println(list.stream()
            .skip(2)
            .limit(3)
            .collect(Collectors.joining("-")));
  }

  /**
   * Count the number of lines in the file using the BufferedReader provided
   */
  private void exercise4() throws IOException, URISyntaxException {
    try (BufferedReader reader = Files.newBufferedReader(
        Paths.get(getFile()), StandardCharsets.UTF_8)) {
      /* YOUR CODE HERE */
        int count = 0;
        while (reader.readLine()!= null ){
            count ++;
        }
        System.out.println("Lines " + count);
    }
  }



    /**
   * Using the BufferedReader to access the file, create a list of words with
   * no duplicates contained in the file.  Print the words.
   * 
   * HINT: A regular expression, WORD_REGEXP, is already defined for your use.
   */
  private void exercise5() throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(
        Paths.get(getFile()), StandardCharsets.UTF_8)) {
      /* YOUR CODE HERE */
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();

        while (line != null){
            Arrays.stream(line.split(WORD_REGEXP)).filter(t -> !list.contains(t)).forEach( word -> list.add(word));
            line = reader.readLine();
        }
        System.out.println(list.stream().collect(Collectors.joining(" | ")));

        // better way
        long lineCount = reader.lines().count();
    }
  }
  
  /**
   * Using the BufferedReader to access the file create a list of words from 
   * the file, converted to lower-case and with duplicates removed, which is
   * sorted by natural order.  Print the contents of the list.
   */
  private void exercise6() throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(
        Paths.get(getFile()), StandardCharsets.UTF_8)) {
      /* YOUR CODE HERE */
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null){
            Arrays.stream(line.split(WORD_REGEXP)).map(String::toLowerCase).filter(t -> !list.contains(t)).forEach( word -> list.add(word));
            line = reader.readLine();
        }
        System.out.println(list.stream().sorted().collect(Collectors.joining(" | ")));

        // Better way
        List<String> uniqueWords = reader.lines()
                .flatMap(line1 -> Stream.of(line1.split(WORD_REGEXP)))
                .distinct()
                .collect(Collectors.toList());

        uniqueWords.stream()
                .forEach(System.out::println);
    }
  }
  
  /**
   * Modify exercise6 so that the words are sorted by length
   */
  private void exercise7() throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(
        Paths.get(getFile()), StandardCharsets.UTF_8)) {
      /* YOUR CODE HERE */
        List<String> list = new ArrayList<String>();
        String line = reader.readLine();
        while (line != null){
            Arrays.stream(line.split(WORD_REGEXP)).map(String::toLowerCase).filter(t -> !list.contains(t)).forEach( word -> list.add(word));
            line = reader.readLine();
        }
        System.out.println(list.stream().sorted((a,b) -> a.length()-b.length()).collect(Collectors.joining(" | ")));
    }
  }

    private URI getFile()  {
        try {
            return this.getClass().getResource("SonnetI.txt").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
  /**
   * Main entry point for application
   *
   * @param args the command line arguments
   * @throws IOException If file access does not work
   */
  public static void main(String[] args) throws IOException {
    Lesson2 lesson = new Lesson2();
      try {
          lesson.runExercises();
      } catch (URISyntaxException e) {
          e.printStackTrace();
      }
  }
}

