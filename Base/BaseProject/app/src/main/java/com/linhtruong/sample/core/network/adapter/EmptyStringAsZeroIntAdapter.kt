package com.linhtruong.sample.core.network.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


/**
 * @author linhtruong
 */
@Suppress("UNCHECKED_CAST")
class EmptyStringAsZeroIntAdapter<Int> private constructor() : JsonDeserializer<Int> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Int {
        json?.let {
            if (it.isJsonPrimitive) {
                val jsonPrimitive = it.asJsonPrimitive
                if (jsonPrimitive.isString) {
                    if (json.asString.isEmpty()) {
                        return 0 as Int
                    }
                    return jsonPrimitive.asInt as Int
                }
            }
        }

        return context!!.deserialize(json, typeOfT)
    }
}