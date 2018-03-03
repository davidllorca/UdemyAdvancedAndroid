package me.davidllorca.advancedandroid.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.di.Injector;
import me.davidllorca.advancedandroid.di.ScreenInjector;
import me.davidllorca.advancedandroid.lifecycle.ActivityLifecycleTask;
import me.davidllorca.advancedandroid.ui.ActivityViewInterceptor;
import me.davidllorca.advancedandroid.ui.RouterProvider;
import me.davidllorca.advancedandroid.ui.ScreenNavigator;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements RouterProvider {

    // Retain component across configuration changes
    private static final String INSTANCE_ID_KEY = "instance_id";

    @Inject ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;
    @Inject
    ActivityViewInterceptor activityViewInterceptor;
    @Inject
    Set<ActivityLifecycleTask> activityLifecycleTasks;

    private String instanceId;
    private Router router; // Analog of FragmentManager

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);

        activityViewInterceptor.setContentView(this, layoutRes());
        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if(screenContainer == null){
            throw new NullPointerException("Activity must have a view with id: screen_container");
        }
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        monitorBackStack();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onCreate(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onStart(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onPause(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onStope(this);
        }
    }

    @Override
    public Router getRouter() {
        return router;
    }

    @LayoutRes
    protected abstract int layoutRes();

    // Abstract method that provides the initial controller.
    // All subclasses will have to say what their route controller is.
    public abstract Controller initialScreen();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    @Override
    public void onBackPressed() {
        if (!screenNavigator.pop()) {
            super.onBackPressed();
        }
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            Injector.clearComponent(this);
        }
        activityViewInterceptor.clear();
        for (ActivityLifecycleTask task : activityLifecycleTasks) {
            task.onDestroy(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {
                if(!isPush && from != null){
                    Injector.clearComponent(from);
                }

            }
        });
    }

}
