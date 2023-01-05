package Latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
    private CountDownLatch latch;
    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        System.out.println("started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        latch.countDown();
    }
}

public class CountdownLatchesThread {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorr = Executors.newFixedThreadPool(3);

        for (int i= 0; i<3; i++) {
            executorr.submit(new Processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("completed");
    }
}
