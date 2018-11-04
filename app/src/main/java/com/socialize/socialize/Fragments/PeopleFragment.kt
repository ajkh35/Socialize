package com.socialize.socialize.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.socialize.socialize.Activities.MainActivity
import com.socialize.socialize.Adapters.PeopleAdapter

import com.socialize.socialize.R
import com.socialize.socialize.Utilities.Constants
import com.socialize.socialize.models.Person
import org.json.JSONObject

private const val POSITION = "position"
private const val TITLE = "title"
private const val LIST = "list"
private const val GRID_SIZE = 2

class PeopleFragment : Fragment() {
    private var mPosition: Int? = null
    private var mTitle: String? = null
    private var mList: JSONObject? = null
    private lateinit var mGrid : RecyclerView
    private var mPersonList: ArrayList<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mPosition = it.getInt(POSITION)
            mTitle = it.getString(TITLE)
            mList = JSONObject(it.getString(LIST))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_people, container, false)

        mPersonList = ArrayList()
        mGrid = view.findViewById(R.id.grid)
        mGrid.layoutManager = GridLayoutManager(activity, GRID_SIZE)

        if(mList!!.getJSONObject(MainActivity.mUserName).has("friends")){
            createPersonList()
        }
        mGrid.adapter = PeopleAdapter(activity,mPersonList)

        return view
    }

    /**
     * Map the retrieved user to person
     */
    private fun userToPersonMapper(user: JSONObject): Person {
        val person = Person()
        person.firstName = user.getString("firstName")
        person.lastName = user.getString("lastName")
        person.userName = user.getString("userName")
        person.pic = user.getString("pic")
        return person
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: String,param3: JSONObject?) =
                PeopleFragment().apply {
                    arguments = Bundle().apply {
                        putInt(POSITION, param1)
                        putString(TITLE, param2)
                        putString(LIST, param3.toString())
                    }
                }
    }

    /**
     * Create the list of friends
     */
    private fun createPersonList() {
        if(activity!!.getSharedPreferences(Constants.MY_PREFS,0).getBoolean(Constants.ISADMIN,false)){
            Toast.makeText(context,getString(R.string.admin_contacts),Toast.LENGTH_SHORT).show()
        }else{
            val friends = mList!!.getJSONObject(MainActivity.mUserName)
                    .getJSONArray("friends")
            if(friends.length() != 0){
                var i = 0
                while(i < friends.length()){
                    if(mList!!.has(friends.get(i).toString())){
                        val user = mList!!.getJSONObject(friends.get(i).toString())
                        val person = userToPersonMapper(user)
                        mPersonList!!.add(person)
                    }
                    i++
                }
            }
        }
    }
}
