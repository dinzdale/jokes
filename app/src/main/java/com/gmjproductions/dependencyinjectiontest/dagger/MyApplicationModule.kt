package com.gmjproductions.dependencyinjectiontest.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.os.AsyncTask
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatActivity

import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import com.gmjproductions.dependencyinjectiontest.ui.JokesFragment
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication
import dagger.Binds

import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import android.support.v7.widget.ViewUtils
import android.app.Activity


/**
 * Created by garyjacobs on 1/25/18.
 */
@Module(includes = arrayOf(AndroidInjectionModule::class))
abstract class MyApplicationModule {
    @Binds
    abstract fun application(myApplication: MyApplication): Application


    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivityInjector(): MainActivity


    @ContributesAndroidInjector
    abstract fun contributeJokesFragmentInjector(): JokesFragment
}

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): Activity

}