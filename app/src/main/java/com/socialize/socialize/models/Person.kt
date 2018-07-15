package com.socialize.socialize.models

import org.json.JSONArray

//private const val MAX_CONTACTS = 100

data class Person(
        var firstName: String = "",
        var lastName: String = "",
        var pic: String = "",
        var userName: String = "",
        var email: String = "",
        var password: String = "",
        var friends: JSONArray? = JSONArray()
)
