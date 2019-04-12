package com.linhtruong.sample.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.linhtruong.sample.core.network.adapter.NullAsEmptyStringAdapter

/**
 * @author linhtruong
 */
class GsonFactory private constructor() {
    companion object {
        fun create(): Gson {
            return GsonBuilder()
                    .registerTypeAdapterFactory(NullAsEmptyStringAdapter())
                    .create()
        }
    }
}