package samples.linhtruong.com.app.facebook.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/5/17 - 16:16.
 * @organization VED
 */

public interface ApiService {

    @GET("graph/api/facebook/test")
    Call<ApiResponse> getListResponse();

}
