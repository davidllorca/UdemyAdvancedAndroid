package me.davidllorca.advancedandroid.lifecycle;

import android.view.View;

import me.davidllorca.advancedandroid.di.ActivityScope;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public abstract class ScreenLifeCycleTask {

    /**
     * Callback received when a Screen becomes the visible screen.
     */
    public void onEnterScope(View view) {

    }

    /**
     * Callback received when a Screen is either popped of moved to the back stack.
     */
    public void onExitScope() {

    }

    /**
     * Callback received when a Screen is destroyed and will not be coming back (except as a new
     * instance). This should be used to clear any{@link ActivityScope} connections (Disposables,
     * etc).
     */
    public void onDestroy() {

    }

}
