package com.fais.tictactoe.di;

import android.app.Application;
import android.content.Context;

import com.fais.tictactoe.dagger.PerApp;
import com.fais.tictactoe.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by paweldylag on 18/10/15.
 * This is main aplication module. It provides access to objects
 * that will live during the application lifecycle.
 */
@Module
final class AppModule {
    private final Application application;

    AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @PerApp
    Context provideAppContext() {
        return this.application;
    }

    @Provides @PerApp
    Navigator provideNavigator() {
        return new Navigator();
    }

}
