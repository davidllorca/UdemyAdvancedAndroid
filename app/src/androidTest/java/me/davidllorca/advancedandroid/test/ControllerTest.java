package me.davidllorca.advancedandroid.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;

import org.junit.Rule;

import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.data.TestRepoService;
import me.davidllorca.advancedandroid.home.MainActivity;
import me.davidllorca.advancedandroid.ui.TestScreenNavigator;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 24/02/18.
 */

public abstract class ControllerTest {

    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestRepoService repoService = testRule.repoService;
    protected RepoRepository repoRepository = testRule.repoRepository;
    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;

    public ControllerTest() {
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    private void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}
