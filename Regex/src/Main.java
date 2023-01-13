import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Create a program that extracts all mails from a text.
•	The program should read the text from a file.
•	Create a suitable regular expression that match the mails. Use Java Matcher and Pattern classes to extract the mails from the text.
•	The output of the program must be a string containing all mails, separated with comma.
•	Make sure to close all streams that were used in the program.
*/
public class Main {
    final static String REGEX = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(REGEX);
        StringBuilder emails = new StringBuilder();

        try (FileReader reader = new FileReader("./text-with-emails.txt");
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String email = matcher.group();
                    emails.append(email).append(",");
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        String commaseparatedlist = emails.toString();
        if (commaseparatedlist.length() > 0){
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 1);
        }

        System.out.println(commaseparatedlist);
    }
}