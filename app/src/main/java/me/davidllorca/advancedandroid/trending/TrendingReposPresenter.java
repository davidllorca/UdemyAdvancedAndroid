package me.davidllorca.advancedandroid.trending;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.model.Repo;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/02/18.
 */

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;

    @Inject
    public TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRepository repoRepository) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        loadRepos();
    }

    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false)) //success fail
                // events.
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }


    @Override
    public void onRepoClicked(Repo repo) {
        // TODO OPEN DETAIL SCREEN
    }
}
