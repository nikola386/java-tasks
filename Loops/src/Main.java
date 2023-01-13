/*
Create a simple java program with main method that prints in the system console the numbers from 0-99 in the following format: 0, 1, 2, 3 …
Implement the program by using three different loops and for each kind of loop create new a method:
•	use a 'for' loop
•	use a 'while' loop
•	use a do-while loop

 */
public class Main {
    static final int TO = 99;
    public static void main(String[] args) {
        usingFor(TO);
        usingWhile(TO);
        usingDoWhile(TO);
    }

    private static void usingFor(int to) {
        for(int i = 0; i <= to; i++) {
            System.out.println(i);
        }
    }
    private static void usingWhile(int to) {
        int i = 0;
        while(i <= to) {
            System.out.println(i);
            i++;
        }
    }

    private static void usingDoWhile(int to) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
        }
        while(i <= to);

    }
}