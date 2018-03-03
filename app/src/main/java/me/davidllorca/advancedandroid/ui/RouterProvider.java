package me.davidllorca.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();

}
