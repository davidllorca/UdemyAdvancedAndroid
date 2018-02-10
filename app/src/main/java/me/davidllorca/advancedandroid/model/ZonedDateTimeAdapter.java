package me.davidllorca.advancedandroid.model;

import android.support.annotation.Nullable;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.threeten.bp.ZonedDateTime;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */
// Moshi don't convet this type because is not a primitive type.
public class ZonedDateTimeAdapter {

    @FromJson
    ZonedDateTime fromJson(String json) {
        return ZonedDateTime.parse(json);
    }

    @ToJson
    String toJson(@Nullable ZonedDateTime value) {
        return value != null ? value.toString() : null;
    }

}
