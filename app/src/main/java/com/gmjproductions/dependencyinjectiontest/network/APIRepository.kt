package com.gmjproductions.dependencyinjectiontest.network

import android.arch.lifecycle.LiveData
import com.gmjproductions.dependencyinjectiontest.model2.Joke

/**
 * Created by garyjacobs on 2/23/18.
 */
interface APIRepository {
    fun getJokeList(): List<Joke>
}