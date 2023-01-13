/*
Create a simple java program with main method that declares an array with 10 integer numbers. Find and print on the console the biggest odd number from the array.
•	Extract the logic that finds the biggest odd number in a static method. Call the method in the main method.
•	Test the program with array containing positive numbers, negative numbers, and zeros.
•	Handle the case when the array does not contain any odds numbers.

*/
public class Main {
    public static void main(String[] args) {
        int withPositiveNumbers[] = { 1,5,7,8,9,0,2,4,3,6 };
        System.out.println(findBiggestOdd(withPositiveNumbers));

        int withZeros[] = { 10,25,0,54,23,0,5,99,76,87 };
        System.out.println(findBiggestOdd(withZeros));

        int withNegativeNumbers[] = { 10,-25,0,-54,23,0,5,-99,-76,-87 };
        System.out.println(findBiggestOdd(withNegativeNumbers));

        int noOddNumber[] = { 2,16,14,8,10,18,20,6,12,4 };
        System.out.println(findBiggestOdd(noOddNumber));
    }

    private static int findBiggestOdd(int[] array) {
        int biggestOdd = 0;
        for(int i = 0; i < array.length; i++) {
            int number = array[i];
            if(number % 2 > 0 && number > biggestOdd) {
                biggestOdd = number;
            }
        }

        return biggestOdd;
    }
}