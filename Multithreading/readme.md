Create a simple Java program that can copy multiple files at the same time
1)	Create class CopyFile with the following properties: file to be copied and destination path. The class should implement Runnable interface.
2)	Add clear output (System.out) messages before and after coping the file: “copy <file> starts…” and “copy <file> ends.”
3)	Create new class with a main method. This class should create 5 different CopyFile threads and test coping 5 different files at the same time. Use bigger files to copy (size more than 20MB). Check the order of starting and finishing different CopyFile threads.
4)	Create a new class that works with 2 CopyFile threads. The first thread should copy some file to destination path (folder) – “DestinationPath1”. After that the second thread should copy the file from folder – “DestinationPath1” to new destination path (folder) called “DestinationPath2”. Both threads should be started at the same time, but the second thread must wait for the first one to finish, otherwise it will fail because the file will not exist yet:
-	Synchronize both CopyFile threads by using Thread join method.
-	Synchronize both CopyFile threads by using a synchronized block on an Object and wait(),  notify() methods from the java.lang.Object class.
