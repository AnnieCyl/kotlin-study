package com.example.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.third_layout.*

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
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_first.layoutManager = layoutManager
        val adapter = FruitAdapterForRecyclerView(fruitList)
        rv_first.adapter = adapter
    }

    private fun initFruits(){
        repeat(2){
            fruitList.add(Fruit("Apple", R.drawable.apple))
            fruitList.add(Fruit("Banana", R.drawable.banana))
            fruitList.add(Fruit("Orange", R.drawable.orange))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon))
            fruitList.add(Fruit("Pear", R.drawable.pear))
            fruitList.add(Fruit("Grape", R.drawable.grape))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry))
            fruitList.add(Fruit("Cherry", R.drawable.cherry))
            fruitList.add(Fruit("Mango", R.drawable.mango))
        }
    }
}