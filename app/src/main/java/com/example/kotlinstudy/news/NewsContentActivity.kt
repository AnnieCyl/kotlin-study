package com.example.kotlinstudy.news

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_news_content.*

class NewsContentActivity : AppCompatActivity() {
    companion object{
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("newsTitle", title)
                putExtra("newsContent", content)
            }
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("newsTitle")
        val content = intent.getStringExtra("newsContent")
        if (title != null && content != null) {
            val fragment = fragment_news_content as NewsContentFragment
            fragment.refresh(title, content)
        }
    }
}