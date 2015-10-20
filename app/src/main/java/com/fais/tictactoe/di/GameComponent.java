package com.fais.tictactoe.di;

import com.fais.tictactoe.domain.BoardManager;
import com.fais.tictactoe.domain.Game;
import com.fais.tictactoe.domain.GameEngine;
import com.fais.tictactoe.domain.PlayerManager;

import dagger.Component;

/**
 * Created by paweldylag on 20/10/15.
 */

@Component (modules = GameModule.class)
public interface GameComponent {
    void inject(GameEngine gameEngine);
    void inject(BoardManager boardManager);
    void inject(PlayerManager playerManager);
}
