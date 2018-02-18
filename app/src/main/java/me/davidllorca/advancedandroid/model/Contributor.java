package me.davidllorca.advancedandroid.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@AutoValue
public abstract class Contributor {

    public static JsonAdapter<Contributor> jsonAdapter(Moshi moshi) {
        return new AutoValue_Contributor.MoshiJsonAdapter(moshi);
    }

    public abstract long id();

    public abstract String login();

    @Json(name = "avatar_url")
    public abstract String avatarUrl();

}
