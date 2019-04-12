package news.agoda.com.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linhtruong.sample.core.network.adapter.NullAsEmptyStringAdapter;
import com.linhtruong.sample.explore.model.MediaEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author linhtruong
 */
public class MediaEntityTest {
    private Gson gson;
    private MediaEntity media;

    private String emptyDimensJson = "{\"url\":\"http://static01.nyt.com/images/2015/08/13/business/13amazon-selects-slide-AUYG/13amazon-selects-slide-AUYG-mediumThreeByTwo210.jpg\"," +
            "\"format\":\"normal\"," +
            "\"height\":\"\"," +
            "\"width\":\"210\" }";

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullAsEmptyStringAdapter())
                .create();
    }

    @Test
    public void testEmptyDimens() {
        media = gson.fromJson(emptyDimensJson, MediaEntity.class);
        assertNotNull(media);
        assertEquals(media.getHeight(), 0);
        assertEquals(media.getWidth(), 210);
    }

    @Test
    public void testValueableJson() {
        media = gson.fromJson(ResourcesHelper.fromAsset(this.getClass(), "media.json"), MediaEntity.class);
        assertNotNull(media);
        assertTrue(media.getUrl().startsWith("http:"));
        assertTrue(media.getHeight() >= 0);
    }
}
