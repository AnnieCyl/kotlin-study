package com.example.kotlinstudy.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.fragment_news_content.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsContentFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    fun refresh(title: String, content: String) {
        layout_content.visibility = View.VISIBLE
        tv_news_title.text = title
        tv_news_content.text = content
    }
}