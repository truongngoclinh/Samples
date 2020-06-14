@file:Suppress("MatchingDeclarationName")

package com.example.rekotlintest.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
fun <T : View> ViewGroup.inflate(id: Int, attach: Boolean = false): T =
    LayoutInflater.from(this.context).inflate(id, this, attach) as T

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
