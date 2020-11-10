package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")
            et_account.setText(account)
            et_password.setText(password)
            cb_remember_pwd.isChecked = true
        }
        btn_login.setOnClickListener{
            val account = et_account.text.toString()
            val password = et_password.text.toString()
            if (account == "admin" && password == "123456") {
                val editor = prefs.edit()
                if (cb_remember_pwd.isChecked) {
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()

                val intent = Intent(this, FirstActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Wrong account or password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}