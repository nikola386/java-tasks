## FileManager 
Create a simple FileManager program that supports the following operations: createEmptyFile(), deleteFile(), createDirectory().
*	The program must be designed in object-oriented manner.
*	Define an abstract class AbstractOperation with method
 execute(File file) throws OperationException.
*	Implement all file operations with concrete implementations of the abstract class.
*	Implement a Factory for creating the different operations. See Factory design pattern bellow.
*	Create your own Exception class OperationException (extend Exception class). All the operations must throw it in case of error. This means that you need to catch all Exceptions in the execute method, wrap them in new OperationException and rethrow this new OperationException.
*	Create a test program.
