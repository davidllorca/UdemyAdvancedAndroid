package me.davidllorca.advancedandroid.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.di.ActivityScope;
import me.davidllorca.udemyadvancedandroid.MainActivity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        @Override
        public void seedInstance(MainActivity instance) {
            // Overrriding we avoid inject Activity anywhere
        }
    }
}
