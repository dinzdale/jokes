package com.gmjproductions.dependencyinjectiontest.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by garyjacobs on 1/25/18.
 */
@Entity(tableName = "joketype")
data class JokeType(@ColumnInfo(name = "joke_type") @PrimaryKey var type: String)


@Entity(tableName = "jokes")
class Joke() {
    @PrimaryKey
    var id: Int = 0
    var type: String? = null
    var setup: String? = null
    var punchline: String? = null
}

