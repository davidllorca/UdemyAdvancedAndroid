package me.davidllorca.advancedandroid.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import me.davidllorca.advancedandroid.base.TestApplication;
import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.data.TestRepoService;
import me.davidllorca.advancedandroid.ui.TestScreenNavigator;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 24/02/18.
 */

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoService = TestApplication.getComponent().repoService();
        repoRepository = TestApplication.getComponent().repoRepository();
    }

    // needed for the next test
    public void clearState() {
        repoService.clearErrorFlags();
        repoService.clearHoldFlags();
        repoRepository.clearCache();
    }

}
