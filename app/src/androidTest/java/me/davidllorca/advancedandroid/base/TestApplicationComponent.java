package me.davidllorca.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;
import me.davidllorca.advancedandroid.data.TestRepoServiceModule;
import me.davidllorca.advancedandroid.networking.ServiceModule;
import me.davidllorca.advancedandroid.trending.TrendingReposControllerTest;
import me.davidllorca.advancedandroid.ui.NavigationModule;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class, // In /main makes sense have this module one level down(with
        // Activities level dependency)
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(TrendingReposControllerTest trendingReposControllerTest);
}
