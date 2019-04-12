package com.linhtruong.sample.explore.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.linhtruong.sample.core.network.adapter.EmptyStringAsNullTypeAdapter


/**
 * @author linhtruong
 */
class NewsEntity(
        @SerializedName("title")
        val title: String,
        @SerializedName("abstract")
        val abstract: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("multimedia")
        @JsonAdapter(EmptyStringAsNullTypeAdapter::class)
        val multimedia: ArrayList<MediaEntity>?
)