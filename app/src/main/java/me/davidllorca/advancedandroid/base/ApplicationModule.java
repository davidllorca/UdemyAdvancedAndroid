package me.davidllorca.advancedandroid.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */
//Module is a provider of dependencies
@Module
public class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    // Any class will inject the application context. This is useful for getting things like SharedPreferences or system Services.
    @Provides
    Context provideApplicationContext(){
        return application;
    }

}
