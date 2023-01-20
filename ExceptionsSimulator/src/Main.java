/*
Create a simple program that simulates StackOverflowError  and OutOfMemoryError in java.
•	StackOverflowError – You could simulate that error with a program with endless recursion.
•	OutOfMemory error – You could simulate that error, for example, with a program that creates multiple objects into
    a loop and adds them to a List.  Run the program with small amount of memory. How to specify heap size for a
    program in Eclipse, see the resources bellow.
 */

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            simulateOufOfMemory();
        } catch (OutOfMemoryError oom) {
            oom.printStackTrace();
        }

        try{
            simulateStackOverflow();
        } catch (StackOverflowError so) {
            so.printStackTrace();
        }
    }

    private static void simulateStackOverflow() {
        simulateStackOverflow();
    }

    private static void simulateOufOfMemory() {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] b = new byte[1048576];
            list.add(b);
        }
    }
}