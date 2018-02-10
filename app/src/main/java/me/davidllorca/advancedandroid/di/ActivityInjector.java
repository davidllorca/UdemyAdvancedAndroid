package me.davidllorca.advancedandroid.di;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.base.BaseActivity;
import me.davidllorca.advancedandroid.base.MyApplication;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */
//(Reimplementation of DispatchingAndroidInjector. That is more generic because it supports fragments, broadcast receivers, activities. This is just used to inject Activities.)
// Inject the map tha we defined in ActivityBindingModule}

    // We didn't need an annotation on the ActivityInjector because it's only going to be injected once when the application is created.
public class ActivityInjector {

    private final Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;
    private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();

    @Inject
    ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors){
        this.activityInjectors = activityInjectors;
    }

    // Before to inject we are going to caching.
    void inject(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw  new IllegalArgumentException("Activity must extends BaseActivity");
        }
        String instanceId = ((BaseActivity)activity).getInstanceId();
        if(cache.containsKey(instanceId)){
            //noinspection unchecked
            ((AndroidInjector<Activity>)cache.get(instanceId)).inject(activity);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Activity> injectorFactory = (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();
        // Now we have a InjectorFactory. We are going to create one.
        AndroidInjector<Activity> injector = injectorFactory.create(activity);
        cache.put(instanceId, injector);
        injector.inject(activity);

    }

    void clear(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }
        cache.remove(((BaseActivity)activity).getInstanceId());
    }

    // static helper method to retrieve the activity injector.
    static ActivityInjector get(Context context) {
        return ((MyApplication) context.getApplicationContext()).getActivityInjector();
    }
}
