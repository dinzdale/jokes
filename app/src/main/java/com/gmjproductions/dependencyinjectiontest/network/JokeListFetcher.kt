package com.gmjproductions.dependencyinjectiontest.network

import android.arch.lifecycle.LiveData
import com.gmjproductions.dependencyinjectiontest.model2.Joke
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
class JokeListFetcher(val url: String) : APIRepository {
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
    override fun getJokeList(): List<Joke> {
        val future = executor.submit(object : Callable<List<Joke>> {
            override fun call(): List<Joke> {
                val url = URL(url)
                val urlc = url.openConnection()
                val br = BufferedReader(InputStreamReader(urlc.getInputStream()))
                val token = object : TypeToken<ArrayList<Joke>>() {}.type
                return Gson().fromJson(br, token)
            }
        })
        return future.get()
    }
}