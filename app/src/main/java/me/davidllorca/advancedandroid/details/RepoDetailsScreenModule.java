package me.davidllorca.advancedandroid.details;

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
public abstract class RepoDetailsScreenModule {

    @Provides
    @ScreenScope
    static RecyclerDataSource provideDataSource(Map<String, ItemRenderer<? extends RecyclerItem>>
                                                            renderers) {
        return new RecyclerDataSource(renderers);
    }

    @Binds
    @IntoSet
    abstract ScreenLifeCycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(ContributorRenderer renderer);
}
