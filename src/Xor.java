import argparse.ArgumentParser;
import argparse.Option;
import argparse.Options;
import argparse.ParseException;
import argparse.ParseResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Xor {

    public static String xor(String message, String key) {
        char[] m = message.toCharArray();
        char[] k = key.toCharArray();
        int msglen = m.length;
        int keylen = k.length;
        char[] result = new char[msglen];
        for (int i = 0; i < msglen; i++) {
            result[i] = (char) (m[i] ^ k[i % keylen]);
        }
        return new String(result);
    }

    public static void main(String[] args) {
        // We initialize our local variables to null (cf. Rot13.java)
        String message = null;
        String key = null;
        String inputfile = null;
        String keyfile = null;
        String outputfile = null;
        Boolean base64 = null;
        Boolean encrypt = null;
        Boolean decrypt = null;
        try {
            ArgumentParser parser = new ArgumentParser();
            Options options = new Options();
            options.addOption("k", "key", true, "Key");
            options.addOption("i", "inputfile", true, "Path to input file");
            options.addOption("kf", "keyfile", true, "Path to keyfile");
            options.addOption("o", "outputfile", true, "Path to output file");
            options.addOption("b64", "base64", false, Boolean.class, "Base64 encoding/decoding");
            options.addOption("e", "encrypt", false, Boolean.class, "Encrypt");
            options.addOption("d", "decrypt", false, Boolean.class, "Decrypt");
            ParseResult result = parser.parse(options, args);
            message = result.getPositionalArg(0);
            key = result.getParsedOptionValue("key");
            inputfile = result.getParsedOptionValue("inputfile");
            keyfile = result.getParsedOptionValue("keyfile");
            outputfile = result.getParsedOptionValue("outputfile");
            base64 = result.getParsedOptionValue("base64", false);
            encrypt = result.getParsedOptionValue("encrypt", false);
            decrypt = result.getParsedOptionValue("decrypt", false);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        if (message == null && inputfile == null) {
            System.out.println("A message or an input file is required.");
            System.exit(1);
        }
        if (key == null && keyfile == null) {
            System.out.println("A key or a keyfile is required.");
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
        if (key == null && keyfile != null) {
            Path path = Path.of(inputfile);
            try {
                key = Files.readString(path);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
        String output = null;
        if (base64) {
            if (encrypt) {
                String encrypted = xor(message, key);
                byte[] encoded = Base64.getEncoder().encode(encrypted.getBytes());
                output = new String(encoded);
            }
            else if (decrypt) {
                byte[] buffer = Base64.getDecoder().decode(message.getBytes());
                String decoded = new String(buffer);
                output = xor(decoded, key);
            }
            else {
                System.out.println("An encrypt or decrypt flag is needed to use the base64 feature");
                System.exit(1);
            }
        }
        else {
            output = xor(message, key);
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
