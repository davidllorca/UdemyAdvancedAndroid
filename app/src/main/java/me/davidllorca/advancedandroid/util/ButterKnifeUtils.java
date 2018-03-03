package me.davidllorca.advancedandroid.util;

import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class ButterKnifeUtils {

    private ButterKnifeUtils() {

    }

    public static void unbind(Unbinder unbinder) {
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (IllegalStateException e) {
                Timber.e(e, "Error unbinding views");
            }
        }
    }

}
