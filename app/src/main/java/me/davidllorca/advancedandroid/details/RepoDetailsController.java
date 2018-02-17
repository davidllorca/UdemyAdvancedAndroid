package me.davidllorca.advancedandroid.details;

import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;

import me.davidllorca.advancedandroid.base.BaseController;
import me.davidllorca.udemyadvancedandroid.R;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 15/02/18.
 */

public class RepoDetailsController extends BaseController {

    static final String REPO_NAME_KEY = "repo_name";
    static final String REPO_OWNER_KEY = "repo_owner";

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
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }

}
