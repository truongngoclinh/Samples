package samples.linhtruong.com.app.facebook.retrofit;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/5/17 - 16:16.
 * @organization VED
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("me/feed")
    Call<FeedResponse> postMessageOnFeed(@Field("message") String message, @Field("access_token") String publishAccessToken);

    @FormUrlEncoded
    @POST("me/photos")
    Call<FeedResponse> postPhotoOnFeed(@Field("caption") String caption, @Field("images") byte[] data, @Field("access_token") String publishAccessToken);

    @FormUrlEncoded
    @POST("me/photos")
    Call<FeedResponse> getSessionUploadPhotoOnFeed(@Field("file_type") String caption, @Field("file_length") byte[] data, @Field("access_token") String publishAccessToken);

    @FormUrlEncoded
    @POST("me/photos")
    Call<FeedResponse> uploadPhotoOnFeed(@Field("file_type") String caption, @Field("source") byte[] data, @Field("access_token") String publishAccessToken);
}
