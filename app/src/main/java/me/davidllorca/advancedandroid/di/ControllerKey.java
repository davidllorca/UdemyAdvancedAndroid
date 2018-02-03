package me.davidllorca.advancedandroid.di;

import android.support.annotation.MainThread;

import com.bluelinelabs.conductor.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/02/18.
 */

@MapKey
@Target(ElementType.METHOD)
public @interface ControllerKey {

    Class<? extends Controller> value();
}
