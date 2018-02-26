package com.gmjproductions.dependencyinjectiontest.dagger

import android.content.Context
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by garyjacobs on 1/25/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, MyApplicationModule::class, APIRepositoryModule::class, ViewModelProvider::class))
interface MyApplicationComponent : AndroidInjector<MyApplication>
