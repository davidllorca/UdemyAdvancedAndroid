package me.davidllorca.advancedandroid.data;

import java.util.List;

import io.reactivex.Single;
import me.davidllorca.advancedandroid.model.Contributor;
import me.davidllorca.advancedandroid.model.Repo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

    @GET
    Single<List<Contributor>> getContributors(@Url String url);

}
