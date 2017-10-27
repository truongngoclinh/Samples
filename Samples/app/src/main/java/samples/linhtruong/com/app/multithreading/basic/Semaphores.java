package samples.linhtruong.com.app.multithreading.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Etc: limit connections
 *
 * @author linhtruong
 * @date 10/26/17 - 15:59.
 * @organization VED
 */

public class Semaphores {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Connection {

    private int mNumberOfConnections = 0;
    private static Connection mInstance;

    // use semaphore to limit connections
    private Semaphore mSemaphore = new Semaphore(10);

    private Connection() {

    }

    public synchronized static Connection getInstance() {
        if (mInstance == null) {
            mInstance = new Connection();
        }

        return mInstance;
    }

    public void connect() {
        try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            mSemaphore.release();
        }
    }

    public void doConnect() {
        synchronized (this) {
            mNumberOfConnections++;
            System.out.println("Number of connections: " + mNumberOfConnections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            // teardown connection after 2s
            mNumberOfConnections--;
        }
    }

}
