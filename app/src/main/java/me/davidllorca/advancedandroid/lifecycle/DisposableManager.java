package me.davidllorca.advancedandroid.lifecycle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class DisposableManager {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void add(Disposable... disposables) {
        compositeDisposable.addAll(disposables);
    }

    public void dispose() {
        compositeDisposable.clear();
    }
}
