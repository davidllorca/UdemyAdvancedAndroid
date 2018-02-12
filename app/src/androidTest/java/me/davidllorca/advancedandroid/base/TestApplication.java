package me.davidllorca.advancedandroid.base;

import android.support.test.InstrumentationRegistry;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

public class TestApplication extends MyApplication {

    public static TestApplicationComponent getComponent() {
        return (TestApplicationComponent)
                ((TestApplication) InstrumentationRegistry.getTargetContext()
                        .getApplicationContext()).component;


    }

    @Override
    protected ApplicationComponent initComponent() {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
