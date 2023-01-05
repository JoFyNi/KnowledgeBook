package stopAndRun;

import java.util.Scanner;

class Prozessor extends Thread {
// data
    private boolean running = true;

    public void run() {
        while (true) {
            System.out.println("1");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void shutdown() {
        running = false;
    }
}

public class Main {
    public static void main(String[] args) {
        Prozessor proc1 = new Prozessor();
        proc1.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();

    }
}
