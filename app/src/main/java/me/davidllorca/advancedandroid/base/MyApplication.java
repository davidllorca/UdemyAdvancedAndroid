package me.davidllorca.advancedandroid.base;

import android.app.Application;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.di.ActivityInjector;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
