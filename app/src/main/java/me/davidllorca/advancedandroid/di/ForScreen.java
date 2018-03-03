package me.davidllorca.advancedandroid.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForScreen {
}
