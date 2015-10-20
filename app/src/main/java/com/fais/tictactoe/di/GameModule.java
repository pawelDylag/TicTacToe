package com.fais.tictactoe.di;

import com.fais.tictactoe.domain.BoardManager;
import com.fais.tictactoe.domain.Game;
import com.fais.tictactoe.domain.GameEngine;
import com.fais.tictactoe.domain.PlayerManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by paweldylag on 20/10/15.
 */
@Module
public class GameModule {

    public GameModule(GameEngine gameEngine) {
    }

    @Provides
    GameEngine provideGameEngine() {
        return new GameEngine();
    }

    @Provides
    BoardManager provideBoardManager() {
        return new BoardManager();
    }

    @Provides
    PlayerManager providePlayerManager() {
        return new PlayerManager();
    }

}
