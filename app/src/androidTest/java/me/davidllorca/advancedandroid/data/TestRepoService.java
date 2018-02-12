package me.davidllorca.advancedandroid.data;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.davidllorca.advancedandroid.test.TestUtils;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@Singleton
public class TestRepoService implements RepoService {

    private final TestUtils testUtils;
    private boolean sendError;

    @Inject
    TestRepoService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    @Override
    public Single<TrendingReposResponse> getTrendingRepos() {
        if (!sendError) {
            TrendingReposResponse response = testUtils.loadJson("mock/get_trending_repos.json",
                    TrendingReposResponse.class);
            return Single.just(response);
        }
        return Single.error(new IOException());
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }
}
