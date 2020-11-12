package com.example.kotlinstudy.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {
    private val createBook = "create table Book(" +
            " id integer primary key autoincrement," +
            BookItem.AUTHOR + " text," +
            BookItem.PRICE + " real," +
            BookItem.PAGES + " integer," +
            BookItem.NAME + " text," +
            BookItem.CATEGORY_ID + " integer)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            CategoryItem.NAME + " text," +
            CategoryItem.CODE + " integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        Toast.makeText(context, "Create successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 每当升级一个数据库版本的时候，onUpgrade() 方法里面都一定要写一个相应的 if 判断语句
        if (oldVersion <= 1) {
            db?.execSQL(createCategory)
        }

        if (oldVersion <= 2) {
            db.execSQL("alter table Book add column ${BookItem.CATEGORY_ID} integer")
        }
    }
}