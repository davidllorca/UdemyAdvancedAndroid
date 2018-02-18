package me.davidllorca.advancedandroid.details;

import com.jakewharton.rxrelay2.BehaviorRelay;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.davidllorca.advancedandroid.di.ScreenScope;
import me.davidllorca.advancedandroid.model.Contributor;
import me.davidllorca.advancedandroid.model.Repo;
import me.davidllorca.udemyadvancedandroid.R;
import timber.log.Timber;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 17/02/18.
 */

@ScreenScope
public class RepoDetailViewModel {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, " +
            "yyyy");

    private BehaviorRelay<RepoDetailState> detailStateRelay = BehaviorRelay.create();
    private BehaviorRelay<ContributorState> contributorStateRelay = BehaviorRelay.create();

    @Inject
    RepoDetailViewModel() {
        detailStateRelay.accept(RepoDetailState.builder().loading(true).build());
        contributorStateRelay.accept(ContributorState.builder().loading(true).build());
    }

    Observable<RepoDetailState> details() {
        return detailStateRelay;
    }

    Observable<ContributorState> contributors() {
        return contributorStateRelay;
    }

    Consumer<Repo> processRepo() {
        return (Repo repo) ->
                detailStateRelay.accept(
                        RepoDetailState.builder()
                                .loading(false)
                                .name(repo.name())
                                .description(repo.description())
                                .createdDate(repo.createdAt().format(DATE_FORMATTER))
                                .updatedDate(repo.updatedAt().format(DATE_FORMATTER))
                                .build()
                );
    }

    Consumer<List<Contributor>> processContributors() {
        return contributors -> contributorStateRelay.accept(
                ContributorState.builder()
                        .loading(false)
                        .contributors(contributors)
                        .build());
    }

    Consumer<Throwable> detailError() {
        return throwable -> {
            Timber.e(throwable, "Error loading repo details");
            detailStateRelay.accept(
                    RepoDetailState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_single_repo)
                            .build()
            );
        };
    }

    Consumer<Throwable> contributorsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading contributors");
            contributorStateRelay.accept(
                    ContributorState.builder()
                            .loading(false)
                            .errorRes(R.string.api_error_contributors)
                            .build()
            );
        };
    }


}
