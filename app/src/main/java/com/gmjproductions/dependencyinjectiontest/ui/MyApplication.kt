package com.gmjproductions.dependencyinjectiontest.ui

import android.app.Activity
import android.app.Application
import android.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.dagger.DaggerMyApplicationComponent
import com.gmjproductions.dependencyinjectiontest.dagger.MyApplicationComponent
import com.gmjproductions.dependencyinjectiontest.dagger.MyApplicationModule
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector


/**
 * Created by garyjacobs on 1/25/18.
 */

class MyApplication : Application(), HasActivityInjector, HasFragmentInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent
                .builder()
                .create(this)
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }

}
