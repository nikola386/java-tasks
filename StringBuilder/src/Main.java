/*
Create a method that takes as input parameter an integer - N and builds a string with the following content: 'The numbers from 0-N are 0 1 2 3 ...'
•	Implement a new method using StringBuilder (use append method).
•	Implement a new method using string concatenations (use + operator). This is not a good practice.
•	Measure and compare the execution time for both methods. Do that programmatically.
•	Run a test with N=100 000.
•	To check the output of the program, please change the following setting in the console:
*/

public class Main {
    public static void main(String[] args) {
        int N = 100000;

        long start1 = System.currentTimeMillis();
        String message = usingStringBuilder(N);
        long end1 = System.currentTimeMillis();
        System.out.println(message);

        long start2 = System.currentTimeMillis();
        String message2 = usingConcatenations(N);
        long end2 = System.currentTimeMillis();
        System.out.println(message2);

        System.out.println("usingStringBuilder took: "+ (end1 - start1));
        System.out.println("usingConcatenations took: "+ (end2 - start2));
    }

    private static String usingStringBuilder(int n) {
        StringBuilder sb = new StringBuilder("The numbers from 0-%d are".formatted(n));
        for(int i = 0; i <=n; i++) {
            sb.append(" ").append(i);
        }

        return sb.toString();
    }

    private static String usingConcatenations(int n) {
        String message = "The numbers from 0-%d are".formatted(n);
        for(int i = 0; i <=n; i++) {
            message += " ";
            message += i;
        }

        return message;
    }
}