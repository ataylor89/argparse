import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class BuilderPattern {

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        Options options = new Options();
        options.addOption(Option.builder()
                            .shortName("k")
                            .longName("key")
                            .hasArg(true)
                            .delimiter(",")
                            .type(List.class)
                            .subType(Integer.class)
                            .description("Multi-integer key")
                            .build());
        options.addOption(Option.builder()
                            .shortName("i")
                            .longName("inputfile")
                            .hasArg(true)
                            .description("Path to input file")
                            .build());
        options.addOption(Option.builder()
                            .shortName("o")
                            .longName("outputfile")
                            .hasArg(true)
                            .description("Path to output file")
                            .build());
        options.addOption(Option.builder()
                            .shortName("b64")
                            .longName("base64")
                            .hasArg(false)
                            .type(Boolean.class)
                            .description("Base64 encoding/decoding")
                            .build());
        options.addOption(Option.builder()
                            .shortName("e")
                            .longName("encrypt")
                            .hasArg(false)
                            .type(Boolean.class)
                            .description("Encrypt")
                            .build());
        options.addOption(Option.builder()
                            .shortName("d")
                            .longName("decrypt")
                            .hasArg(false)
                            .type(Boolean.class)
                            .description("Decrypt")
                            .build());
        options.addOption(Option.builder()
                            .shortName("h")
                            .longName("help")
                            .hasArg(false)
                            .type(Boolean.class)
                            .description("Help menu")
                            .build());
        options.addOption(Option.builder()
                            .shortName("v")
                            .longName("verbose")
                            .hasArg(false)
                            .type(Boolean.class)
                            .description("Verbose mode")
                            .build());
        ParseResult result = parser.parse(options, args);
        String message = result.getPositionalArg(0);
        List<Integer> key = result.getParsedOptionValue("key");
        String inputfile = result.getParsedOptionValue("inputfile");
        String outputfile = result.getParsedOptionValue("outputfile");
        Boolean base64 = result.getParsedOptionValue("base64");
        Boolean encrypt = result.getParsedOptionValue("encrypt");
        Boolean decrypt = result.getParsedOptionValue("decrypt");
        Boolean help = result.getParsedOptionValue("help");
        Boolean verbose = result.getParsedOptionValue("verbose");
        if (help) {
            System.out.println("usage: java BuilderPattern [-h] [-v] [-i inputfile] [-k KEY] [-o OUTPUTFILE] [-e] [-d] [-b64] [message]");
            System.out.println("\nThe purpose of this file is to test the builder feature in Option.java");
            System.exit(0);
        }
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
        String output = null;
        if (encrypt) {
            if (base64) {
                String encrypted = AddSubtract2.encrypt(message, key);
                byte[] encoded = Base64.getEncoder().encode(encrypted.getBytes());
                output = new String(encoded);
            }
            else {
                output = AddSubtract2.encrypt(message, key);
            }
        }
        if (decrypt) {
            if (base64) {
                byte[] buffer = Base64.getDecoder().decode(message.getBytes());
                String decoded = new String(buffer);
                output = AddSubtract2.decrypt(decoded, key);
            }
            else {
                output = AddSubtract2.decrypt(message, key);
            }
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
