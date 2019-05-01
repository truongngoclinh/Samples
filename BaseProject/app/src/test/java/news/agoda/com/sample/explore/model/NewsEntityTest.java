package news.agoda.com.sample.explore.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linhtruong.sample.core.network.adapter.NullAsEmptyStringAdapter;
import com.linhtruong.sample.explore.model.NewsEntity;
import news.agoda.com.sample.ResourcesHelper;
import news.agoda.com.sample.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author linhtruong
 */
public class NewsEntityTest extends UnitTest {
    private Gson gson;
    private NewsEntity news;

    private String emptyJson = "{\"title\":\"\", \"abstract\":\"\", \"url\":\"\", \"multimedia\":\"\"}";
    private String nullMultimediaJson = "{\"title\":\"\", \"abstract\":\"\", \"url\":\"\", \"multimedia\":null}";
    private String nullTitleJson = "{\"title\":null, \"abstract\":\"\", \"url\":\"\", \"multimedia\":\"\"}";

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullAsEmptyStringAdapter())
                .create();
    }

    @Test
    public void testEmptyJson() {
        news = gson.fromJson(emptyJson, NewsEntity.class);
        assertNotNull(news);
        assertNull(news.getMultimedia());
        assertTrue(news.getTitle().isEmpty());
    }

    @Test
    public void testNullMultimedia() {
        news = gson.fromJson(nullMultimediaJson, NewsEntity.class);
        assertNotNull(news);
        assertNull(news.getMultimedia());
        assertTrue(news.getTitle().isEmpty());
    }

    @Test
    public void testNullTitle() {
        news = gson.fromJson(nullTitleJson, NewsEntity.class);
        assertNotNull(news);
        assertNotNull(news.getTitle());
        assertTrue(news.getTitle().isEmpty());
    }

    @Test
    public void testValuableData() {
        String json = ResourcesHelper.fromAsset(this.getClass(), "news.json");
        assertNotNull(json);
        assertTrue(!json.isEmpty());

        Type listType = new TypeToken<ArrayList<NewsEntity>>() {
        }.getType();
        ArrayList<NewsEntity> newsList = gson.fromJson(json, listType);
        assertNotNull(newsList);
        assertTrue(newsList.size() > 0);

        NewsEntity news = newsList.get(0);
        assertNotNull(news);
        assertTrue(!news.getTitle().isEmpty());
        assertNotNull(news.getMultimedia());
        assertTrue(news.getMultimedia().size() > 0);
    }
}
