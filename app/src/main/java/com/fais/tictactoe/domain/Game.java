package com.fais.tictactoe.domain;

import android.graphics.Point;

import com.fais.tictactoe.data.Player;

import com.fais.tictactoe.interfaces.OutputProvider;

import com.fais.tictactoe.interfaces.GameEngine;


/**
 * Created by paweldylag on 20/10/15.
 */
public class Game{

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    private Player firstPlayer;
    private Player secondPlayer;

    private int boardSize;

    private OutputProvider outputProvider;

    public Game(Player firstPlayer, Player secondPlayer, int boardSize, OutputProvider outputProvider) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.boardSize = boardSize;
        this.outputProvider = outputProvider;
    }

    public void start() {
        gameEngine.startGame();
    }

    public void onBoardClick(Point point) {
        gameEngine.onBoardClick(point);
    }

    public void onExitGame() {
        gameEngine.onExitGame();
    }

    public void onPause() {
        gameEngine.onPause();
    }
}
