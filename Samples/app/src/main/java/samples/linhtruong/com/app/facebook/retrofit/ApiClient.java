package samples.linhtruong.com.app.facebook.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/5/17 - 16:21.
 * @organization VED
 */

public class ApiClient {

    public static final String BASE_URL = "graph.facebook.com/api/";
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }
}
