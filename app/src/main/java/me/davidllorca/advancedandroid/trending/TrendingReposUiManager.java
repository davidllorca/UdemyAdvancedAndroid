package me.davidllorca.advancedandroid.trending;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.davidllorca.advancedandroid.lifecycle.ScreenLifeCycleTask;
import me.davidllorca.advancedandroid.util.ButterKnifeUtils;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

public class TrendingReposUiManager extends ScreenLifeCycleTask {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    private Unbinder unbinder;

    @Inject
    TrendingReposUiManager() {

    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_trending);
    }

    @Override
    public void onExitScope() {
        ButterKnifeUtils.unbind(unbinder);
    }
}
