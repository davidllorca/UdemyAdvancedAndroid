package me.davidllorca.advancedandroid.trending;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.davidllorca.advancedandroid.data.RepoRepository;
import me.davidllorca.advancedandroid.data.TrendingReposResponse;
import me.davidllorca.advancedandroid.lifecycle.DisposableManager;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.advancedandroid.testutils.TestUtils;
import me.davidllorca.advancedandroid.ui.ScreenNavigator;
import me.davidllorca.poweradapter.adapter.RecyclerDataSource;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */
public class TrendingReposPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers
                .trampoline());
    }

    @Mock
    RepoRepository repoRepository;
    @Mock
    TrendingReposViewModel viewModel;
    @Mock
    Consumer<Throwable> onErrorConsumer;
    @Mock
    Consumer<Boolean> loadingConsumer;
    @Mock
    ScreenNavigator screenNavigator;
    @Mock
    RecyclerDataSource dataSource;

    private TrendingReposPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.reposUpdated()).thenReturn(() -> {
        });
    }

    @Test
    public void reposLoaded() throws Exception {
        List<Repo> repos = setUpSuccess();
        initializePresenter();

        verify(repoRepository).getTrendingRepos();
        verify(dataSource).setData(repos);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void reposLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(dataSource);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingErrors() throws Exception {
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    //Initialize Mock with successful response
    private List<Repo> setUpSuccess() {
        TrendingReposResponse response = TestUtils.loadJson("mock/search/get_trending_repos.json",
                TrendingReposResponse.class);
        List<Repo> repos = response.repos();

        when(repoRepository.getTrendingRepos()).thenReturn(Single.just(repos));

        return repos;
    }

    @Test
    public void onRepoClicked() throws Exception {
        Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
        setUpSuccess();
        initializePresenter();

        presenter.onRepoClicked(repo);

        verify(screenNavigator).goToRepoDetails(repo.owner().login(), repo.name());
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(repoRepository.getTrendingRepos()).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter() {
        presenter = new TrendingReposPresenter(viewModel, repoRepository, screenNavigator,
                Mockito.mock(DisposableManager.class), dataSource);
    }

}