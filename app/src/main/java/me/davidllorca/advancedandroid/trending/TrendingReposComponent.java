package me.davidllorca.advancedandroid.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.di.ScreenScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/02/18.
 */

@ScreenScope
@Subcomponent
public interface TrendingReposComponent extends AndroidInjector<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    }
}
