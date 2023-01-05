package LowLevelSynchronization;

import java.util.LinkedList;
import java.util.Random;

public class lowProcessor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size()== LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }
    public void consumer() throws InterruptedException {
        Random arg0 = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.println("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("value is: " + value);
                lock.notify();
            }
            Thread.sleep(arg0.nextInt(1000));
        }
    }

}
