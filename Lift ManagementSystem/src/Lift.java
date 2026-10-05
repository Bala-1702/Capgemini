import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Lift implements Runnable{


    private int currentFLoor = 1;

    private List<Integer> requests = new LinkedList<>();

    public Lift() {

    }
    public int getCurrentFloor(){
        return currentFLoor;
    }

    public void addRequests(int floor){
        synchronized (requests){
            requests.add(floor);
            requests.notify();
        }

    }

    @Override
    public void run(){
        while (true){
            int destination;
            synchronized (requests){
                while (requests.isEmpty()){
                    try {
                        requests.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
                destination = requests.remove(0);
            }

            moveToFloor(destination);
        }
    }

    public void moveToFloor(int destination) {
        while(currentFLoor!=destination){
            System.out.println("Lift "+ Thread.currentThread().getName()+" is in "+ currentFLoor);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if(currentFLoor<destination){
                currentFLoor++;
            }else {
                currentFLoor--;
            }
        }
        System.out.println("Lift reached "+destination);
    }
}
