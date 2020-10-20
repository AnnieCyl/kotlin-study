package com.example.kotlinstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener{
            val account = et_account.text.toString()
            val password = et_password.text.toString()
            if (account == "admin" && password == "123456") {
                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Wrong account or password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}