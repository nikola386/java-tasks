import java.io.*;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Create a program that reads a text from a file and extracts all the unique words from that text.
    •	The program takes a file as input argument and returns as result a collection of Strings containing unique words (no duplicated words).
    •	The text is read from a file. File should contain multiple lines.
    •	The text may contain words, spaces, and the following symbols .  , :  ;  ? !
    •	To return the unique words choose a suitable collection in java. Which collection supports unique elements?
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Missing parameters");
        }

        LinkedHashSet<String> words = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile("(\\w+)");

        File sourceFile = new File(args[0]);
        try (FileReader fileReader = new FileReader(sourceFile);
             BufferedReader bReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group();
                    words.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        words.forEach(System.out::println);
        System.out.printf("Total unique words: %d%n", words.size());
    }
}