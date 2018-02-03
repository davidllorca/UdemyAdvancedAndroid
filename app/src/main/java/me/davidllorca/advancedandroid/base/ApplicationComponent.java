package me.davidllorca.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 1/02/18.
 */

@Singleton
@Component(modules ={
        ApplicationModule.class,
        ActivityBindingModule.class
})
public interface ApplicationComponent {

    void inject(MyApplication application);
}
