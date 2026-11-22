import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * This class is a Java binding for the rsa/decrypt.py Python file
 */
public class Decrypt {

    public static void main(String[] args) {
        try {
            String[] command = new String[args.length + 2];
            command[0] = "python3";
            command[1] = "rsa/decrypt.py";
            for (int i = 0; i < args.length; i++) {
                command[i+2] = args[i];
            }
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PYTHONUNBUFFERED", "1");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
