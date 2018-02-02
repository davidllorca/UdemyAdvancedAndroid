package me.davidllorca.advancedandroid.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.davidllorca.advancedandroid.home.MainActivityComponent;
import me.davidllorca.udemyadvancedandroid.MainActivity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

@Module(subcomponents = { MainActivityComponent.class })
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);

}
