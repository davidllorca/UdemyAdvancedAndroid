package me.davidllorca.advancedandroid.details;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import me.davidllorca.advancedandroid.lifecycle.ScreenLifeCycleTask;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@Module
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifeCycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);
}
