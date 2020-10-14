package com.example.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.third_layout.*
import java.lang.StringBuilder

class ThirdActivity : BaseActivity() {
    private val TAG = "ThirdActivity"
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_layout)
        Log.d(TAG, "Lifecycle onCreate, task id is $taskId")

        button_exit.setOnClickListener{
            ActivityCollector.finishAll()
            android.os.Process.killProcess(android.os.Process.myPid())
        }

        initFruits()
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        rv_first.layoutManager = layoutManager
        val adapter = FruitAdapterForRecyclerView(fruitList)
        rv_first.adapter = adapter
    }

    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.banana))
            fruitList.add(Fruit(getRandomLengthString("Orange"), R.drawable.orange))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon))
            fruitList.add(Fruit(getRandomLengthString("Pear"), R.drawable.pear))
            fruitList.add(Fruit(getRandomLengthString("Grape"), R.drawable.grape))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry))
            fruitList.add(Fruit(getRandomLengthString("Cherry"), R.drawable.cherry))
            fruitList.add(Fruit(getRandomLengthString("Mango"), R.drawable.mango))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}