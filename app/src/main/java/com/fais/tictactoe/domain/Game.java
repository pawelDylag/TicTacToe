package com.fais.tictactoe.domain;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fais.tictactoe.Data.Player;

import com.fais.tictactoe.R;
import com.fais.tictactoe.Util;
import com.fais.tictactoe.interfaces.OutputProvider;

import com.fais.tictactoe.interfaces.GameEngine;


/**
 * Created by paweldylag on 20/10/15.
 */
public class Game{

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    private int boardSize;

    private OutputProvider outputProvider;

    public Game(Player firstPlayer, Player secondPlayer, int boardSize) {
        this.boardSize = boardSize;
        this.playerManager = new PlayerManager(firstPlayer, secondPlayer);
    }

    public void setOutputProvider(OutputProvider outputProvider) {
        this.outputProvider = outputProvider;
    }

    public void start() {
        gameEngine.startGame();
    }

    public void onBoardClick(int position) {
        // convert click position from 1d to 2d
        Point point = Util.convert1DIndexTo2D(position, boardSize);
        // check if move is possible
        boolean isMovePossible = gameEngine.onBoardClick(point);
        if (isMovePossible) {
            // draw on board
            // TODO: PRZEKAZYWAC IKONE AKTUALNEGO GRACZA DO NARYSOWANIA
            outputProvider.drawOnBoard(point.x, point.y, R.drawable.board_player_1_thumbnail);
        }
    }


    public void onExitGame() {
        gameEngine.onExitGame();
    }

    public void onPause() {
        gameEngine.onPause();
    }
}
