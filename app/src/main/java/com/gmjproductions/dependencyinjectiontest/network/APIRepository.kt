package com.gmjproductions.dependencyinjectiontest.network

import android.arch.lifecycle.LiveData
import android.content.Context
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModel

/**
 * Created by garyjacobs on 2/23/18.
 */
interface APIRepository {
    fun loadNewJokeList(): List<Joke>
    fun loadAllJokesFromDB(): List<Joke>
    fun loadAllJokeTypesFromDB(): List<JokeType>
    fun loadAllJokesOfTypeFromDB(jokeType: JokeType): List<Joke>
}