package me.davidllorca.advancedandroid.details;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 17/02/18.
 */

@AutoValue
abstract class RepoDetailState {

    static Builder builder() {
        return new AutoValue_RepoDetailState.Builder();
    }

    abstract boolean loading();

    @Nullable
    abstract String name();

    @Nullable
    abstract String description();

    @Nullable
    abstract String createdDate();

    @Nullable
    abstract String updatedDate();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder name(String name);

        abstract Builder description(String description);

        abstract Builder createdDate(String createdDate);

        abstract Builder updatedDate(String updatedDate);

        abstract Builder errorRes(Integer errorRes);

        abstract RepoDetailState build();

    }
}
