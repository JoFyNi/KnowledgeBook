package ThreadPools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private int id;
    public Processor(int id) {
        this.id = id;
    }

    public Processor(CountDownLatch latch) {
    }

    public void run() {
        System.out.println("starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("completed: " + id);
    }
}

public class ThreadPools {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i<5; i++) {
            executor.submit(new Processor(i));
        }

        // wait for Thread are completed, then shutdown
        executor.shutdown();
        System.out.println("All tasks submitted!");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            //
        }
        System.out.println("All tasks completed!");
    }
}

