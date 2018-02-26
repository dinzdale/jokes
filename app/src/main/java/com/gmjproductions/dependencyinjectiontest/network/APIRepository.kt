package com.gmjproductions.dependencyinjectiontest.network

import android.content.Context
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.Joke

/**
 * Created by garyjacobs on 2/23/18.
 */
interface APIRepository {
    fun loadNewJokeList(context: Context)
    //fun saveJokeListToDB(jokeList: List<Joke>)
    fun getJokeListFromDB(context: Context) : List<Joke>
}