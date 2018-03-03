package me.davidllorca.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.di.ActivityScope;
import me.davidllorca.advancedandroid.ui.ActivityViewInterceptorModule;
import me.davidllorca.advancedandroid.ui.NavigationModule;


/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class, // Says to ActivityComponent which remember is the injector for
        // the activity that it can retrieve dependencies from a NavigationModule.
        ActivityViewInterceptorModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

        @Override
        public void seedInstance(MainActivity instance) {
            // Overriding we avoid inject Activity anywhere
        }
    }
}
