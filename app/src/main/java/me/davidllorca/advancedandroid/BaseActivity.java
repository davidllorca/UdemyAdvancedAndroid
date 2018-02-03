package me.davidllorca.advancedandroid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

import me.davidllorca.advancedandroid.di.Injector;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 2/02/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    // Retain component across configuration changes
    private static String INSTANCE_ID_KEY = "instance_id";
    private String instanceId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if(savedInstanceState != null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            Injector.clearComponent(this);
        }
    }
}
