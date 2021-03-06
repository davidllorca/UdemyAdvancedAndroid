package me.davidllorca.advancedandroid.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.udemyadvancedandroid.R;
import timber.log.Timber;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/02/18.
 */

@ScreenScope
        // We want this ViewModel to be retained as long as the screen is in the scope. Always
        // return the same instance.
class TrendingReposViewModel {

    // Relays are Observables and also Consumers.
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Action reposUpdated() {
        return () -> errorRelay.accept(-1);
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }

}
