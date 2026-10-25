import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Rot88 {

    /*
     * The maximum code point in the Unicode character encoding system.
     *
     * For example, the code point 97 maps to the character 'a',
     * and the code point 12460 (30AC in hex) maps to a character in the Japanese syllabary.
     *
     * Keep in mind that the prefix 0x denotes hexadecimal notation.
     *
     * 0x10FFFF is hexadecimal notation for the decimal value 1,114,111.
     *
     * We actually don't use this constant... I end up hard-coding it...
     * But I thought it would be nice to define it, and add a comment.
     */
    public static final int MAX_UNICODE = 0x10FFFF;

    public static String rot88(String message) {
        StringBuilder builder = new StringBuilder();
        for (char input : message.toCharArray()) {
            int source = (int) input;
            int target = (source + 0x88000) % 0x110000;
            char output = (char) target;
            builder.append(output);
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
        String output = rot88(message);
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
