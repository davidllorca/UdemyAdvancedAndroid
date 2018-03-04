package me.davidllorca.advancedandroid.trending;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.poweradapter.item.ItemRenderer;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 4/03/18.
 */

class RepoRenderer implements ItemRenderer<Repo> {

    private Provider<TrendingReposPresenter> presenterProvider;

    @Inject
    RepoRenderer(Provider<TrendingReposPresenter> presenterProvider) {
        this.presenterProvider = presenterProvider;
    }

    @Override
    public int layoutRes() {
        return R.layout.view_repo_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, presenterProvider.get()));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull Repo item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder {

        @BindView(R.id.tv_repo_name)
        TextView repoNameText;
        @BindView(R.id.tv_repo_description)
        TextView repoDescriptionText;
        @BindView(R.id.tv_fork_count)
        TextView forkCountText;
        @BindView(R.id.tv_star_count)
        TextView starCountText;

        private Repo repo;

        ViewBinder(View itemView, TrendingReposPresenter presenter) {
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (repo != null) {
                    presenter.onRepoClicked(repo);
                }
            });
        }

        void bind(Repo repo) {
            this.repo = repo;
            repoNameText.setText(repo.name());
            repoDescriptionText.setText(repo.description());
            forkCountText.setText(NumberFormat.getInstance().format(repo.forksCount()));
            starCountText.setText(NumberFormat.getInstance().format(repo.stargazersCount()));
        }
    }
}
