package me.davidllorca.advancedandroid.base;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import me.davidllorca.advancedandroid.di.ForScreen;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;
import me.davidllorca.advancedandroid.lifecycle.ScreenLifeCycleTask;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifeCycleTask> screenLifeCycleTaskS();
}
