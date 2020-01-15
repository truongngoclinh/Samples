package com.linhtruong.samplekt.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.linhtruong.samplekt.R

fun AppCompatActivity.addFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().add(R.id.container, fragment)
}