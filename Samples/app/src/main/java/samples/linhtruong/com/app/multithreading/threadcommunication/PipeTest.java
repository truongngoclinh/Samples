package samples.linhtruong.com.app.multithreading.threadcommunication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 2 threads communicate and transfer write/read data using java.io Pipe class.
 * For binary transferring: use {@link java.io.PipedInputStream & {@link java.io.PipedOutputStream}}
 * For normal data transferring: use {@link PipedWriter & {@link java.io.PipedReader}}
 *
 * We also can treat UI thread as writer then a worker thread as reader and run the long task.
 * But it can block UI thread if writing on a full pipe.
 *
 * @author linhtruong
 * @date 8/10/17 - 19:02.
 * @organization VED
 */

public class PipeTest {

    private static final int BUFFER_SIZE_CHARS = 1024 * 4;

    public static void main(String[] args) {
        final PipedReader pipedReader = new PipedReader(BUFFER_SIZE_CHARS);
        final PipedWriter pipedWriter = new PipedWriter();
        try {
            pipedReader.connect(pipedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        pipedWriter.write(i);
                        System.out.println("writing data: " + i);
                    }

                    pipedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                int i;
                try {
                    while ((i = pipedReader.read()) != -1) {
                        System.out.println("getting data: " + i);
                    }

                    pipedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("start transferring...");
        writer.start();
        reader.start();
    }
}
