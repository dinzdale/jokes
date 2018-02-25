package com.gmjproductions.dependencyinjectiontest.dagger

import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import com.gmjproductions.dependencyinjectiontest.network.JokeListFetcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by garyjacobs on 2/23/18.
 */
@Module
class APIRepositoryModule() {
    val url = "https://08ad1pao69.execute-api.us-east-1.amazonaws.com/dev/random_ten"

    @Provides
    @Singleton
    fun providesAPIRepository() : APIRepository = JokeListFetcher(url)
}