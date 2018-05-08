package com.socialize.socialize.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialize.socialize.Adapters.HomeFeedAdapter

import com.socialize.socialize.R
import com.socialize.socialize.models.Post

private const val POSITION = "position"
private const val TITLE = "title"

class HomeFeed : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mFeedRecycler: RecyclerView
    private var mList: List<Post>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_home_feed, container, false)

        createList()
        mFeedRecycler = view.findViewById(R.id.feed)
        mFeedRecycler.layoutManager = LinearLayoutManager(activity)
        mFeedRecycler.adapter = activity?.let { HomeFeedAdapter(mList,it) }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(position: Int, title: String) =
                HomeFeed().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, position)
                        putString(TITLE, title)
                    }
                }
    }

    private fun createList() {
        mList = ArrayList()
        for (i in 0..9){
            (mList as ArrayList<Post>).add(Post("Title $i","Content $i","",""))
        }
    }
}
