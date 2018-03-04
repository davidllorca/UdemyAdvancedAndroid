package me.davidllorca.advancedandroid.details;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 17/02/18.
 */

@AutoValue
abstract class ContributorState {

    static Builder builder() {
        return new AutoValue_ContributorState.Builder();
    }

    abstract boolean loading();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder errorRes(Integer errorRes);

        abstract ContributorState build();

    }
}
