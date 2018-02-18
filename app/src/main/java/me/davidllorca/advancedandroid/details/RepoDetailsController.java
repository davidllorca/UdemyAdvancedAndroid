package me.davidllorca.advancedandroid.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.davidllorca.advancedandroid.base.BaseController;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 15/02/18.
 */

public class RepoDetailsController extends BaseController {

    static final String REPO_NAME_KEY = "repo_name";
    static final String REPO_OWNER_KEY = "repo_owner";
    @Inject
    RepoDetailViewModel viewModel;
    @Inject
    RepoDetailsPresenter presenter;
    @BindView(R.id.tv_repo_name)
    TextView repoNameText;
    @BindView(R.id.tv_repo_description)
    TextView repoDescriptionText;
    @BindView(R.id.tv_creation_date)
    TextView createdDateText;
    @BindView(R.id.tv_updated_date)
    TextView updatedDateText;
    @BindView(R.id.contributor_list)
    RecyclerView contributorList;
    @BindView(R.id.loading_indicator)
    View detailsLoadingView;
    @BindView(R.id.contributor_loading_indicator)
    View contributorsLoadingView;
    @BindView(R.id.content)
    View contentContainer;
    @BindView(R.id.tv_error)
    TextView errorText;
    @BindView(R.id.tv_contributors_error)
    TextView contributorsErrorText;
    public RepoDetailsController(Bundle bundle) {
        super(bundle);
    }

    public static Controller newInstance(String repoName, String repoOwner) {
        Bundle bundle = new Bundle();
        bundle.putString(REPO_NAME_KEY, repoName);
        bundle.putString(REPO_OWNER_KEY, repoOwner);
        return new RepoDetailsController(bundle);
    }

    @Override
    protected void onViewBound(View view) {
        contributorList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        contributorList.setAdapter(new ContributorAdapter());
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.details()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repoDetailState -> {
                    if (repoDetailState.loading()) {
                        detailsLoadingView.setVisibility(View.VISIBLE);
                        contentContainer.setVisibility(View.GONE);
                        errorText.setVisibility(View.GONE);
                        errorText.setText(null);
                    } else {
                        if (repoDetailState.isSuccess()) {
                            errorText.setText(null);
                        } else {
                            //noinspection ConstantConditions
                            errorText.setText(repoDetailState.errorRes());
                        }
                        detailsLoadingView.setVisibility(View.GONE);
                        contentContainer.setVisibility(repoDetailState.isSuccess() ? View.VISIBLE
                                : View.GONE);
                        errorText.setVisibility(repoDetailState.isSuccess() ? View.GONE : View
                                .VISIBLE);
                        repoNameText.setText(repoDetailState.name());
                        repoDescriptionText.setText(repoDetailState.description());
                        createdDateText.setText(repoDetailState.createdDate());
                        updatedDateText.setText(repoDetailState.updatedDate());
                    }
                }),
                viewModel.contributors()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(contributorState -> {
                    if (contributorState.loading()) {
                        contributorsLoadingView.setVisibility(View.VISIBLE);
                        contributorList.setVisibility(View.GONE);
                        contributorsErrorText.setVisibility(View.GONE);
                        contributorsErrorText.setText(null);
                    } else {
                        contributorsLoadingView.setVisibility(View.GONE);
                        contributorList.setVisibility(contributorState.isSuccess() ? View.VISIBLE
                                : View.GONE);
                        contributorsErrorText.setVisibility(contributorState.isSuccess() ? View
                                .GONE : View.VISIBLE);
                        if (contributorState.isSuccess()) {
                            contributorsErrorText.setText(null);
                            ((ContributorAdapter) contributorList.getAdapter()).setData
                                    (contributorState.contributors());
                        } else {
                            //noinspection ConstantConditions
                            contributorsErrorText.setText(contributorState.errorRes());
                        }
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }

}
