package question01;

import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = input.nextInt();

        List<Integer> sharedList = new ArrayList<>();
        Object lock = new Object();

        Thread evenThread = new Thread(new EvenNumberTask(number, sharedList, lock));
        Thread oddThread = new Thread(new OddNumberTask(number, sharedList, lock));

        evenThread.start();
        oddThread.start();
        try{
            evenThread.join();
            oddThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Collections.sort(sharedList);
        System.out.println("Shared List: " + sharedList);
        input.close();
    }


}
