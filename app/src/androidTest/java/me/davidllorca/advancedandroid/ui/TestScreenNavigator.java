package me.davidllorca.advancedandroid.ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.davidllorca.advancedandroid.lifecycle.ActivityLifecycleTask;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 24/02/18.
 */

@Singleton
public class TestScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private final DefaultScreenNavigator defaultScreenNavigator;
    private Controller overrideController;

    @Inject
    TestScreenNavigator() {
        this.defaultScreenNavigator = new DefaultScreenNavigator();
    }

    /**
     * Set the Controller to launch when the Activity attaches to the ScreenNavigator. This will
     * be used instead of the Controller provided by
     * {@link RouterProvider#initialScreen()}
     *
     * @param overrideController Controller to launch when Activity starts. Or null to fall back
     *                           to default.
     */
    public void overrideInitialController(Controller overrideController) {
        this.overrideController = overrideController;
    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        if (!(activity instanceof RouterProvider)) {
            throw new IllegalArgumentException("Activity must be instance of RouteProvider");
        }
        Controller launchController = overrideController == null ? ((RouterProvider) activity)
                .initialScreen() : overrideController;
        defaultScreenNavigator.initWithRouter(((RouterProvider) activity).getRouter(),
                launchController);
    }

    @Override
    public boolean pop() {
        return defaultScreenNavigator.pop();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        defaultScreenNavigator.goToRepoDetails(repoOwner, repoName);
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        defaultScreenNavigator.onDestroy(activity);
    }
}
