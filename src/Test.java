import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseResult;

public class Test {

    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        Options options = new Options();
        options.addOption("k", "key", true, "The key used for encryption or decryption");
        options.addOption("o", "outputfile", true, "The path to the output file");
        options.addOption("v", "verbose", false, "Enable verbose mode");
        ParseResult result = parser.parse(options, args);
        System.out.println(result);
    }
}
