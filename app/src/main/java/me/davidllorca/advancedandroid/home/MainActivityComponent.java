package me.davidllorca.advancedandroid.home;

import java.util.jar.Manifest;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.di.ActivityScope;
import me.davidllorca.udemyadvancedandroid.MainActivity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

@ActivityScope
@Subcomponent(modules = {
        MainScreenBIndingModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{};
}
