package me.davidllorca.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 24/02/18.
 */
@Module
public abstract class TestNavigationModule {

    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);
}
