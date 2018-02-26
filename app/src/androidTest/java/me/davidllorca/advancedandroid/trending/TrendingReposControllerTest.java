package me.davidllorca.advancedandroid.trending;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.bluelinelabs.conductor.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.davidllorca.advancedandroid.data.TestRepoService;
import me.davidllorca.advancedandroid.test.ControllerTest;
import me.davidllorca.udemyadvancedandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@RunWith(AndroidJUnit4.class)
public class TrendingReposControllerTest extends ControllerTest {

    // Don't need this because we extend from ControllerTest
//    @Rule
//    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity
//            .class, true, false);
//    @Inject
//    TestRepoService repoService;

//    @Before
//    public void setUp() {
//        TestApplication.getComponent().inject(this);
//    }

    @Test
    public void loadRepos() {
        repoService.clearErrorFlags();
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.GONE)));
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.GONE)));
        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.VISIBLE)));

        onView(allOf(withId(R.id.tv_repo_name), withText("RxJava"))).check(matches
                (withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void loadReposError() {
        repoService.setErrorFlags(TestRepoService.FLAG_TRENDING_REPOS);
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.GONE)));

        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers
                .Visibility.GONE)));

        onView(withId(R.id.tv_error)).check(matches(allOf(withText(R.string.api_error_repos),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));

    }

    @Override
    protected Controller controllerToLaunch() {
        return new TrendingReposController();
    }
}
