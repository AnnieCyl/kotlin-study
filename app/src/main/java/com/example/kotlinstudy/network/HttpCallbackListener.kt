package com.example.kotlinstudy.network

import java.lang.Exception

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}
