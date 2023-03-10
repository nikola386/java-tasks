import java.io.IOException;
import java.util.Scanner;

/*
Create a simple command line calculator supporting add, subtract, and multiply operations:
        •	All operations must be separated in different methods.
        •	The operations should support double numbers.
        •	The program reads from the console and prints the results in the console.
        •	The user is asked in the console to enter two numbers, and operation (1-add, 2-subtract, 3-multiply, 4-exit).
        •	The program should work in a loop, so that the user can do multiple calculations. Program finishes when the exit (4) operation is entered.
*/

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Basic calculator");
        System.out.println("Operations: 1-add, 2-subtract, 3-multiply, 4-exit");

        int operation = 0;
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.print("operation: ");
            operation = in.nextInt();

            if(operation == 4) {
                System.exit(0);
            }
            if(operation > 4 || operation < 1) {
                System.out.println("Incorrect operation");
                continue;
            }

            System.out.print("a: ");
            double a = in.nextDouble();

            System.out.print("b: ");
            double b = in.nextDouble();

            double result = calculate(operation, a, b);
            System.out.printf("result: %f%n", result);
        }
    }

    private static double calculate(int operation, double a, double b) throws IOException {
        switch(operation) {
            case 1: return add(a, b);
            case 2: return subtract(a, b);
            case 3: return multiply(a, b);
            default: throw new IOException("Incorrect operation");
        }
    }


    private static double add (double a, double b) {
        return a + b;
    }

    private static double subtract (double a, double b) {
        return a - b;
    }

    private static double multiply (double a, double b) {
        return a * b;
    }
}