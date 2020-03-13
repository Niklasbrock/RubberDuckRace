import java.util.ArrayList;

public class MyQueue<Object> extends ArrayList<Object> {

    /*Method to retrieve the first object in the queue and then remove it from the queue*/
    public Object poll(){
        /*1. Retrieve index 0 of ArrayList*/
        Object bufferObject = get(0);
        /*2. Remove index 0 of ArrayList*/
        remove(0);
        /*3. Return retrieved object*/
        return bufferObject;
    }

}
