package samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;
import com.android.databinding.library.baseAdapters.BR;
import com.squareup.picasso.Picasso;
import samples.linhtruong.com.playground.java.google_arch.test.databinding_profile_sample.utils.CircleTransform;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 17:52.
 * @oanization VED
 */
public class User extends BaseObservable {
    private String name;
    private String email;
    private String profileImage;
    private String about;

    public ObservableField<Long> numberOfFollowers = new ObservableField<>();
    public ObservableField<Long> numberOfPosts = new ObservableField<>();
    public ObservableField<Long> numberOfFollowing = new ObservableField<>();

    public User() {

    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        notifyPropertyChanged(BR.profileImage);
    }

    @Bindable
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
        notifyPropertyChanged(BR.about);
    }

    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).transform(new CircleTransform()).into(view);
    }

}
