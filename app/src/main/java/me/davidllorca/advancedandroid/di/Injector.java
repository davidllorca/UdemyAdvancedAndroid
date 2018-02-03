package me.davidllorca.advancedandroid.di;

import android.app.Activity;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

public class Injector {

    public Injector() {
    }

    public static void inject(Activity activity){
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }
}
