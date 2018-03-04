package me.davidllorca.advancedandroid.trending;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.ScreenLifeCycleTask;
import me.davidllorca.poweradapter.adapter.RecyclerDataSource;
import me.davidllorca.poweradapter.item.ItemRenderer;
import me.davidllorca.poweradapter.item.RecyclerItem;
import me.davidllorca.poweradapter.item.RenderKey;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@Module
public abstract class TrendingReposScreenModule {

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends
            RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }

    @Binds
    @IntoSet
    abstract ScreenLifeCycleTask bindUiManager(TrendingReposUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Repo")
    abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

}
