import java.util.Arrays;

/*
You have a text defined in a String variable. Select one example sentence.
•	Create a method that finds the count of capital 'A' in a text. Use chartAt() method from String class.
•	Create a method that reverse the symbols in the text.  Use reverse() method from StringBuilder class.
•	Create a method that replaces 'A' with 'AA'. Use replaceAll() method from String class.
•	Create a method that splits the text in words. The method returns an array with all words in the sentence. Use split() method from String class.
 */
public class Main {
    public static void main(String[] args) {
        String sentence = "You have a text defined in a String variable. Select one example sentence. Create a method that finds the count of capital 'A' in a text. Use chartAt() method from String class";

        int numOfChars = getNumberOfChars(sentence, 'A');
        System.out.printf("getNumberOfChars: %d%n%n", numOfChars);

        String reversed = reverseSymbols(sentence);
        System.out.printf("reverseSymbols: %s%n%n", reversed);

        String replace = replaceChar(sentence, "A", "AA");
        System.out.printf("replaceChar: %s%n%n", replace);

        String[] words = slitByWords(sentence);
        System.out.printf("slitByWords: %s%n", Arrays.toString(words));
    }

    private static int getNumberOfChars(String string, char a) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == a) {
                count++;
            }
        }

        return count;
    }

    private static String reverseSymbols(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return builder.toString();
    }

    private static String replaceChar(String string, String from, String to) {
        return string.replaceAll(from, to);
    }

    private static String[] slitByWords(String string) {
        return string.split(" ");
    }
}