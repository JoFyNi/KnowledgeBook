package Semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance;
    private Semaphore semaphore = new Semaphore(10, true); // permit = 10
    // Semaphore is an Object that maintains a count
     // waits until a permit is set (in diesem fall 1)  -> await

    static {
        try {
            instance = new Connection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int connections = 0;
    private Connection() throws InterruptedException {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }
    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Connections: " +connections);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            connections--;
        }
    }
}
