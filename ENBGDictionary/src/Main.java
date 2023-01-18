import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/*
Create a program that works as an English-Bulgarian dictionary:
    •	The program should give the following options to user: add new word, translate word, exit.
    •	Add new word – user enters a word in English and its Bulgarian translation in the console.
        New word is added to the dictionary and stored into a properties file.
    •	Translate word - user enters an English word in the console and it is translated to Bulgarian.
        The words are read from the properties file.
    •	Exit – the program terminates.
    •	The program should work into a loop, user should be able to translate/add multiple words, until exit command is chosen.
    •	Properties file should be used as dictionary database; each line contains one translation in the following format:
        dog : куче
        cat : котка
        cow : крава
 */
public class Main {
    private static final String dictionaryFile = "dictionary.properties";
    public static void main(String[] args) {
        System.out.println("English-Bulgarian Dictionary");
        System.out.println("Commands: add, translate, exit");

        String cmd;
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.print("cmd: ");
            cmd = in.next();

            if(cmd.equals("exit")) {
                System.exit(0);
            }
            if(!cmd.equals("add") && !cmd.equals("translate")) {
                System.out.println("Incorrect command");
                continue;
            }

            switch (cmd) {
                case "add" -> {
                    System.out.print("EN word: ");
                    String enWord = in.next();
                    System.out.print("BG word: ");
                    String bgWord = in.next();

                    addWord(enWord, bgWord);
                }
                case "translate" -> {
                    System.out.print("EN word: ");
                    String enWord = in.next();
                    String translated = translateWord(enWord);

                    if(translated == null || translated.isBlank()) {
                        System.out.printf("Word %s has no translation%n", enWord);
                    } else {
                        System.out.printf("%s : %s%n", enWord, translated);
                    }
                }
            }
        }
    }

    private static void addWord(String enWord, String bgWord) {
        try (OutputStream output = new FileOutputStream(dictionaryFile, true)) {
            Properties prop = new Properties();
            prop.setProperty(enWord, bgWord);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static String translateWord(String enWord) {
        try (InputStream input = new FileInputStream(dictionaryFile)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(enWord);
        } catch (FileNotFoundException f) {
            System.out.println("Dictionary file does not exists yet");
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}