package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.presentation.InputProvider;
import com.fais.tictactoe.presentation.OutputProvider;

import javax.inject.Inject;

/**
 * Created by paweldylag on 20/10/15.
 */
public class Game  {

    @Inject GameEngine gameEngine;

    private Player firstPlayer;
    private Player secondPlayer;
    private int boardSize;
    private InputProvider inputProvider;
    private OutputProvider outputProvider;

    public Game(Player firstPlayer, Player secondPlayer, int boardSize) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.boardSize = boardSize;
    }

    public void start() {
        //gameEngine.startGame()
    }
}
