import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(args[1]), Charset.defaultCharset());
            for(String line : lines) {
                String[] entries = line.split(",");
                Arrays.sort(entries);
                System.out.println(entries);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
