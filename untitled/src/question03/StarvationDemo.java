package question03;

public class StarvationDemo {
    private static final Object resource = new Object();

    public static void main(String[] args) {
        Thread highPriorityThread = new Thread(new Worker(), "High-Priority-Thread");
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);

        Thread lowPriorityThread1 = new Thread(new Worker(), "Low-Priority-Thread");
        lowPriorityThread1.setPriority(Thread.MIN_PRIORITY);
        Thread lowPriorityThread2 = new Thread(new Worker(), "Low-Priority-Thread");
        lowPriorityThread2.setPriority(Thread.MIN_PRIORITY);
        Thread lowPriorityThread3 = new Thread(new Worker(), "Low-Priority-Thread");
        lowPriorityThread3.setPriority(Thread.MIN_PRIORITY);

        highPriorityThread.start();
        lowPriorityThread1.start();
        lowPriorityThread2.start();
        lowPriorityThread3.start();
    }

    static class Worker implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (resource){
                    System.out.println(Thread.currentThread().getName() + " acquired the lock");
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        }
    }
}


