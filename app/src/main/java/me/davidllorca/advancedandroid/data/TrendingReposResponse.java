package me.davidllorca.advancedandroid.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import me.davidllorca.advancedandroid.model.Repo;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@AutoValue
public abstract class TrendingReposResponse {

    public static JsonAdapter<TrendingReposResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_TrendingReposResponse.MoshiJsonAdapter(moshi);
    }

    @Json(name = "items")
    public abstract List<Repo> repos();

}
