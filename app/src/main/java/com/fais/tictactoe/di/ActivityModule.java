package com.fais.tictactoe.di;

import android.app.Activity;

import com.fais.tictactoe.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by paweldylag on 20/10/15.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
