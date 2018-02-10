package me.davidllorca.advancedandroid.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

@AutoValue
public abstract class User {

    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }

    public abstract long id();

    public abstract String login();
}
