package com.fais.tictactoe.domain;

import android.graphics.Point;

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

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    private int boardSize;

    private OutputProvider outputProvider;

    public TicTacToeGame(int firstPlayer, int secondPlayer, int boardSize) {
        this.boardSize = boardSize;
        this.gameEngine = new TicTacToeGameEngine();
        this.boardManager = new TicTacToeBoardManager();
        setPlayers(firstPlayer, secondPlayer);
    }

    public void setOutputProvider(OutputProvider outputProvider) {
        this.outputProvider = outputProvider;
    }

    public void setPlayers(int firstPlayer, int secondPlayer) {
        PlayerFactory playerFactory = new PlayerFactory();
        this.playerManager = new TicTacToePlayerManager(playerFactory.getPlayer(firstPlayer),
                                                    playerFactory.getPlayer(secondPlayer));
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
