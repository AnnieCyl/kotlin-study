package com.example.kotlinstudy.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), View.OnClickListener {
    private val messageList = ArrayList<ChatMessage>()

    // adapter 是可空变量
    private var adapter: MessageAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initMessages()

        val layoutManager = LinearLayoutManager(this)
        rv_chat.layoutManager = layoutManager

        adapter = MessageAdapter(messageList)
        rv_chat.adapter = adapter

        btn_send.setOnClickListener(this)
    }

    private fun initMessages() {
        val msg1 = ChatMessage("Hello guy.", ChatMessage.TYPE_RECEIVED)
        messageList.add(msg1)
        val msg2 = ChatMessage("Hello. Who is that?", ChatMessage.TYPE_SEND)
        messageList.add(msg2)
        val msg3 = ChatMessage("This is Tom. Nice talking to you.", ChatMessage.TYPE_RECEIVED)
        messageList.add(msg3)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_send -> {
                val content = et_input_text.text.toString()
                if (content.isNotEmpty()){
                    val msg = ChatMessage(content, ChatMessage.TYPE_SEND)
                    messageList.add(msg)
                    // 当有新消息时，刷新 RecyclerView 中的显示
                    adapter?.notifyItemInserted(messageList.size - 1)
                    // 将 RecyclerView 定位到最后一行
                    rv_chat.scrollToPosition(messageList.size - 1)
                    // 清空输入框中的内容
                    et_input_text.setText("")
                }
            }
        }
    }
}