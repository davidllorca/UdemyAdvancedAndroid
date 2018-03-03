package me.davidllorca.advancedandroid.ui;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 10/02/18.
 */

public interface ScreenNavigator {

    boolean pop();

    void goToRepoDetails(String repoOwner, String repoName);

}
