package com.gmjproductions.dependencyinjectiontest.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.telecom.Call
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by garyjacobs on 2/23/18.
 */
class JokeListFetcher @Inject constructor(val url: String) : APIRepository {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun loadNewJokeList(context: Context) {
        val db = JokesDatabase.getInstance(context)
        val future = executor.submit(object : Callable<List<Joke>> {
            override fun call(): List<Joke> {
                val url = URL(url)
                val urlc = url.openConnection()
                val br = BufferedReader(InputStreamReader(urlc.getInputStream()))
                val token = object : TypeToken<ArrayList<Joke>>() {}.type
                val jokeList: List<Joke> = Gson().fromJson(br, token)
                return jokeList
                jokeList.forEach {
                    db.jokesDao().insertJoke(it)
                    it.type?.let {
                        db.jokesDao().insertJokeType(JokeType(it))
                    }
                }
            }
        }).get()

    }


    override fun getJokeListFromDB(context: Context): List<Joke> {
        val future = executor.submit(object : Callable<List<Joke>> {
            override fun call(): List<Joke> {
                return JokesDatabase.getInstance(context.applicationContext).jokesDao().getAllJokes()
            }
        })
        return future.get()
    }
}