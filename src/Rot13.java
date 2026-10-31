import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseException;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Rot13 {

    public static final Map<Character, Character> table;

    static {
        table = new HashMap<>();
        int ord_a = (int) 'a';
        int ord_A = (int) 'A';
        char source;
        char target;
        for (int i = 0; i < 26; i++) {
            source = (char) (ord_a + i);
            target = (char) (ord_a + (i + 13) % 26);
            table.put(source, target);
            source = (char) (ord_A + i);
            target = (char) (ord_A + (i + 13) % 26);
            table.put(source, target);
        }
    }

    public static String rot13(String message) {
        StringBuilder builder = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (table.containsKey(ch)) {
                builder.append(table.get(ch));
            }
            else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        /*
         * Local variables are different from fields (instance variables and class variables).
         *
         * When we declare a field, the compiler assigns it a default value.
         *
         * When we declare a local variable, the compiler does not assign it a default value.
         *
         * Local variables have to be initialized before they are used.
         *
         * In fact, the Java Language Basics tutorial has this to say:
         *
         * "Accessing an unitializied local variable will result in a compile-time error."
         * (Source: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
         *
         * For this reason, we initialize our local variables to null.
         *
         * Note that our table variable, declared at the top of the class, does not need to be initialized,
         * because it is a field, a class variable, so the compiler assigns it a default value of null.
         *
         * Here is an explanation of the different kinds of variables in Java (instance, class, local, parameters):
         * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html.
         *
         * To make a long story short... the rules for local variables are different from the rules for fields.
         */
        String message = null;
        String inputfile = null;
        String outputfile = null;
        Boolean base64 = null;
        Boolean encrypt = null;
        Boolean decrypt = null;
        try {
            ArgumentParser parser = new ArgumentParser();
            Options options = new Options();
            options.addOption("i", "inputfile", true, "Path to input file");
            options.addOption("o", "outputfile", true, "Path to output file");
            options.addOption("b64", "base64", false, Boolean.class, "Base64 encoding/decoding");
            options.addOption("e", "encrypt", false, Boolean.class, "Encrypt");
            options.addOption("d", "decrypt", false, Boolean.class, "Decrypt");
            ParseResult result = parser.parse(options, args);
            message = result.getPositionalArg(0);
            inputfile = result.getParsedOptionValue("inputfile");
            outputfile = result.getParsedOptionValue("outputfile");
            base64 = result.getParsedOptionValue("base64", false);
            encrypt = result.getParsedOptionValue("encrypt", false);
            decrypt = result.getParsedOptionValue("decrypt", false);
        } catch (ParseException e) {
            System.out.println(e);
            System.exit(1);
        }
        if (message == null && inputfile == null) {
            System.out.println("A message or an input file is required.");
            System.exit(1);
        }
        if (message == null && inputfile != null) {
            Path path = Path.of(inputfile);
            try {
                message = Files.readString(path);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
        String output = null;
        if (base64) {
            if (encrypt) {
                String encrypted = rot13(message);
                byte[] encoded = Base64.getEncoder().encode(encrypted.getBytes());
                output = new String(encoded);
            }
            else if (decrypt) {
                byte[] buffer = Base64.getDecoder().decode(message.getBytes());
                String decoded = new String(buffer);
                output = rot13(decoded);
            }
            else {
                System.out.println("An encrypt or decrypt flag is needed to use the base64 feature");
                System.exit(1);
            }
        }
        else {
            output = rot13(message);
        }
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
