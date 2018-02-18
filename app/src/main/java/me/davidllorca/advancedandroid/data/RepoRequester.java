package me.davidllorca.advancedandroid.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import me.davidllorca.advancedandroid.model.Contributor;
import me.davidllorca.advancedandroid.model.Repo;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service) {
        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos);
    }

    Single<Repo> getRepos(String repoOwner, String repoName) {
        return service.getRepo(repoOwner, repoName);
    }

    Single<List<Contributor>> getContributors(String url) {
        return service.getContributors(url);
    }

}
