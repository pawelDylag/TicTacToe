package com.fais.tictactoe.di;

import android.app.Application;


/**
 * Created by paweldylag on 18/10/15.
 * Klasa rozszerzajaca bazowa klase aplikacji.
 * Trzyma informacje o komponentach.
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        // init injector
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
