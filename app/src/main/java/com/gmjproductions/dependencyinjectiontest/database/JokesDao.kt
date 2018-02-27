package com.gmjproductions.dependencyinjectiontest.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType
import retrofit2.http.DELETE

/**
 * Created by garyjacobs on 2/25/18.
 */
@Dao
interface JokesDao {

    @Insert(onConflict = REPLACE)
    fun insertJoke(joke: Joke)

    @Insert(onConflict = REPLACE)
    fun insertJokeType(joketype: JokeType)

    @Query("SELECT * FROM jokes")
    fun getAllJokes(): List<Joke>

    @Query("SELECT * FROM joketype")
    fun getAllJokeTypes(): List<JokeType>

    @Query("SELECT * FROM jokes WHERE type=:jokeType")
    fun getJokesOfType(jokeType: JokeType): List<Joke>

    @Delete
    fun deleteJokes(jokeList: List<Joke>)

    @Delete
    fun deleteJokeTypes(jokeTypeList: List<JokeType>)
}