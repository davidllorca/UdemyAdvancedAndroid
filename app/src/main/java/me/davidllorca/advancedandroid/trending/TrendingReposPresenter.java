package me.davidllorca.advancedandroid.trending;

import javax.inject.Inject;

import me.davidllorca.advancedandroid.data.RepoRequester;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.model.Repo;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/02/18.
 */

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRequester repoRequester;

    @Inject
    public TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
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
