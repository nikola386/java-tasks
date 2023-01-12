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