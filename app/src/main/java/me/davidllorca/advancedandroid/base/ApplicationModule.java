package me.davidllorca.advancedandroid.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */

@Module
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides Context provideApplicationContext(){
        return application;
    }

}
