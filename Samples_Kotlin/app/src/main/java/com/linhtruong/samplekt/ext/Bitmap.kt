package com.linhtruong.samplekt.ext

import android.graphics.*
import android.widget.TextView

fun Bitmap.addWatermaskText(text: String, options: WatermaskOptions) {
    val bitmap = copy(config, true)
    val canvas = Canvas(bitmap)

}

enum class Corner {
    TOP_RIGHT,
    BOTTOM_RIGHT,
    TOP_LEFT,
    BOTTOM_LEFT
}

class WatermaskOptions(
    size: Int = 13,
    color: Int = Color.BLACK,
    position: Corner = Corner.BOTTOM_RIGHT,
    padding: Float = 10f,
    typeface: Typeface? = null
)