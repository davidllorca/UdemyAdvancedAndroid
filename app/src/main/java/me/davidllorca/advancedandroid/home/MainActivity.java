package me.davidllorca.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import me.davidllorca.advancedandroid.base.BaseActivity;
import me.davidllorca.advancedandroid.trending.TrendingReposController;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Controller initialScreen() {
        return new TrendingReposController();
    }
}
