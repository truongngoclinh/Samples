package samples.linhtruong.com.app.multithreading.basic;

/**
 * 3 ways to implement a thread
 *
 * @author linhtruong
 * @date 10/25/17 - 11:07.
 * @organization VED
 */

class MyRunner implements Runnable {

    @Override
    public void run() {
        System.out.println("This is thread 1");
    }
}


class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("This is thread 2");
    }
}

public class ThreadCreating {

    public static void main(String[] args) {

        new MyThread().start();
        new Thread(new MyRunner()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is thread 3");
            }
        }).start();
    }

}
