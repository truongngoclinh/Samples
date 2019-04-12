package com.linhtruong.sample.core.network

/**
 * @author linhtruong
 */
object NetworkConst {
    const val CONNECT: Long = 10000
    const val READ: Long = 15000
    const val WRITE: Long = 15000

    const val CACHE_DIR_SIZE: Long = 20 * 1024 * 1024 // 20M disk caching
    const val CACHE_DIR_NAME = "news_cache"

    const val OK = "OK"

    const val URL = "https://api.myjson.com/"
    const val PREFIX_HTTP = "http"
}