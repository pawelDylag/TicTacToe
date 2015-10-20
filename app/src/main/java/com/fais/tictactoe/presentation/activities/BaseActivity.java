package com.fais.tictactoe.presentation.activities;

import android.app.Activity;
import android.os.Bundle;

import com.fais.tictactoe.di.ActivityModule;
import com.fais.tictactoe.di.App;
import com.fais.tictactoe.di.AppComponent;
import com.fais.tictactoe.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base activity extended by all activities in this app
 * Created by paweldylag on 18/10/15.
 */
public class BaseActivity extends Activity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getAppComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection
     */
    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     **/
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
