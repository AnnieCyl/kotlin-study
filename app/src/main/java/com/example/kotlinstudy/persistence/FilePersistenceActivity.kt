package com.example.kotlinstudy.persistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_file_persistence.*
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class FilePersistenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistence)
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = et_file.text.toString()
        save(inputText)
    }

    fun save(inputText: String) {
        val output = openFileOutput("file_test", Context.MODE_APPEND)
        val writer = BufferedWriter(OutputStreamWriter(output))
        writer.use {
            it.write(inputText)
        }
    }
}