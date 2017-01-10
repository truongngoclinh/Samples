package samples.linhtruong.com.app.facebook.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/5/17 - 16:20.
 * @organization VED
 */

public class FeedResponse {

    @SerializedName("id")
    String postId;

    @SerializedName("error")
    Error error;

    public class Error {

        @SerializedName("message")
        String message;
    }
}
