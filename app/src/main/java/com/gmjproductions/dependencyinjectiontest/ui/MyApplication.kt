package com.gmjproductions.dependencyinjectiontest.ui

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.dagger.DaggerMyApplicationComponent
import com.gmjproductions.dependencyinjectiontest.dagger.MyApplicationComponent

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector


/**
 * Created by garyjacobs on 1/25/18.
 */

class MyApplication @Inject constructor() : Application(), HasActivityInjector, HasFragmentInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent
                .create()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }
}
