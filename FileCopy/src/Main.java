import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
Create a program that copies a file. The program will receive file to copy (source) and file name to copy the file into (destination).
•	Implement the program using 4 different approaches:
    -	Implement the program using FileInputStream and FileOutputStream. The file should be copied byte by byte.
    -	Implement the program using BufferedInputStream and BufferedOutputStream. The file should be copied using a buffer with fixed size.
    -	Implement the program using FileChannel class.
    -	Implement the program using Files.copy static method.
•	Test the program with file that is at least 10 MB.
•	Measure the time spent to copy the file for all different approaches.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            throw new IllegalArgumentException("Missing parameters");
        }

        long bytes = Files.size(Paths.get(args[0]));
        System.out.printf("Coping %d bytes from %s to %s%n", bytes, args[0], args[1]);

        System.out.print("using usingIOStreams ");
        long start = System.currentTimeMillis();
        usingIOStreams(args[0], args[1]);
        long end = System.currentTimeMillis();
        System.out.printf("took %dms%n", (end - start));

        System.out.print("using usingBufferedIOStreams ");
        start = System.currentTimeMillis();
        usingBufferedIOStreams(args[0], args[1]);
        end = System.currentTimeMillis();
        System.out.printf("took %dms%n", (end - start));

        System.out.print("using usingFileChannel ");
        start = System.currentTimeMillis();
        usingFileChannel(args[0], args[1]);
        end = System.currentTimeMillis();
        System.out.printf("took %dms%n", (end - start));

        System.out.print("using usingFilesCopy ");
        start = System.currentTimeMillis();
        usingFilesCopy(args[0], args[1]);
        end = System.currentTimeMillis();
        System.out.printf("took %dms%n", (end - start));
    }

    private static void usingIOStreams(String source, String dest) {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(dest)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void usingBufferedIOStreams(String source, String dest) {
        try (FileInputStream in = new FileInputStream(source);
             BufferedInputStream bufferedIn = new BufferedInputStream(in);
             FileOutputStream out = new FileOutputStream(dest);
             BufferedOutputStream bufferedOut = new BufferedOutputStream(out)) {

            int numBytes;
            byte[] buffer = new byte[1000];
            while ((numBytes = bufferedIn.read(buffer))!= -1)
            {
                bufferedOut.write(buffer, 0, numBytes);
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private static void usingFileChannel(String source, String dest) {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(dest)) {

            FileChannel from = in.getChannel();
            FileChannel to = out.getChannel();
            to.transferFrom(from, 0, from.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void usingFilesCopy(String source, String dest) {
        try {
            Files.copy(Paths.get(source), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}