package com.example.kotlinstudy.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.fragment_left.*

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        btn_left_fragment.setOnClickListener{
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
        // 直接用布局文件中定义的 fragment ID 来获取相应的 fragment 实例
        val leftFragment = fragment_left as LeftFragment
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fl_right, fragment)
        // 以下这句代码可以实现返回栈，按下 back 键的时候是返回上一个 fragment，而不是直接退出这个 activity 了
        transaction.addToBackStack(null)
        transaction.commit()
    }
}