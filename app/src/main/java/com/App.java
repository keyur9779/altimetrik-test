package com;

import android.app.Activity;
import android.app.Application;

import com.dependencyInjector.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * File Description
 * <p>
 * Author:
 *
 *
 *
 */
public class App extends Application implements HasActivityInjector {

    private static App sInstance;


    public static App getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(App app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
