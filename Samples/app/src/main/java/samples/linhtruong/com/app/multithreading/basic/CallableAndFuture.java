package samples.linhtruong.com.app.multithreading.basic;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 10/26/17 - 16:17.
 * @organization VED
 */

public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int duration = new Random().nextInt(2000);

                if (duration >= 1000) {
                    throw new Exception("Sleep for too long...");
                }

                System.out.println("Starting...");
                Thread.sleep(duration);
                System.out.println("Finished.");

                return duration;
            }
        });

        executorService.shutdown();
        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Exception ex = (Exception) e.getCause();
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
