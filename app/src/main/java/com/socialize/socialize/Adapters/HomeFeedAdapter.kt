package com.socialize.socialize.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.socialize.socialize.R
import com.socialize.socialize.models.Post
import kotlinx.android.synthetic.main.post.view.*

class HomeFeedAdapter(list: List<Post>?,context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context
    private val mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.post,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostHolder).getCard().setOnClickListener {
            Toast.makeText(mContext,"This is card",Toast.LENGTH_SHORT).show()
        }
    }

    class PostHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var mCard : View?
        private var mPic : View?
        private var mTitle : View?
        private var mDateTime : View?
        private var mContent : View?

        init {
            mCard = itemView!!.card
            mPic = itemView.picture
            mTitle = itemView.title
            mDateTime = itemView.date_time
            mContent = itemView.content
        }

        fun getCard() : LinearLayout {
            return mCard as LinearLayout
        }
    }
}
