package com.example.kotlinstudy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener{
//            finish()
//            Toast.makeText(this, "You click Button 1", Toast.LENGTH_SHORT).show()

            // 显式 Intent
//            val intent = Intent(this, SecondActivity::class.java)

            // 隐式 Intent
//            val intent = Intent("com.example.kotlinstudy.ACTION_START")
//            intent.addCategory("com.example.kotlinstudy.MY_CATEGORY")

            // 隐式 Intent 启动浏览器，如果有 Activity 注册的 action 是 android.intent.action.VIEW，
            // 且 data 设置了 android:scheme="https"，则会要求选择是启动浏览器还是符合条件的 activity
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")

            // 调用系统拨号界面
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")

            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You click Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}