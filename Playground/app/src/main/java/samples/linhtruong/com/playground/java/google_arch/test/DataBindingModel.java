package samples.linhtruong.com.playground.java.google_arch.test;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import samples.linhtruong.com.playground.BR;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/21/18 - 15:19.
 * @organization VED
 */
public class DataBindingModel extends BaseObservable {
    private String firstName;
    private String lastName;

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    /*    public static ObservableField<String> name = new ObservableField<>();
    public static ObservableField<String> email = new ObservableField<>();

    public static ObservableField<String> getEmail() {
        return email;
    }

    public static ObservableField<String> getName() {
        return name;
    }*/
}
