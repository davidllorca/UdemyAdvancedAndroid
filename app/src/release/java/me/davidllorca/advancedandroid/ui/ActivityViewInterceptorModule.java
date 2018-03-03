package me.davidllorca.advancedandroid.ui;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActivityViewInterceptorModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;

    }
}