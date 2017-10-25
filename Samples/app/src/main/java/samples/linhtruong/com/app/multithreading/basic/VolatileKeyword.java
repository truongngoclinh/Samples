package samples.linhtruong.com.app.multithreading.basic;

import java.util.Scanner;

/**
 * Add volatile to get up-to-date value modified from main-memory
 *
 * @author linhtruong
 * @date 10/25/17 - 11:07.
 * @organization VED
 */

class Runner extends Thread {

    private volatile boolean isRunning = true;
    @Override
    public void run() {
       while (isRunning) {
           System.out.println("Thread is runnning!");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    public void shutdown() {
        isRunning = false;
    }
}

public class VolatileKeyword {

    public static void main(String[] args) {

        Runner r1 = new Runner();
        r1.start();

        System.out.println("Press enter key to stop the thread");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        r1.shutdown();
    }
}
