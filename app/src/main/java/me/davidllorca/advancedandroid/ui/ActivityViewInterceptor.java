package me.davidllorca.advancedandroid.ui;

import android.app.Activity;
import android.support.annotation.LayoutRes;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public interface ActivityViewInterceptor {

    ActivityViewInterceptor DEFAULT = new ActivityViewInterceptor() {
        @Override
        public void setContentView(Activity activity, int layoutRes) {
            activity.setContentView(layoutRes);
        }

        @Override
        public void clear() {

        }
    };

    void setContentView(Activity activity, @LayoutRes int layoutRes);

    void clear();
}
