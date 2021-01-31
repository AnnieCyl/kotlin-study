package com.example.kotlinstudy.practice

import android.content.Context
import android.content.Intent

class Reified {
    inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit){
        val intent = Intent(context, T::class.java)
        intent.block()
        context.startActivity(intent)
    }
}