package me.davidllorca.advancedandroid.data;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@Module
public abstract class TestRepoServiceModule {

    @Provides
    @Named("network_scheduler")
    static Scheduler provideNetworkScheduler() {
        return Schedulers.trampoline(); // RUn in current thread
    }

    @Binds
    abstract RepoService bindRepoService(TestRepoService repoService);
}
