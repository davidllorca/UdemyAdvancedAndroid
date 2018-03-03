package me.davidllorca.advancedandroid.details;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import me.davidllorca.advancedandroid.base.ScreenModule;
import me.davidllorca.advancedandroid.di.ScreenComponent;
import me.davidllorca.advancedandroid.di.ScreenScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 15/02/18.
 */

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        RepoDetailsScreenModule.class
})
public interface RepoDetailsComponent extends ScreenComponent<RepoDetailsController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailsController> {

        @BindsInstance
        public abstract void bindRepoOwner(@Named("repo_owner") String repoOwner);

        @BindsInstance
        public abstract void bindRepoName(@Named("repo_name") String repoName);

        @Override
        public void seedInstance(RepoDetailsController instance) {
            bindRepoOwner(instance.getArgs().getString(RepoDetailsController.REPO_OWNER_KEY));
            bindRepoName(instance.getArgs().getString(RepoDetailsController.REPO_NAME_KEY));
        }
    }
}
