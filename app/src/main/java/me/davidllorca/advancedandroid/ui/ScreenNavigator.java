package me.davidllorca.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

public interface ScreenNavigator {

    void initWithRouter(Router router, Controller rootScreen);

    boolean pop();

    void clear();

}
