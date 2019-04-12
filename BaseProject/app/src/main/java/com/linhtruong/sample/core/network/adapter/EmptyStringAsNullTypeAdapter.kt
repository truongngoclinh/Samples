package com.linhtruong.sample.core.network.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


/**
 * @author linhtruong
 */
class EmptyStringAsNullTypeAdapter<T> private constructor() : JsonDeserializer<T> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T? {
        json?.let {
            if (it.isJsonPrimitive) {
                val jsonPrimitive = it.asJsonPrimitive
                if (jsonPrimitive.isString && json.asString.isEmpty()) {
                    return null
                }
            }
        }

        return context?.deserialize(json, typeOfT)
    }
}