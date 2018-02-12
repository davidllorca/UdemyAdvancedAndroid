package me.davidllorca.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.davidllorca.advancedandroid.di.ControllerKey;
import me.davidllorca.advancedandroid.trending.TrendingReposComponent;
import me.davidllorca.advancedandroid.trending.TrendingReposController;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */
@Module(subcomponents = TrendingReposComponent.class)
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector
            (TrendingReposComponent.Builder builder);
}
