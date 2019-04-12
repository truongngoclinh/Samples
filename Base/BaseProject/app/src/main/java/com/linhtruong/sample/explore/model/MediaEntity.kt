package com.linhtruong.sample.explore.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.linhtruong.sample.core.network.adapter.EmptyStringAsZeroIntAdapter


/**
 * @author linhtruong
 */
class MediaEntity(
        @SerializedName("url")
        val url: String,
        @SerializedName("height")
        @JsonAdapter(EmptyStringAsZeroIntAdapter::class)
        val height: Int,
        @SerializedName("width")
        @JsonAdapter(EmptyStringAsZeroIntAdapter::class)
        val width: Int
)