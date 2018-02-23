package com.gmjproductions.dependencyinjectiontest.dagger;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;

import com.gmjproductions.dependencyinjectiontest.MainActivity;
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by garyjacobs on 1/25/18.
 */
@Module
public abstract class MyApplicaionModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();

//    private final MyApplication context;
//
//    public MyApplicaionModule(MyApplication context) {
//        this.context = context;
//    }
//
//    @Provides
//    @Singleton
//    public Context provideContext() {
//        return context;
//    }

}
