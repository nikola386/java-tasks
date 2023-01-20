import java.util.LinkedList;
import java.util.Queue;

public class RequestProcessor {
    Queue<String> queue = new LinkedList<>();
    public boolean addRequest(String request) {
        return queue.add(request);
    }

    public void processAllRequests() {
        for(String element : queue) {
            System.out.printf("Processing \"%s\"%n", element);
        }
        queue.clear();
    }
}
