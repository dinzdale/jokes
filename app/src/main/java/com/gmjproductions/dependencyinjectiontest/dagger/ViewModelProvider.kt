package com.gmjproductions.dependencyinjectiontest.dagger

import android.arch.lifecycle.ViewModelProviders
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.model2.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model2.JokesViewModelFactory
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Created by garyjacobs on 2/24/18.
 */
@Module
class ViewModelProvider() {

    @Provides
    @Singleton
    fun provideViewModelFactory(apiRepository: APIRepository) = JokesViewModelFactory(apiRepository)

}