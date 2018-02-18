package me.davidllorca.advancedandroid.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.advancedandroid.testutils.TestUtils;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 17/02/18.
 */
public class RepoRepositoryTest {

    @Mock
    Provider<RepoRequester> repoRequesterProvider;
    @Mock
    RepoRequester repoRequester;

    private RepoRepository repository;
    private TrendingReposResponse trendingReposResponse;
    private Repo rxJavaRepo; // It's the first repo in our fake response.
    private Repo otherRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(repoRequesterProvider.get()).thenReturn(repoRequester);

        trendingReposResponse = TestUtils.loadJson("mock/get_trending_repos.json",
                TrendingReposResponse.class);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(trendingReposResponse.repos
                ()));

        rxJavaRepo = trendingReposResponse.repos().get(0);
        otherRepo = trendingReposResponse.repos().get(1);

        repository = new RepoRepository(repoRequesterProvider, Schedulers.trampoline());
    }

    @Test
    public void getTrendingRepos() throws Exception {
        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());

        // Create a different list and have the API call return that on subsequent calls
        List<Repo> modifiedList = new ArrayList<>(trendingReposResponse.repos());
        modifiedList.remove(0);
        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(modifiedList));

        // Verify we still get the cached list rather than the different API response
        repository.getTrendingRepos().test().assertValue(trendingReposResponse.repos());
    }

    @Test
    public void getRepo() throws Exception {
        // Load trending repos to mimic most likely state of app
        repository.getTrendingRepos().subscribe(); // This initialize our repository cache.

        // Change requester to return a different repo if ever invoked
        when(repoRequester.getRepos(anyString(), anyString())).thenReturn(Single.just(otherRepo));

        // Verify we still get the RxJava repo, which is cached from our trending repos call above
        repository.getRepo("ReactiveX", "RxJava").test().assertValue(rxJavaRepo);
        // Fetch a repo that would not be in the cache and verify the API result is returned
        repository.getRepo("NotInCache", "NotInCache").test().assertValue(otherRepo);
    }

}