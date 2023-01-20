/*
Create a simple FileManagerFactory program that supports the following operations: createEmptyFile(), deleteFile(), createDirectory().
•	The program must be designed in object-oriented manner.
•	Define an abstract class AbstractOperation with method execute(File file) throws OperationException.
•	Implement all file operations with concrete implementations of the abstract class.
•	Implement a Factory for creating the different operations. See Factory design pattern bellow.
•	Create your own Exception class OperationException (extend Exception class).
    All the operations must throw it in case of error. This means that you need to catch all Exceptions in
    the execute method, wrap them in new OperationException and rethrow this new OperationException.
•	Create a test program.
 */

package com.nikola.testapp;

import com.nikola.filemanager.FileManagerFactory;
import com.nikola.filemanager.exceptions.OperationException;
import com.nikola.filemanager.operations.AbstractOperation;

import java.io.File;

public class Main {
    public static void main(String[] args) throws OperationException {
        AbstractOperation op;
        File testDir = new File("./test");
        File testFile = new File("test.txt");

        FileManagerFactory factory = new FileManagerFactory();

        System.out.println("Test invalid operation");
        try {
            factory.getOperation("invalid");
        } catch (OperationException oe) {
            oe.printStackTrace();
        }

        System.out.println("Test null file");
        try {
            op = factory.getOperation("addDir");
            op.execute(null);
        } catch (OperationException oe) {
            oe.printStackTrace();
        }

        System.out.println("Creating directory");
        op = factory.getOperation("addDir");
        op.execute(testDir);

        System.out.println("Creating file");
        op = factory.getOperation("addFile");
        op.execute(testFile);

        System.out.println("Deleting directory and file");
        op = factory.getOperation("delete");
        op.execute(testFile);
        op.execute(testDir);
    }
}