package com.example.rekotlintest.core.network

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
    const val URL = "https://api.themoviedb.org/3/"
    const val PREFIX_HTTP = "http"
    const val API_KEY = "c4ee6f8f89f1cf73f37f010b3ac69202"
}