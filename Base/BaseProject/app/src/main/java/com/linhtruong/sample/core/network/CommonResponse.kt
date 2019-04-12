package com.linhtruong.sample.core.network

import com.google.gson.annotations.SerializedName
import com.linhtruong.sample.core.extension.empty

/**
 * @author linhtruong
 */
open class CommonResponse {
    @SerializedName("status")
    var status: String = String.empty()
}