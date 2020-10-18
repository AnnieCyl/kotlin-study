package com.example.kotlinstudy.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.fragment_news_title.*
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsTitleFragment : Fragment() {
    private var isTwoPane = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_title, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null
        val layoutManager = LinearLayoutManager(activity)
        rv_news_title.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        rv_news_title.adapter = adapter
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("This is news title $i", getRandomLengthString("This is news content $i"))
            newsList.add(news)
        }
        return newsList;
    }

    private fun getRandomLengthString(s: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(2){
            builder.append(s)
        }
        return builder.toString()
    }

    inner class NewsAdapter(val newsList: List<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.tv_news_item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener{
                val news = newsList[holder.adapterPosition]
                if (isTwoPane) {
                    // 如果是双页模式，则刷新 NewsContentFragment 中的内容
                    val fragment = newsContentFrag as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    // 如果是单页模式，则直接启动 NewsContentActivity
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }85
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }
}