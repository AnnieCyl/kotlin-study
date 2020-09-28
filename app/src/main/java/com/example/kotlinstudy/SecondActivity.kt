package com.example.kotlinstudy

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.second_layout.*

class SecondActivity : BaseActivity() {
    private val TAG = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val extraData = intent.getStringExtra("extra_data")
        Log.d(TAG, "extra data is $extraData")
        Log.d(TAG, "Lifecycle onCreate, task id is $taskId")

        button2.setOnClickListener{
//            returnToFirstActivity()
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        returnToFirstActivity()
    }

    private fun returnToFirstActivity(){
        val intent = Intent();
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object{
        fun actionStart(context: Context, data1: String, data2: String){
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("param1", data1)
                putExtra("param2", data2)
            }

            context.startActivity(intent)
        }
    }
}