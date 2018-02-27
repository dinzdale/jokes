package com.gmjproductions.dependencyinjectiontest.network

import android.content.Context
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModel

/**
 * Created by garyjacobs on 2/23/18.
 */
interface APIRepository {
    // TODO figure out how to inject this
    fun loadNewJokeList() : List<Joke>
}