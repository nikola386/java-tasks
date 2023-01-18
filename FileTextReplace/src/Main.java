import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Create a program that replace a particular word in some text with another word.
    •	The text should be read from a file. The file should contain multiple lines.
    •	The program should receive four parameters: source files, target file, word to replace, new word.
    •	The target file should contain the text with already replaced word.
    •	Implement the program using some concrete implementations of Reader and Writer interfaces.
    •	The program should work with huge files and not crash with OutOfMemory error.
    Hint: Do not read the whole text in memory, read, replace, and write it line by line.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Missing parameters");
        }

        String wordToReplace = args[2];
        String wordToReplaceWith = args[3];
        Pattern pattern = Pattern.compile(wordToReplace);

        File sourceFile = new File(args[0]);
        File destFile = new File(args[1]);

        try (FileReader fileReader = new FileReader(sourceFile);
             FileWriter destFileReader = new FileWriter(destFile);
             BufferedReader bReader = new BufferedReader(fileReader);
             BufferedWriter bWriter = new BufferedWriter(destFileReader)) {

            String line;
            while ((line = bReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                String modifiedLine = matcher.replaceAll(wordToReplaceWith);

                bWriter.write(modifiedLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}