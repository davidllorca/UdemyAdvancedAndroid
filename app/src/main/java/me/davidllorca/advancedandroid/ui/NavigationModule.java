package me.davidllorca.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import me.davidllorca.advancedandroid.di.ActivityScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@Module
public abstract class NavigationModule {

    // When a object injects ScreenNavigator they will be given a DefaultScreenNavigator.
    @Binds
    @ActivityScope // Try yo avoid this. It's just to use ScreenNavigator in our test
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);
}
