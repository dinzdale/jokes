package com.gmjproductions.dependencyinjectiontest.dagger

import android.arch.lifecycle.ViewModelProviders
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.model2.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model2.MyAsyncTask
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by garyjacobs on 2/6/18.
 */
@Module
data class ActivityModule @Inject constructor(val activity:MainActivity) {

    @Provides
    @Singleton
    fun provideMyAsyncTask (activity: MainActivity) = MyAsyncTask(activity)

    @Provides
    @Singleton
    fun provideViewModel() = ViewModelProviders.of(activity).get(JokesViewModel::class.java)
}