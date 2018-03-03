package me.davidllorca.advancedandroid.details;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.lifecycle.ScreenLifeCycleTask;
import me.davidllorca.advancedandroid.ui.ScreenNavigator;
import me.davidllorca.advancedandroid.util.ButterKnifeUtils;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 3/03/18.
 */

@ScreenScope
public class RepoDetailsUiManager extends ScreenLifeCycleTask {

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    private Unbinder unbinder;
    private String repoName;
    private ScreenNavigator screenNavigator;


    @Inject
    RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {
        this.repoName = repoName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(repoName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
