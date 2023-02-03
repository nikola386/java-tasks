import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFile implements Runnable {
    private final String source;
    private final String dest;

    public CopyFile(String source, String dest) {
        this.source = source;
        this.dest = dest;
    }

    @Override
    public void run() {
        Path sourceFile = Paths.get(source);
        Path destDir = Paths.get(dest);

        if(!Files.isRegularFile(sourceFile)) {
            throw new IllegalArgumentException("source is not a file");
        }

        if(!Files.exists(destDir)) {
            try {
                Files.createDirectory(destDir);
            } catch (IOException e) {}
        }

        System.out.printf("Start copying %s -> %s%n", source, dest);
        try {
            Files.copy(sourceFile, destDir.resolve(sourceFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("End copying %s -> %s%n", source, dest);
    }
}
