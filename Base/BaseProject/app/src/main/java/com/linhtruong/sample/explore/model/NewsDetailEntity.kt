package com.linhtruong.sample.explore.model

import android.os.Parcel
import android.os.Parcelable
import com.linhtruong.sample.core.extension.empty


/**
 * @author linhtruong
 */
class NewsDetailEntity constructor(
        var title: String? = String.empty(),
        var summary: String? = String.empty(),
        var imgUrl: String? = String.empty(),
        var storyUrl: String? = String.empty()
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(summary)
        parcel.writeString(imgUrl)
        parcel.writeString(storyUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsDetailEntity> {
        override fun createFromParcel(parcel: Parcel): NewsDetailEntity {
            return NewsDetailEntity(parcel)
        }

        override fun newArray(size: Int): Array<NewsDetailEntity?> {
            return arrayOfNulls(size)
        }
    }

}
