package samples.linhtruong.com.app.freetest;

/**
 * @author linhtruong
 */
public class Outer {
    private String name = "just my name";

    class Inner {
        private void printName() {
            System.out.println(name);
        }
    }
}
