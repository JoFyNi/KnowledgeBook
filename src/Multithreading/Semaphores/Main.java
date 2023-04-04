package Multithreading.Semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Semaphores.Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
