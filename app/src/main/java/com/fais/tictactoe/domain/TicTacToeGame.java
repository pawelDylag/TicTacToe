package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.util.Log;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.PlayerFactory;
import com.fais.tictactoe.R;
import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;
import com.fais.tictactoe.utilities.Util;
import com.fais.tictactoe.interfaces.OutputProvider;

import com.fais.tictactoe.interfaces.GameEngine;


/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeGame {

    private static final String TAG = TicTacToeGame.class.getSimpleName();

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;
    private OutputProvider outputProvider;
    private int boardSize;

    public TicTacToeGame(int firstPlayer, int secondPlayer, int boardSize, OutputProvider outputProvider) {
        // set boardsize
        this.boardSize = boardSize;
        // init board manager
        this.boardManager = new TicTacToeBoardManager(boardSize);
        // set output provider
        this.outputProvider = outputProvider;
        // create player objects
        PlayerFactory playerFactory = new PlayerFactory();
        this.playerManager = new TicTacToePlayerManager(this, playerFactory.getPlayer(firstPlayer, this),
                playerFactory.getPlayer(secondPlayer, this));
        // set game engine
        this.gameEngine = new TicTacToeGameEngine(this);
    }

    public void start() {
        gameEngine.startGame();
    }

    public void onBoardClick(int position) {
        Log.d(TAG, "onBoardClick() : playerType = " + playerManager.currentPlayer().getPlayerType());
        // check if not AI turn is in progress
        int playerType = playerManager.currentPlayer().getPlayerType();
        if (playerType == Parameters.PLAYER_HUMAN || playerType == Parameters.PLAYER_SECOND_HUMAN) {
            // convert click position from 1d to 2d
            Point point = Util.convert1DIndexTo2D(position, boardSize);
            gameEngine.onBoardClick(point);
        }
    }

    public void onExitGame() {
        gameEngine.onExitGame();
    }

    public void onPause() {
        gameEngine.onPause();
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public OutputProvider getOutputProvider() {
        return outputProvider;
    }
}
