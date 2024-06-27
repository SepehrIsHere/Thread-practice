package question01;

import java.util.List;

public class OddNumberTask implements Runnable {
    private int number;
    private List<Integer> sharedList;
    private final Object lock;

    public OddNumberTask(int number, List<Integer> sharedList, Object lock) {
        this.number = number;
        this.sharedList = sharedList;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 1; i <= number; i += 2) {
                sharedList.add(i);
                System.out.println("Odd Thread added: " + i);
                try {
                    lock.wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
            }
        }
    }
}
