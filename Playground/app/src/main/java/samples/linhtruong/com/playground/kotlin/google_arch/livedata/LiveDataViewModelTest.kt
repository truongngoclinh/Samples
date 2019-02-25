package samples.linhtruong.com.playground.kotlin.google_arch.livedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Singleton


/**
 * @author linhtruong
 */
@Singleton
class LiveDataViewModelTest : ViewModel() {
    var serviceData = MutableLiveData<Integer>()

    /**
     * Testing how postValue work -> google said that it only update the last value
     */
    fun updateServiceData(value: Integer) {
        serviceData.postValue(value)
    }

    fun batchUpdateServiceData(value: ArrayList<Integer>) {
        for (i in value)  {
            serviceData.postValue(i)
        }
    }

    fun setServiceData(value: Integer) {
        serviceData.value = value
    }
}