package com.gmjproductions.dependencyinjectiontest.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType

/**
 * Created by garyjacobs on 2/25/18.
 */
@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: Joke)

    @Insert
    fun insertJokeType(joketype: JokeType)

    @Query("SELECT * FROM jokes")
    fun getAllJokes() : List<Joke>

    @Query("SELECT * FROM jokes WHERE type=:jokeType")
    fun getJokesOfType(jokeType: String) : List<Joke>
}