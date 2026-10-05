import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"Bala");
        for(Map.Entry m : map.entrySet()){
            System.out.println(m);
            System.out.println(m.getKey()+" "+m.getValue());
        }
        Lift lift1 = new Lift();
        Lift lift2 = new Lift();
        new Thread(lift1,"1").start();
        new Thread(lift2,"2").start();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Enter The Floor");
            int floor = sc.nextInt();

            int firstLift = Math.abs(lift1.getCurrentFloor() - floor);
            int secondLift = Math.abs(lift2.getCurrentFloor() - floor);

            if(floor == 0){
                System.exit(0);
            } else if (floor<1 || floor>4) {
                System.out.println("Enter valid floors");
                continue;
            }

            if(firstLift<=secondLift){
                lift1.addRequests(floor);
                System.out.println("Lift 1 is Assigned");
            }else {
                lift2.addRequests(floor);
                System.out.println("Lift 2 is Assigned");
            }
        }
    }
}