package me.davidllorca.advancedandroid.data;

import dagger.Binds;
import dagger.Module;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 12/02/18.
 */

@Module
public abstract class TestRepoServiceModule {

    @Binds
    abstract RepoService bindRepoService(TestRepoService repoService);
}
