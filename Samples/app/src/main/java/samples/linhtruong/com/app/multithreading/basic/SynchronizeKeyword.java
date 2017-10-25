package samples.linhtruong.com.app.multithreading.basic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Synchronize block-code
 *
 * volatile & synchonized
 * https://stackoverflow.com/questions/3519664/difference-between-volatile-and-synchronized-in-java
 * https://stackoverflow.com/questions/42412311/java-volatile-keyword-not-working-as-expected
 *
 * Actually volatile is just memory visibility not atomicity so it doesnt ensure that read/write operations are atomic.
 *
 * @author linhtruong
 * @date 10/25/17 - 11:08.
 * @organization VED
 */

public class SynchronizeKeyword {

//    private volatile int count = 0; // not working
//    private int count = 0;
    private AtomicInteger count = new AtomicInteger(0); // of course work

   /* public synchronized void increase() { // of course work
        count++;
    }
*/
    public static void main(String[] args) throws InterruptedException {
        new SynchronizeKeyword().run();
    }

    public void run() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0; i < 100000; i++) {
                   count.getAndIncrement();
//                   count++;
//                   increase();
               }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0;i < 100000; i++) {
                   count.getAndIncrement();
//                   count++;
//                   increase();
               }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count = " + count);
    }
}
