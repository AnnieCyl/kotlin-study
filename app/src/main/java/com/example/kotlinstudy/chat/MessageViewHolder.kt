package com.example.kotlinstudy.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.layout_message_left_item.view.*

sealed class MessageViewHolder(view: View): RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View) : MessageViewHolder(view) {
    val leftMessage : TextView = view.findViewById(R.id.tv_left_message)
}

class RightViewHolder(view: View) : MessageViewHolder(view) {
    val rightMessage : TextView = view.findViewById(R.id.tv_right_message)
}