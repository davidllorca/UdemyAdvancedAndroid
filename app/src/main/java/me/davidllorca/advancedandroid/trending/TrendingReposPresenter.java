package me.davidllorca.advancedandroid.trending;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.di.ForScreen;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.advancedandroid.ui.ScreenNavigator;
import me.davidllorca.poweradapter.adapter.RecyclerDataSource;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/02/18.
 */

@ScreenScope
class TrendingReposPresenter {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private ScreenNavigator screenNavigator;
    private DisposableManager disposableManager;
    private RecyclerDataSource dataSource;

    @Inject
    public TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource dataSource) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;
        loadRepos();
    }

    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false)) //success fail
                // events.
                .doOnSuccess(__ -> viewModel.reposUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
