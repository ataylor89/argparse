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
/* I'll be using the static method Arrays.fill to set every element in an array of booleans to true */
import java.util.Arrays;

public class Primegen {
    
    public Primegen() {}

    public int[] generate(int count) {
        int[] primes = new int[count];
        double n = (double) count;
        // This is a formula for calculating an upper bound for the nth prime
        // I'll be crossing my fingers every time I run this program, hoping that my upper bound will work :)
        int upperBound = (int) ceil(n*log(n) + n*log(log(n)));
        boolean[] sieve = sieve(upperBound);
        int i = 2;
        int j = 0;
        while (i < sieve.length && j < primes.length) {
            if (sieve[i]) {
                primes[j] = i;
                j++;
            }
            i++;
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
        int[] primes = primegen.generate(count);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < primes.length; i++) {
            builder.append(primes[i]);
            if (i + 1 < primes.length) {
                builder.append(" ");
            }
        }
        String output = builder.toString();
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
