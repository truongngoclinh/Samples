package samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.model;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 17:57.
 * @organization VED
 */
public class Post {
    String imageUrl;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
