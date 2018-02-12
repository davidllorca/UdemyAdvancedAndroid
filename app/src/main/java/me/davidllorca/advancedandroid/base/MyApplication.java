package me.davidllorca.advancedandroid.base;

import android.app.Application;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.di.ActivityInjector;
import me.davidllorca.udemyadvancedandroid.BuildConfig;
import timber.log.Timber;

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
        // It has to inject itself because ActivityInjector is injected in this class.
        component.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
