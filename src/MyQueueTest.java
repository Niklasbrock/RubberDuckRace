import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @org.junit.jupiter.api.Test
    void poll() {
        MyQueue<Object> myQueue = new MyQueue<>();
        /*Create a test object and add it to Queue*/
        Object testObject = new Object();
        myQueue.add(testObject);
        /*Instantiate a new Object object and set it equal to the poll methods return object*/
        Object firstInQueue = myQueue.poll();
        /*Test whether the 2 objects are the same*/
        assertEquals(firstInQueue, testObject);
    }
}