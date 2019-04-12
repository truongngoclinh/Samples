package news.agoda.com.sample;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author linhtruong
 */
public class ResourcesHelper {
    public static String fromAsset(Class classname, String fileName) {
        String json = "";
        try {
            InputStream is = classname.getClassLoader().getResourceAsStream(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }
}
