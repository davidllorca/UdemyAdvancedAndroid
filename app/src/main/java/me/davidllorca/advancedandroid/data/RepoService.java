package me.davidllorca.advancedandroid.data;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

public interface RepoService {

    @GET("search/repositories?q=language:java&order=descc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

}
