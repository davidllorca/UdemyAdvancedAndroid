package me.davidllorca.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@Module
public abstract class NavigationModule {

    // When a object injects ScreenNavigator they will be given a DefaultScreenNavigator.
    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
