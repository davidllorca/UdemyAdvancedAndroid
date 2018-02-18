package me.davidllorca.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.details.RepoDetailsController;
import me.davidllorca.advancedandroid.di.ActivityScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        // The router gets information from saved instance state, so it knows if it needs to
        // recreate a back stack or not. hasRootController() is telling us if the router has ever
        // been initialized with the controller yet. If it hasn't then this is the first time
        // that this router has been used.
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        // This will return true if the router handled the back request. Could be false if back
        // stack only has one item.
        return router != null && router.handleBack();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        if (router != null) {
            router.pushController(RouterTransaction.with(RepoDetailsController.newInstance
                    (repoName, repoOwner))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void clear() {
        router = null;
    }
}
