package com.linhtruong.sample.core.network.adapter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.linhtruong.sample.core.extension.empty


/**
 * @author linhtruong
 */
@Suppress("UNCHECKED_CAST")
class NullAsEmptyStringAdapter : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        val rawType = type?.rawType as Class<T>
        if (rawType != String::class.java) {
            return null
        }

        return StringAdapter() as TypeAdapter<T>
    }

    inner class StringAdapter : TypeAdapter<String>() {
        override fun read(`in`: JsonReader?): String {
            if (`in`?.peek() == JsonToken.NULL) {
                `in`.nextNull()
                return String.empty()
            }

            return `in`!!.nextString()
        }

        override fun write(out: JsonWriter?, value: String?) {
            if (value == null) {
                out?.nullValue()
            }
            out?.value(value)
        }
    }
}