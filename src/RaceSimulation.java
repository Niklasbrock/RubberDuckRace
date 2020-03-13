import java.awt.*;
import java.util.Random;


public class RaceSimulation {

    MyQueue<MyQueue<RubberDuck>> simulationQueue;
    RubberDuck randomlySelectedDuck;
    int randomlySelectedDuckQueueIndex;
    int size;

    public RaceSimulation(int size){
        this.size = size;
        simulationQueue = new MyQueue<>();
        startRace();
        System.out.println("The Winner is " + getWinner().number);
    }
    public void startRace() {
        /*Start a race with 10 rounds*/
        for (int i = 0; i < 10; i++) {
            iterate();
            if (randomlySelectedDuck != null) {
                moveDuck(randomNumberWithinSize());
            }
            System.out.println("Currently the race looks as follows:");
            printQueues();
            /*Selects the duck to be moved in next iteration*/
            if (size > 1){
                randomlySelectedDuck = pollDuck(randomNumberWithinSize());
            }
            size--;
        }
    }

    /*Each iteration 1 queue is removed and the size of the queues is shrunk by 1
    * iterate method adds N (size) Queues into the simulationQueue*/
    public void iterate(){
        simulationQueue.clear();
        int counter = 1;
        /*We have N (size) queues, each with N (size) yellow rubber ducks*/
        for (int i = 0; i < size; i++){
            simulationQueue.add(new MyQueue<>());
            for (int j = 0; j < size; j++){
                simulationQueue.get(i).add(new RubberDuck(counter, Color.YELLOW));
                counter++;
            }
        }
    }
    /*Returns first duck in queue, in randomly selected queue*/
    public RubberDuck pollDuck(int queueIndex){
        /*Store queueIndex for use in move method*/
        randomlySelectedDuckQueueIndex = queueIndex;
        return simulationQueue.get(queueIndex).poll();
    }

    /*Moves randomlySelectedDuck to next Queue in line at a random spot*/
    public void moveDuck(int subQueueIndex){
        if (randomlySelectedDuckQueueIndex != 0){
            /*Set the duck to be moved into next Queue index in line if not in first line*/
            simulationQueue.get(randomlySelectedDuckQueueIndex - 1).set(subQueueIndex, randomlySelectedDuck);

            System.out.println("Duck number " + randomlySelectedDuck.number + " in queue " +
                    randomlySelectedDuckQueueIndex + " moves to position " + subQueueIndex + " in queue " +
                    (randomlySelectedDuckQueueIndex - 1));
        } else {
            /*If in first line, the duck is moved back one Queue index*/
            simulationQueue.get(randomlySelectedDuckQueueIndex).set(subQueueIndex, randomlySelectedDuck);

            System.out.println("Duck number " + randomlySelectedDuck.number + " in queue " +
                    randomlySelectedDuckQueueIndex + " moves to position " + subQueueIndex + " in queue " +
                    (randomlySelectedDuckQueueIndex));
        }
    }

    public int randomNumberWithinSize(){
        Random random = new Random();
        return random.nextInt(size);
    }

    public void printQueues(){
        int counter = 1;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.println("In " + counter + ". place is duck number: " + simulationQueue.get(i).get(j).number);
                counter++;
            }
        }
    }

    public RubberDuck getWinner(){
        /*Return only remaining Rubber Duck in Queue as winner*/
        return simulationQueue.poll().poll();
    }

    public static void main(String[] args){

            RaceSimulation raceSimulation = new RaceSimulation(10);

    }
}
