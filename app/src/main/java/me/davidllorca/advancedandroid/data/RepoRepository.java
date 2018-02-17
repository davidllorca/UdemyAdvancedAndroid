package me.davidllorca.advancedandroid.data;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;
import me.davidllorca.advancedandroid.model.Repo;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 15/02/18.
 */

@Singleton
public class RepoRepository {

    private final List<Repo> cachedTrendingRepos = new ArrayList<>();
    private Provider<RepoRequester> repoRequesterProvider;

    @Inject
    public RepoRepository(Provider<RepoRequester> repoRequesterProvider) {
        this.repoRequesterProvider = repoRequesterProvider;
    }

    public Single<List<Repo>> getTrendingRepos() {
        return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
                .firstOrError(); //if cachecTrendingRepos() return item the second call will not
        // happen.
    }

    public Single<Repo> getRepo(String repoOwner, String repoName) {
        return Maybe.concat(cachedRepo(repoOwner, repoName), apiRepo(repoOwner, repoName))
                .firstOrError();
    }

    private Maybe<Repo> cachedRepo(String repoOwner, String repoName) {
        return Maybe.create(e -> {
            for (Repo cachedRepo : cachedTrendingRepos) {
                if (cachedRepo.owner().login().equals(repoOwner) && cachedRepo.name().equals
                        (repoName)) {
                    e.onSuccess(cachedRepo);
                    break;
                }
            }
            e.onComplete();
        });
    }

    private Maybe<Repo> apiRepo(String repoOwner, String reponame) {
        return repoRequesterProvider.get().getRepos(repoOwner, reponame)
                .toMaybe();
    }

    private Maybe<List<Repo>> cachedTrendingRepos() {
        return Maybe.create(e -> {
            if (cachedTrendingRepos.isEmpty()) {
                e.onSuccess(cachedTrendingRepos);
            }
            e.onComplete();
        });
    }

    private Maybe<List<Repo>> apiTrendingRepos() {
        return repoRequesterProvider.get().getTrendingRepos()
                .doOnSuccess(repos -> {
                    cachedTrendingRepos.clear();
                    cachedTrendingRepos.addAll(repos);

                }).toMaybe();
    }

}
