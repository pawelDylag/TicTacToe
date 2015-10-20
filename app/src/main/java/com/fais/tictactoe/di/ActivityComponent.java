package com.fais.tictactoe.di;

import android.app.Activity;

import com.fais.tictactoe.dagger.PerActivity;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * Created by paweldylag on 20/10/15.
 */
@PerActivity
@Component(dependencies = AppComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity provideActivity();
}