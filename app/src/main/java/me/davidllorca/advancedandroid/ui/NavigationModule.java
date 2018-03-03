package me.davidllorca.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import me.davidllorca.advancedandroid.lifecycle.ActivityLifecycleTask;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@Module
public abstract class NavigationModule {

    // When a object injects ScreenNavigator they will be given a DefaultScreenNavigator.
    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);

}
