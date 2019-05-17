package samples.linhtruong.com.playground.java.test;

/**
 * @author linhtruong
 */
public class Outer {
    private String name = "just a name";
    class Inner {
        private void print() {
            System.out.println(name);
        }
    }
}
