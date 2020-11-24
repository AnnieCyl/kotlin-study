package com.example.kotlinstudy.practice

import android.content.SharedPreferences

class SharedPreferencesTest {
    fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.block()
        editor.apply()
    }
}