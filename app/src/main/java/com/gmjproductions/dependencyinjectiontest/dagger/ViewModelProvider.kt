package com.gmjproductions.dependencyinjectiontest.dagger

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModelFactory
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import com.gmjproductions.dependencyinjectiontest.ui.JokesFragment
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Created by garyjacobs on 2/24/18.
 */
@Module
class ViewModelProvider() {

    @Provides
    @Singleton
    fun provideViewModelFactory() = JokesViewModelFactory()

}