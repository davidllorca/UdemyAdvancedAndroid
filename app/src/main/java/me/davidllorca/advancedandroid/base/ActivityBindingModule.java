package me.davidllorca.advancedandroid.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.davidllorca.advancedandroid.home.MainActivity;
import me.davidllorca.advancedandroid.home.MainActivityComponent;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

// Module composed of subcomponents
// In case of this app which is going to be a single activity application there's only going to
// be one subcomponent in here and one method.
@Module(subcomponents = {MainActivityComponent.class})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector
            (MainActivityComponent.Builder builder);

}
