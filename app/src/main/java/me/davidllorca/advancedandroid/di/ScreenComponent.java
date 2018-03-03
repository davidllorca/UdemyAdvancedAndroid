package me.davidllorca.advancedandroid.di;

import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();

}
