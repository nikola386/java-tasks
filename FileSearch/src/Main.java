/*
Create a program that search if a file exists in a folder.
•	The program should receive 3 parameters: starting directory, file name to search for and max depth of nested folders.
•	The program searches recursively in all sub folders.
•	if the file is found the program must print the full path of the found file.
•	If the file with this name is present in multiple folders, the program should find all occurrences.
•	Implement the program using two different approaches:
    -	Implement the program using recursion.
    -	Implement the program using Files.walk static method.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 3 ) {
            throw new IllegalArgumentException();
        }

        String path = args[0];
        String fileName = args[1];
        int maxDepth = Integer.parseInt(args[2]);

        System.out.println("Files found using Recursion:");
        usingRecursion(path, fileName, maxDepth);

        System.out.println("Files found using Files.walk:");
        usingFileWalk(path, fileName, maxDepth);
    }

    private static void usingFileWalk(String rootDir, String fileName, int maxDepth) throws IOException {
        Path path = Paths.get(rootDir);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;
        try (Stream<Path> walk = Files.walk(path, maxDepth)) {
            result = walk
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                    .collect(Collectors.toList());
        }

        result.forEach(System.out::println);
    }

    private static void usingRecursion(String rootDir, String fileName, int maxDepth) {
        File root = new File( rootDir );
        File[] list = root.listFiles();

        if (list == null) return;

        int depth = maxDepth - 1;
        for (File f : list) {
            if (f.isDirectory() && depth > 0) {
                usingRecursion(f.getAbsolutePath(), fileName, depth);
            }
            else if (f.getName().equals(fileName)) {
                System.out.println(f.getAbsoluteFile());
            }
        }
    }
}