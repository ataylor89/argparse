import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AddSubtract {

    public static String encrypt(String message, Integer key) {
        char[] input = message.toCharArray();
        char[] output = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            int source = (int) input[i];
            int target = source + key.intValue();
            output[i] = (char) target;
        }
        return new String(output);
    }

    public static String decrypt(String message, Integer key) {
        char[] input = message.toCharArray();
        char[] output = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            int source = (int) input[i];
            int target = source - key.intValue();
            output[i] = (char) target;
        }
        return new String(output);
    }

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        Options options = new Options();
        options.addOption("k", "key", true, Integer.class, "Single-integer key");
        options.addOption("i", "inputfile", true, "Path to input file");
        options.addOption("o", "outputfile", true, "Path to output file");
        options.addOption("e", "encrypt", false, Boolean.class, "Encrypt");
        options.addOption("d", "decrypt", false, Boolean.class, "Decrypt");
        ParseResult result = parser.parse(options, args);
        String message = result.getPositionalArg(0);
        Integer key = result.getOption("key", Integer.class);
        String inputfile = result.getOption("inputfile");
        String outputfile = result.getOption("outputfile");
        Boolean encrypt = result.getOption("encrypt", false, Boolean.class);
        Boolean decrypt = result.getOption("decrypt", false, Boolean.class);
        if (message == null && inputfile == null) {
            System.out.println("A message or an input file is required.");
            System.exit(1);
        }
        if (key == null) {
            System.out.println("A key is required.");
            System.exit(1);
        }
        if ((encrypt && decrypt) || (!encrypt && !decrypt)) {
            System.out.println("The encrypt flag or the decrypt flag must be set, but not both");
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
        String output = encrypt ? encrypt(message, key) : decrypt(message, key);
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
