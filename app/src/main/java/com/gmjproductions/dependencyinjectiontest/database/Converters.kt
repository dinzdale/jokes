package com.gmjproductions.dependencyinjectiontest.database

import android.arch.persistence.room.TypeConverter
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by garyjacobs on 2/25/18.
 */
class Converters() {
    @TypeConverter
    fun toArrayList(jokesS: String): ArrayList<Joke> {
        val token = object : TypeToken<ArrayList<Joke>>() {}
        return Gson().fromJson<ArrayList<Joke>>(jokesS, token.type)
    }
    @TypeConverter
    fun toString(list: ArrayList<Joke>): String {
        val token = object : TypeToken<ArrayList<Joke>>() {}
        return Gson().toJson(list, token.type)
    }
}
