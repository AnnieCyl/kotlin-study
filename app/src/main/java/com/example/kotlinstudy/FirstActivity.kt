package com.example.kotlinstudy

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kotlinstudy.chat.ChatActivity
import com.example.kotlinstudy.fragment.FragmentActivity
import com.example.kotlinstudy.news.NewsActivity
import kotlinx.android.synthetic.main.first_layout.*
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter

class FirstActivity : BaseActivity(), View.OnClickListener {
    private val TAG = "FirstActivity"

    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(TAG, "Lifecycle temp data from savedInstanceState")
        }

        // 将系统自带的标题栏隐藏掉
        supportActionBar?.hide()

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        Log.d(TAG, "Lifecycle onCreate, task id is $taskId")
        setContentView(R.layout.first_layout)
        button1.setOnClickListener(this)
        btn_change_image.setOnClickListener(this)
        startNormalActivity.setOnClickListener(this)
        startDialogActivity.setOnClickListener(this)
        btn_control_progress_bar.setOnClickListener(this)
        btn_add_progress.setOnClickListener(this)
        btn_alert.setOnClickListener(this)
        btn_list_view.setOnClickListener(this)
        btn_chat.setOnClickListener(this)
        btn_fragment.setOnClickListener(this)
        btn_news.setOnClickListener(this)
        btn_send_broadcast.setOnClickListener(this)
        btn_send_ordered_broadcast.setOnClickListener(this)
        btn_force_offline.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_fragment -> {
                val intent = Intent(this, FragmentActivity::class.java)
                startActivity(intent)
            }
            button1 -> onButton1Click()
            btn_change_image -> img_first.setImageResource(R.drawable.lazy)
            startNormalActivity -> {
                val intent = Intent(this, NormalActivity::class.java)
                startActivity(intent)
            }
            startDialogActivity -> {
                val intent = Intent(this, DialogActivity::class.java)
                startActivity(intent)
            }
            btn_control_progress_bar -> {
                if (pb_default.visibility == View.VISIBLE) {
                    pb_default.visibility = View.GONE
                } else {
                    pb_default.visibility = View.VISIBLE
                }
            }
            btn_add_progress -> {
                if (pb_bar.progress >= 100) {
                    pb_bar.progress = 0;
                } else {
                    pb_bar.progress = pb_bar.progress + 10
                }
            }
            btn_alert -> {
                AlertDialog.Builder(this).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which -> }
                    setNegativeButton("Cancel") { dialog, which -> }
                    show()
                }
            }
            btn_list_view -> {
                val intent = Intent(this, ListViewActivity::class.java)
                startActivity(intent)
            }
            btn_chat -> {
                val intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)
            }
            btn_news -> {
                val intent = Intent(this, NewsActivity::class.java)
                startActivity(intent)
            }
            btn_send_broadcast -> {
                val intent = Intent("com.example.kotlinstudy.MY_BROADCAST")
                // 这里一定要调用 setPackage 方法指定这条广播是发送给哪个应用的，从而让它变成一条显式广播，否则静态注册的 BroadcastReceiver 将无法接收到这条广播。
                // 因为 Android 8.0 以后静态注册的 BroadcastReceiver 是无法接收隐式广播的。
                intent.setPackage(packageName)
                sendBroadcast(intent)
            }
            btn_send_ordered_broadcast -> {
                val intent = Intent("com.example.kotlinstudy.MY_BROADCAST")
                // 这里一定要调用 setPackage 方法指定这条广播是发送给哪个应用的，从而让它变成一条显式广播，否则静态注册的 BroadcastReceiver 将无法接收到这条广播。
                // 因为 Android 8.0 以后静态注册的 BroadcastReceiver 是无法接收隐式广播的。
                intent.setPackage(packageName)
                sendOrderedBroadcast(intent, null)
            }
            btn_force_offline -> {
                val intent = Intent("com.example.kotlinstudy.FORCE_OFFLINE")
                sendBroadcast(intent)
            }
        }
    }

    fun save(inputText: String) {
        val output = openFileOutput("file_test", Context.MODE_APPEND)
        val writer = BufferedWriter(OutputStreamWriter(output))
        writer.use {
            it.write(inputText)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Lifecycle onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Lifecycle onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Lifecycle onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Lifecycle onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
        Log.d(TAG, "Lifecycle onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Lifecycle onRestart")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You click Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is $returnedData")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        val tempData = "Something you just typed"
        outState.putString("data_key", tempData)
    }

    private fun onButton1Click() {
//        finish()
//        Toast.makeText(this, "You click Button 1", Toast.LENGTH_SHORT).show()
//
//        // 显式 Intent
//        val intent = Intent(this, SecondActivity::class.java)
//
//        // 隐式 Intent
//        val intent = Intent("com.example.kotlinstudy.ACTION_START")
//        intent.addCategory("com.example.kotlinstudy.MY_CATEGORY")
//
//        // 隐式 Intent 启动浏览器，如果有 Activity 注册的 action 是 android.intent.action.VIEW，
//        // 且 data 设置了 android:scheme="https"，则会要求选择是启动浏览器还是符合条件的 activity
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = Uri.parse("https://www.baidu.com")
//
//        // 调用系统拨号界面
//        val intent = Intent(Intent.ACTION_DIAL)
//        intent.data = Uri.parse("tel:10086")
//
//        val data = "Hello SecondActivity"
//        val intent = Intent(this, SecondActivity::class.java)
//        intent.putExtra("extra_data", data)

        startActivity(intent)
        startActivityForResult(intent, 1)
        val inputText = et_first.text.toString()
        Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
        SecondActivity.actionStart(this, "data1", "data2")
    }

    inner class TimeChangeReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }
    }
}