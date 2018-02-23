package com.gmjproductions.dependencyinjectiontest.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Display;

import com.gmjproductions.dependencyinjectiontest.dagger.DaggerMyApplicationComponent;
import com.gmjproductions.dependencyinjectiontest.dagger.MyApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


/**
 * Created by garyjacobs on 1/25/18.
 */

public class MyApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    public MyApplicationComponent appComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMyApplicationComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

}
