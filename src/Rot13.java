import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        ArgumentParser parser = new ArgumentParser();
        Options options = new Options();
        options.addOption("i", "inputfile", true, "Path to input file");
        options.addOption("o", "outputfile", true, "Path to output file");
        ParseResult result = parser.parse(options, args);
        String message = result.getPositionalArg(0);
        String inputfile = result.getOption("inputfile");
        String outputfile = result.getOption("outputfile");
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
        String output = rot13(message);
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
