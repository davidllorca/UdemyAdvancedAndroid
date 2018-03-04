package me.davidllorca.poweradapter.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */
@MapKey
@Target(ElementType.METHOD)
public @interface RenderKey {

    String value();

}
