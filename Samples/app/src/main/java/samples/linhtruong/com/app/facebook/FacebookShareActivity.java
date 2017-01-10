package samples.linhtruong.com.app.facebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.facebook.retrofit.ApiClient;
import samples.linhtruong.com.app.facebook.retrofit.ApiConstant;
import samples.linhtruong.com.app.facebook.retrofit.FeedResponse;
import samples.linhtruong.com.app.facebook.retrofit.ApiService;
import samples.linhtruong.com.base.BaseActivity;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 1/4/17 - 16:08.
 * @organization VED
 */

@EActivity
public class FacebookShareActivity extends BaseActivity {

    private static final String TAG = "FacebookShareActivity";

    @ViewById(R.id.btn_share)
    Button mShareWithNativeApp;

    @ViewById(R.id.btn_share_iframe)
    Button mShareWithoutNativeApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_facebook_share);
    }

    @AfterViews
    void afterView() {
        FacebookSdk.sdkInitialize(this);
    }

    @Click(R.id.btn_share)
    void showNativeShareDialog() {
        Log.d(TAG, "showNativeShareDialog: ");
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(getSampleBitmap())
                .setCaption("test")
                .build();

        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();

        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        ShareDialog.show(this, sharePhotoContent);
    }

    @Click(R.id.btn_share_iframe)
    void publishWithGraphAPI() {
        Log.d(TAG, "publishWithGraphAPI: ");
        ApiService service = ApiClient.getRetrofit().create(ApiService.class);
//        Call<FeedResponse> call = service.postMessageOnFeed("test...", ApiConstant.ACCESS_TOKEN.publish_action);
        Call<FeedResponse> call = service.postPhotoOnFeed("test photo upload", getSampleBitmapAsByteArray(), ApiConstant.ACCESS_TOKEN.publich_action_and_user_photos);
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: successful published!");
                    Log.d(TAG, "onResponse: " + response.body());
                } else {
                    Log.d(TAG, "onResponse: unsuccessful!");
                    Log.d(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private Bitmap getSampleBitmap() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.songs_white);
    }

    private byte[] getSampleBitmapAsByteArray() {
        Bitmap bm = getSampleBitmap();
        if (bm != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            return outStream.toByteArray();
        } else {
            return null;
        }
    }
}
