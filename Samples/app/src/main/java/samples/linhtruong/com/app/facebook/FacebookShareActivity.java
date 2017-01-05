package samples.linhtruong.com.app.facebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.share.ShareApi;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samples.linhtruong.com.app.R;
import samples.linhtruong.com.app.facebook.retrofit.ApiClient;
import samples.linhtruong.com.app.facebook.retrofit.ApiResponse;
import samples.linhtruong.com.app.facebook.retrofit.ApiService;
import samples.linhtruong.com.base.BaseActivity;
import samples.linhtruong.com.base.BaseApplication;

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

        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        ShareApi.share(sharePhotoContent, null);
    }

    @Click(R.id.btn_share_iframe)
    void showWebJsDialog() {
        Log.d(TAG, "showWebJsDialog: ");
        ApiService service = ApiClient.getRetrofit().create(ApiService.class);
        Call<ApiResponse> call = service.getListResponse();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
               if (response.isSuccessful()) {

               }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private Bitmap getSampleBitmap() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.songs_white);
    }
}
