import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseException;
import argparse.ParseResult;
import java.io.IOException;
/* I want to use ceil as shorthand for Math.ceil, and log as shorthand for Math.log */
import static java.lang.Math.ceil;
import static java.lang.Math.log;
/* In case you're wondering, java.nio stands for Java New I/O... it's a really good addition to java.io */
import java.nio.file.Files;
import java.nio.file.Path;
/* I'll be using the ArrayList class, from the Java Collections Framework, to store prime numbers */
import java.util.ArrayList;
/* I'll be using the static method Arrays.fill to set every element in an array of booleans to true */
import java.util.Arrays;
import java.util.List;
/* I'll be using this class to reduce a Stream of Integer instances to a String that can be printed to stdout */
import java.util.stream.Collectors;

public class Primegen {
    
    public Primegen() {}

    public List<Integer> generate(int count) {
        /*
         * We'll store the primes we generate in this ArrayList.
         *
         * ArrayList is a popular class in the Java Collections Framework (JCF).
         *
         * ArrayList is a parameterized class, also called a generic class.
         *
         * It has a type parameter called E, and it accepts a type argument.
         *
         * The type argument is a lot like a method argument (an argument that you pass to a method).
         *
         * The type argument that you pass to ArrayList determines the data type that it stores.
         *
         * We pass a type argument of Integer to ArrayList,
         * which means that it will be storing instances of the Integer class.
         *
         * In case you're wondering, ArrayList uses a resizable array, behind the scenes, to store its data.
         *
         * See resources.md for links to tutorials on Java Generics and the Java Collections Framework.
         */
        List<Integer> primes = new ArrayList<>();
        // This is a formula for calculating an upper bound for the nth prime
        // I'll be crossing my fingers every time I run this program, hoping that my upper bound will work :)
        double n = (double) count;
        int upperBound = (int) ceil(n*log(n) + n*log(log(n)));
        // We create a sieve of size upperBound to help us generate primes
        // We loop through the sieve, and if the value at a given index is true, we add the index to our list of primes
        boolean[] sieve = sieve(upperBound);
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    /*
     * This is an implementation of the famous Sieve of Eratosthenes algorithm,
     * which has been around for over 2000 years.
     *
     * The word "sieve" gets used often in computer science.
     *
     * In my opinion, a sieve is a filter.
     *
     * A filter is a device that separates an input stream into two categories:
     * what gets filtered in and what gets filtered out.
     *
     * The kitchen colander is an example of a sieve.
     *
     * The Sieve of Eratosthenes gets its name from the fact that it creates a sieve (or filter),
     * which separates prime numbers from composite numbers, by means of a boolean array.
     */
    public boolean[] sieve(int size) {
        boolean[] sieve = new boolean[size];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i < size; i++) {
            if (sieve[i]) {
                for (int j = 2; i * j < size; j++) {
                    sieve[i * j] = false;
                }
            }
        }
        return sieve;
    }

    public static void main(String[] args) {
        Integer count = null;
        String outputfile = null;
        try {
            ArgumentParser parser = new ArgumentParser();
            Options options = new Options();
            options.addOption("o", "outputfile", true, "Path to output file");
            ParseResult result = parser.parse(options, args);
            count = Integer.parseInt(result.getPositionalArg(0));
            outputfile = result.getParsedOptionValue("outputfile");
        } catch (ParseException|NumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (count == null) {
            System.out.println("Missing a positional argument");
            System.exit(1);
        }
        Primegen primegen = new Primegen();
        List<Integer> primes = primegen.generate(count);
        /*
         * A stream, in Java, is "a sequence of elements from a source that supports aggregate operations".
         * (Source: https://www.oracle.com/technical-resources/articles/java/ma14-java-se-8-streams.html)
         *
         * We are calling List#stream (or ArrayList#stream) to get an instance of Stream<Integer>.
         * Then we can perform aggregate operations like map and collect on the stream of integers.
         *
         * The Stream API, introduced in Java 8, is really useful, and I'll link to some tutorials on it in resources.md.
         *
         * It's all based on the java.util.stream.Stream interface.
         *
         * The List interface, and Collection classes in general, have a stream method,
         * to return a Stream of the same type as the List (or Collection).
         */
        String output = primes.stream().map(String::valueOf).collect(Collectors.joining(" "));
        if (outputfile == null) {
            System.out.println(output);
        }
        else {
            Path path = Path.of(outputfile);
            try {
                Files.writeString(path, output);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}
