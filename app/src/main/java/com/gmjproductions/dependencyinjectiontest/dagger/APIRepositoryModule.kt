package com.gmjproductions.dependencyinjectiontest.dagger

import android.content.Context
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import com.gmjproductions.dependencyinjectiontest.network.JokeListFetcher
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by garyjacobs on 2/23/18.
 */
@Module
class APIRepositoryModule() {
    val urlS = "https://08ad1pao69.execute-api.us-east-1.amazonaws.com/dev/random_ten"
    @Provides
    @Named("URL")
    fun provideURL(): String = urlS

    @Provides
    fun provideContext(application: MyApplication): Context = application

    @Provides
    @Singleton
    fun provideDatabase(context: Context): JokesDatabase = JokesDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providesAPIRepository(@Named("URL") url: String, jokesDatabase: JokesDatabase): APIRepository = JokeListFetcher(urlS, jokesDatabase)
}