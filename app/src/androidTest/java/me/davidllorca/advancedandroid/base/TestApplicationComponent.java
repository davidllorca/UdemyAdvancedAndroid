package me.davidllorca.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;
import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.data.TestRepoService;
import me.davidllorca.advancedandroid.data.TestRepoServiceModule;
import me.davidllorca.advancedandroid.networking.ServiceModule;
import me.davidllorca.advancedandroid.ui.TestActivityViewInterceptorModule;
import me.davidllorca.advancedandroid.ui.TestNavigationModule;
import me.davidllorca.advancedandroid.ui.TestScreenNavigator;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
        TestActivityViewInterceptorModule.class
})
public interface TestApplicationComponent extends ApplicationComponent {

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();

}

