package com.socialize.socialize.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.socialize.socialize.Adapters.PeopleAdapter

import com.socialize.socialize.R
import com.socialize.socialize.models.Person

private const val POSITION = "position"
private const val TITLE = "title"
private const val GRID_SIZE = 2

class PeopleFragment : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null
    private lateinit var mGrid : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_people, container, false)

        mGrid = view.findViewById(R.id.grid)
        mGrid.layoutManager = GridLayoutManager(activity, GRID_SIZE)
        mGrid.adapter = PeopleAdapter(activity,getPersonList())

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
                PeopleFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                        putString(TITLE, param2)
                    }
                }
    }

    private fun getPersonList() : ArrayList<Person> {
        val list = ArrayList<Person>()

        for (i in 0..9){
            list.add(Person("Person $i","","","","",""))
        }

        return list
    }
}
