package com.example.kotlinstudy

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : BaseActivity() {
    private val TAG = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(TAG, "Lifecycle temp data from savedInstanceState")
        }

        // 将系统自带的标题栏隐藏掉
        supportActionBar?.hide()

        Log.d(TAG, "Lifecycle onCreate, task id is $taskId")
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
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:10086")

//            val data = "Hello SecondActivity"
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", data)

//            startActivity(intent)
//            startActivityForResult(intent, 1)
            val inputText = et_first.text.toString()
            Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()
            SecondActivity.actionStart(this, "data1", "data2")
        }

        btn_change_image.setOnClickListener{
            img_first.setImageResource(R.drawable.lazy)
        }

        startNormalActivity.setOnClickListener{
            val intent = Intent(this, NormalActivity::class.java)
            startActivity(intent)
        }

        startDialogActivity.setOnClickListener{
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }

        btn_control_progress_bar.setOnClickListener{
            if (pb_default.visibility == View.VISIBLE) {
                pb_default.visibility = View.GONE
            } else {
                pb_default.visibility = View.VISIBLE
            }
        }

        btn_add_progress.setOnClickListener{
            if (pb_bar.progress >= 100) {
                pb_bar.progress = 0;
            } else {
                pb_bar.progress = pb_bar.progress + 10
            }
        }

        btn_alert.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("This is Dialog")
                setMessage("Something important.")
                setCancelable(false)
                setPositiveButton("OK"){dialog, which ->  }
                setNegativeButton("Cancel"){dialog, which ->  }
                show()
            }
        }

        btn_list_view.setOnClickListener{
            val intent = Intent(this, ListViewActivity::class.java)
            startActivity(intent)
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
        when(item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You click Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You click Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode == Activity.RESULT_OK){
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
}