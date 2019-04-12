package com.linhtruong.sample.explore.model

import com.google.gson.annotations.SerializedName
import com.linhtruong.sample.core.network.CommonResponse


/**
 * @author linhtruong
 */
class NewsResponse(
        @SerializedName("results")
        val news: ArrayList<NewsEntity> = ArrayList()
) : CommonResponse()
