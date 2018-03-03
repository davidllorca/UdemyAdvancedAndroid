package me.davidllorca.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@Module
public abstract class ActivityViewInterceptorModule {

    @Binds
    abstract ActivityViewInterceptor bindDebugActivityViewInterceptor
            (DebugActivityViewInterceptor debugActivityViewInterceptor);
}
