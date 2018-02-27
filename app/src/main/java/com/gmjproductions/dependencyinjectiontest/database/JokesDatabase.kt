package com.gmjproductions.dependencyinjectiontest.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType
import javax.inject.Inject

/**
 * Created by garyjacobs on 2/25/18.
 */
@Database(entities = arrayOf(JokeType::class, Joke::class), version = 1)
@TypeConverters(Converters::class)
abstract class JokesDatabase : RoomDatabase() {

    companion object {
        private var instance: JokesDatabase? = null
        fun getInstance(context: Context): JokesDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, JokesDatabase::class.java, JokesDatabase::class.java.simpleName).build()
            }
            return instance!!
        }
    }

    abstract fun jokesDao(): JokesDao
}