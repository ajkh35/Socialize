package com.socialize.socialize.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.socialize.socialize.R
import com.socialize.socialize.models.Person
import kotlinx.android.synthetic.main.person.view.*

class PeopleAdapter(context: Context?,list: ArrayList<Person>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContext = context
    private val mList: ArrayList<Person>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.person,parent,false)
        return PersonHolder(view)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = mList!!.get(position)
        (holder as PersonHolder).getCard()!!.setOnClickListener {
            Toast.makeText(mContext,user.firstName,Toast.LENGTH_SHORT).show()
        }

        holder.getName().text = user.firstName
    }

    class PersonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var mCard = itemView!!.card
        private var mPic = itemView!!.person_pic
        private var mName = itemView!!.person_name

        fun getCard() : View? {
            return mCard
        }

        fun getName() : TextView {
            return mName
        }
    }
}
