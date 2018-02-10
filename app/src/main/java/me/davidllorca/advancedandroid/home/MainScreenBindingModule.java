package me.davidllorca.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.davidllorca.advancedandroid.di.ControllerKey;
import me.davidllorca.advancedandroid.trending.TrendingReposComponent;
import me.davidllorca.advancedandroid.trending.TrendingReposController;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/02/18.
 */

// This module is going to be part of activity scope because we're building our screen scope on top of the activity scope.
@Module(subcomponents = {
        TrendingReposComponent.class
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);
    
}
