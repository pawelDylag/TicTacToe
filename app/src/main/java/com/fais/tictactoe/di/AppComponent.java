package com.fais.tictactoe.di;

import android.content.Context;

import com.fais.tictactoe.dagger.PerApp;
import com.fais.tictactoe.presentation.activities.BaseActivity;

import dagger.Component;

/**
 * Created by paweldylag on 18/10/15.
 * Komponent do skladania modulu aplikacji
 */

@PerApp
@Component(
        modules = {
            AppModule.class
        }
)

public interface AppComponent {

    void inject (BaseActivity activity);

    Context provideAppContext();

}
