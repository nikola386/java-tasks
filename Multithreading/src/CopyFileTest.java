public class CopyFileTest {
    public static void main(String[] args) throws InterruptedException {
        runMultipleThreads();
        runSynchronisedThreadsUsingJoin();
    }

    private static void runMultipleThreads() {
        for(int i = 0; i < 5; i++) {
            Runnable task = new CopyFile("./source/" + (i+1), "./dest");
            Thread t = new Thread(task, "task_" + i);
            t.start();
        }
    }

    private static void runSynchronisedThreadsUsingJoin() throws InterruptedException {
        Runnable task = new CopyFile("./source/1", "./destinationPath1");
        Thread thread = new Thread(task, "synced_task_1");
        Runnable task2 = new CopyFile("./destinationPath1/1", "./destinationPath2");
        Thread thread2 = new Thread(task2, "synced_task_2");

        thread.start();
        thread.join();
        thread2.start();
    }
}