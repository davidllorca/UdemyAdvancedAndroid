package me.davidllorca.advancedandroid.di;

import android.app.Application;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
