package com.example.kotlinstudy.persistence

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        btn_create_database.setOnClickListener {
            dbHelper.writableDatabase
        }

        btn_add_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            val book1 = ContentValues().apply {
                put(BookItem.NAME, "Good good study")
                put(BookItem.AUTHOR, "Annie")
                put(BookItem.PAGES, 100)
                put(BookItem.PRICE, 200)
            }
            db.insert("Book", null, book1)
            val book2 = ContentValues().apply {
                put(BookItem.NAME, "Day day up")
                put(BookItem.AUTHOR, "Annie")
                put(BookItem.PAGES, 300)
                put(BookItem.PRICE, 400)
            }
            db.insert("Book", null, book2)

            db.execSQL(
                "insert into Book (${BookItem.NAME}, ${BookItem.AUTHOR}, ${BookItem.PAGES}, ${BookItem.PRICE}) values(?, ?, ?, ?)",
                arrayOf("Ha ha ha", "Annie", "200", "88")
            )

            Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show()
        }

        btn_update_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(BookItem.PRICE, 10.99)
            }
            // 第三个参数是 SQL 语句的 where 部分，? 是占位符，表示通过第四个参数提供的一个字符串数组为第三个参数中的每个占位符指定相应的内容
            db.update("Book", values, "name = ?", arrayOf("Good good study"))

            db.execSQL("update Book set price = ? where name = ?", arrayOf("11", "Ha ha ha"))

            Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show()
        }

        btn_delete_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("100"))

            db.execSQL("delete from Book where pages > ?", arrayOf("200"))

            Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
        }

        btn_query_data.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null, null)
            val cursor2 = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(BookItem.NAME))
                    val author = cursor.getString(cursor.getColumnIndex(BookItem.AUTHOR))
                    val pages = cursor.getInt(cursor.getColumnIndex(BookItem.PAGES))
                    val price = cursor.getDouble(cursor.getColumnIndex(BookItem.PRICE))
                    Log.d("DatabaseActivity", "Book name is $name")
                    Log.d("DatabaseActivity", "Book author is $author")
                    Log.d("DatabaseActivity", "Book pages is $pages")
                    Log.d("DatabaseActivity", "Book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()

            if (cursor2.moveToFirst()) {
                do {
                    val name = cursor2.getString(cursor2.getColumnIndex(BookItem.NAME))
                    val author = cursor2.getString(cursor2.getColumnIndex(BookItem.AUTHOR))
                    val pages = cursor2.getInt(cursor2.getColumnIndex(BookItem.PAGES))
                    val price = cursor2.getDouble(cursor2.getColumnIndex(BookItem.PRICE))
                    Log.d("DatabaseActivity", "2-Book name is $name")
                    Log.d("DatabaseActivity", "2-Book author is $author")
                    Log.d("DatabaseActivity", "2-Book pages is $pages")
                    Log.d("DatabaseActivity", "2-Book price is $price")
                } while (cursor2.moveToNext())
            }
            cursor2.close()
        }
    }
}