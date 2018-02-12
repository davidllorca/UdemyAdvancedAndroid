package me.davidllorca.advancedandroid.test;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import me.davidllorca.advancedandroid.base.TestApplication;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        // Overrides tis to call TestApplication.class
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}
