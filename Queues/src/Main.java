/*
Create a RequestProcessor class with the following methods:
•	addRequest(String request).
•	processAllRequests().
•	The requests are stored internally in RequestProcessor class as a queue.
•	When you call processAllRequests(), the requests are processed in the order they are added.
•	Use appropriate Queue methods to implement addRequest() and processAllRequests() methods.
•	The process method only prints a text in the console (Processing “request1”).
 */
public class Main {
    public static void main(String[] args) {
        RequestProcessor processor = new RequestProcessor();
        processor.addRequest("request1");
        processor.addRequest("request2");
        processor.addRequest("request3");
        processor.processAllRequests();

        processor.addRequest("request4");
        processor.addRequest("request5");
        processor.addRequest("request6");
        processor.processAllRequests();
    }
}