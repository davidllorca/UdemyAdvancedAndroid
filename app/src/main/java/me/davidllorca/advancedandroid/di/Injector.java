package me.davidllorca.advancedandroid.di;

import android.app.Activity;

import com.bluelinelabs.conductor.Controller;

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

    public static void inject(Controller controller){
        ScreenInjector.get(controller.getActivity()).inject(controller);
    }

    public static void clearComponent(Controller controller){
        ScreenInjector.get(controller.getActivity()).clear(controller);
    }

}
