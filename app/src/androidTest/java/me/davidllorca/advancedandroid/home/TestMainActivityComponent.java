package me.davidllorca.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.di.ActivityScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@ActivityScope
@Subcomponent(modules = {
        TestScreenBindingModule.class
})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
