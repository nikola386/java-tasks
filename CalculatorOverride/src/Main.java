/*
Create a simple Calculator program which  supports  add, multiply and subtract operations.
The purpose of the program is to demonstrate method overloading in java:
•	Program should support operations with the following data types: int, double and String.
•	Implement methods for addition:  add(double a, double b), add(int a, int b), add(String a, String b).
•	Implement methods for subtraction:  subtract (double a, double b), subtract (int a, int b), subtract (String a, String b).
•	Implement methods for multiplication: multiply (double a, double b), multiply (int a, int b), multiply (String a, String b).
•	Methods with Strings arguments must convert the String values first to numbers and make the calculation. \
    Also implement a proper validation for the entered String values, check if they could be converted to numeric values.
•	Create a main method to test all Calculator operations.
 */
public class Main {
    public static void main(String[] args) {

        try {
            System.out.printf("multiply(String): %f%n", add("asdad", ""));
        } catch (NumberFormatException nfe) {
            System.out.printf("multiply(String) with invalid strings: %s%n", nfe);
        }

        System.out.printf("add(double): %f%n", add(1.1, 2.2));
        System.out.printf("add(int): %d%n", add(1, 2));
        System.out.printf("add(String): %f%n", add("1.2", "2"));

        System.out.printf("subtract(double): %f%n", subtract(1.1, 2.2));
        System.out.printf("subtract(int): %d%n", subtract(1, 2));
        System.out.printf("subtract(String): %f%n", subtract("1.2", "2"));

        System.out.printf("multiply(double): %f%n", multiply(1.1, 2.2));
        System.out.printf("multiply(int): %d%n", multiply(1, 2));
        System.out.printf("multiply(String): %f%n", multiply("1.2", "2"));
    }

    private static boolean isNumeric(String a) {
        if (a == null) {
            return false;
        }
        try {
            Double.parseDouble(a);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static double add(String a, String b) {
        if(!isNumeric(a) || !isNumeric(b)) {
            throw new NumberFormatException("argument is not a number");
        }

        return Double.parseDouble(a) + Double.parseDouble(b);
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    private static double subtract(String a, String b) {
        if(!isNumeric(a) || !isNumeric(b)) {
            throw new IllegalArgumentException("argument is not a number");
        }

        return Double.parseDouble(a) - Double.parseDouble(b);
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static double multiply(String a, String b) {
        if(!isNumeric(a) || !isNumeric(b)) {
            throw new IllegalArgumentException("argument is not a number");
        }

        return Double.parseDouble(a) * Double.parseDouble(b);
    }
}