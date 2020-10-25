package com.example.kotlinstudy.persistence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_file_persistence.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class FilePersistenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistence)

        btn_save_file.setOnClickListener{
            val inputText = et_file.text.toString()
            save(inputText)
        }

        btn_load_file.setOnClickListener{
            val inputText = load()
            et_file.setText(inputText)
            et_file.setSelection(inputText.length)
            Toast.makeText(this, "Loaded successfully.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun save(inputText: String) {
        val output = openFileOutput("file_test", Context.MODE_APPEND)
        val writer = BufferedWriter(OutputStreamWriter(output))
        writer.use {
            it.write(inputText)
        }
        Toast.makeText(this, "Saved successfully.", Toast.LENGTH_SHORT).show()
    }

    private fun load(): String {
        val content = StringBuilder()
        val input = openFileInput("file_test")
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine {
                content.append(it)
            }
        }
        return content.toString()
    }
}