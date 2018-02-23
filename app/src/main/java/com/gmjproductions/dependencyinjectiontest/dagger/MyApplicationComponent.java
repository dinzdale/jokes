package com.gmjproductions.dependencyinjectiontest.dagger;

import com.gmjproductions.dependencyinjectiontest.MainActivity;
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication;

import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by garyjacobs on 1/25/18.
 */
@Component(modules = {AndroidInjectionModule.class, MyApplicaionModule.class})
public interface MyApplicationComponent extends AndroidInjector<MyApplication> {
    //void inject(MainActivity myActvity);
}
