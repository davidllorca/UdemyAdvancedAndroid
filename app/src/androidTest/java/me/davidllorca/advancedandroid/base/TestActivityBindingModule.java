package me.davidllorca.advancedandroid.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import me.davidllorca.advancedandroid.home.MainActivity;
import me.davidllorca.advancedandroid.home.TestMainActivityComponent;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */
@Module(subcomponents = TestMainActivityComponent.class)
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector
            (TestMainActivityComponent.Builder builder);

}
