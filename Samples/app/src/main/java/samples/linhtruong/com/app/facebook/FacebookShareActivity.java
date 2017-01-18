package samples.linhtruong.com.app.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

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

    @ViewById(R.id.btn_share_dialog)
    Button mShareWithNativeApp;

    @ViewById(R.id.btn_share_api)
    Button mShareWithoutNativeApp;

    @ViewById(R.id.share_content)
    LinearLayout mShareContent;

    private ShareDialog mShareDialog;
    private CallbackManager mCallbackManager;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_facebook_share);
    }

    @AfterViews
    void afterView() {
        FacebookSdk.sdkInitialize(this);

        mCallbackManager = CallbackManager.Factory.create();
        mShareDialog = new ShareDialog(this);
        mShareDialog.registerCallback(mCallbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                onShareSuccess();
            }

            @Override
            public void onCancel() {
                onShareCancel();
            }

            @Override
            public void onError(FacebookException error) {
                onShareFail(error.getMessage());
            }
        });
    }

    @Click(R.id.btn_share_dialog)
    void shareWithAppInstalled() {
        Log.d(TAG, "shareWithAppInstalled: ");
        shareFB();
    }

    @Click(R.id.btn_share_api)
    void shareWithoutAppInstalled() {
        Log.d(TAG, "shareWithoutAppInstalled: ");
        shareFBWebDialog();
    }

    public boolean isAppInstalled(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }

        try {
            ApplicationInfo info = mContext.getPackageManager().getApplicationInfo(packageName, 0);
            return info != null && info.enabled;
        } catch (Exception ignored) {

        }

        return false;
    }

    private void shareFB() {
        if (!isAppInstalled("com.facebook.katana")) {
            onShareCancel();
            return;
        }

        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(captureScreenShot(mShareContent))
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

//        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse("https://developers.facebook.com")).build();

        try {
            mShareDialog.show(content);
        } catch (Exception e) {
            onShareFail(e.getMessage());
        }
    }

    private void testGraphAPI() {
        Log.d(TAG, "testGraphAPI: ");
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

    private void shareFBWebDialog() {
        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "get permission success!");
                initGraphRequest();
            }

            @Override
            public void onCancel() {
                onShareCancel();
            }

            @Override
            public void onError(FacebookException error) {
                onShareFail(error.getMessage());
            }
        });
    }

    private void initGraphRequest() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/feed",
                null,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        shareFBWithGraphAPI();
                    }
                }
        ).executeAsync();
    }

    private void shareFBWithGraphAPI() {
        Log.d(TAG, "shareFBWithGraphAPI");
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(captureScreenShot(mShareContent))
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        ShareApi.share(content, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                onShareSuccess();
            }

            @Override
            public void onCancel() {
                onShareCancel();
            }

            @Override
            public void onError(FacebookException error) {
                onShareFail(error.getMessage());
            }
        });
    }

    private void onShareCancel() {
        Log.d(TAG, "onShareCancel: ");
    }

    private void onShareFail(String error) {
        Log.d(TAG, "onShareFail: " + error);
    }

    private void onShareSuccess() {
        Log.d(TAG, "onShareSuccess: ");
    }

    private Bitmap captureScreenShot(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawColor(Color.WHITE);
        view.layout(0, 0, view.getWidth(), view.getHeight());
        view.draw(c);
        view.setDrawingCacheEnabled(false);

        return b;
    }
}
