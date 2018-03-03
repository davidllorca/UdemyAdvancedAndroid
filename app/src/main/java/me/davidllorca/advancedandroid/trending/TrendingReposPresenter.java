package me.davidllorca.advancedandroid.trending;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.di.ForScreen;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.advancedandroid.ui.ScreenNavigator;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/02/18.
 */

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private ScreenNavigator screenNavigator;
    private DisposableManager disposableManager;

    @Inject
    public TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        loadRepos();
    }

    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false)) //success fail
                // events.
                .subscribe(viewModel.reposUpdated(), viewModel.onError()));
    }


    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
