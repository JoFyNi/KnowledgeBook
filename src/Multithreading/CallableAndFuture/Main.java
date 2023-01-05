package CallableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping for too long!");
                }

                System.out.println("starting...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("finished");

                return duration;
            }
        });
        executor.shutdown();

        System.out.println("Result: " + future.get());
    }
}
