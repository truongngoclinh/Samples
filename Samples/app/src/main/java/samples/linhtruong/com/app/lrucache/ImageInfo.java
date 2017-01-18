package samples.linhtruong.com.app.lrucache;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/17/17 - 00:27.
 * @organization VED
 */

public class ImageInfo {

    private ImageInfo() {

    }

    public String url;

    public static ImageInfo create(String url) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.url = url;

        return imageInfo;
    }
}
