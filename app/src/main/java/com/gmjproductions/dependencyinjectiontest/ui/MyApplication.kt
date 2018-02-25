package com.gmjproductions.dependencyinjectiontest.ui

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.dagger.DaggerMyApplicationComponent
import com.gmjproductions.dependencyinjectiontest.dagger.MyApplicationComponent

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector


/**
 * Created by garyjacobs on 1/25/18.
 */

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    lateinit var myApplicationComponent: MyApplicationComponent

    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent
                .create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}
