package com.fais.tictactoe.domain;

import android.content.Context;
import android.graphics.Point;
import android.widget.GridView;

import com.fais.tictactoe.data.Player;

import com.fais.tictactoe.interfaces.OutputProvider;

import com.fais.tictactoe.interfaces.GameEngine;
import com.fais.tictactoe.presentation.AndroidOutputProvider;


/**
 * Created by paweldylag on 20/10/15.
 */
public class Game{

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    private int boardSize;

    private OutputProvider outputProvider;

    public Game(Context context, GridView gridView, Player firstPlayer, Player secondPlayer, int boardSize) {
        this.boardSize = boardSize;
        this.playerManager = new PlayerManager(firstPlayer, secondPlayer);
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
