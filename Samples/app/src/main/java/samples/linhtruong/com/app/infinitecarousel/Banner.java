package samples.linhtruong.com.app.infinitecarousel;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 3/2/17 - 16:03.
 * @organization VED
 */

public class Banner {

    public String url;
    public int id;

    private Banner() {

    }

    public static Banner create(String url, int id) {
        Banner banner = new Banner();
        banner.url = url;
        banner.id = id;

        return banner;
    }
}
