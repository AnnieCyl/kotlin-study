package com.example.kotlinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    private val data = listOf("Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana",
    "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango")

    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initFruits()
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        val adapter = FruitAdapter(this, R.layout.fruit_item_layout, fruitList)
        lv_first.adapter = adapter
        lv_first.setOnItemClickListener{ parent, view, position, id ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
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