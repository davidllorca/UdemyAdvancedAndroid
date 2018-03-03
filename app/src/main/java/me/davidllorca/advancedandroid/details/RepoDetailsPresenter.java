package me.davidllorca.advancedandroid.details;

import javax.inject.Inject;
import javax.inject.Named;

import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.di.ForScreen;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 17/02/18.
 */

@ScreenScope
public class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailViewModel viewModel,
            @ForScreen DisposableManager disposableManager) {
        disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                }));
    }
}
