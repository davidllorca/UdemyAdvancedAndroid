package me.davidllorca.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;
import me.davidllorca.advancedandroid.data.RepoServiceModule;
import me.davidllorca.advancedandroid.networking.ServiceModule;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */
// Component is the object that injects classes. The component is built with all dependencies of
// its own scope and any scopes that it's building on top of. We defined the interface and Dagger
// comes and generated the implementation and we will be looking.

// Lifecycle will be the lifecycle of the app. Because of the we use Singleton annotation.
    // We have to say which module we are going to use.
// ActivityBindingModule.class -> This things will be available in our application scope.
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class
})
public interface ApplicationComponent {

    void inject(MyApplication application);
}
