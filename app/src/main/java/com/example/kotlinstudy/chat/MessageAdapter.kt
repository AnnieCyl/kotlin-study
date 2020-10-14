package com.example.kotlinstudy.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import java.lang.IllegalArgumentException

class MessageAdapter(val messageList: List<ChatMessage>): RecyclerView.Adapter<MessageViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return message.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = if (viewType == ChatMessage.TYPE_RECEIVED) {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_message_left_item, parent, false)
        LeftViewHolder(view)
    } else {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_message_right_item, parent, false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMessage.text = message.content
            is RightViewHolder -> holder.rightMessage.text = message.content
        }
    }

    override fun getItemCount() = messageList.size
}