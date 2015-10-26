package com.fais.tictactoe.domain;

import android.graphics.Point;

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

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;
    private int boardSize;
    private OutputProvider outputProvider;
    private int firstPlayer;
    private int secondPlayer;

    public TicTacToeGame(int firstPlayer, int secondPlayer, int boardSize, OutputProvider outputProvider) {
        this.boardSize = boardSize;
        this.boardManager = new TicTacToeBoardManager();
        this.boardManager.setBoardSize(boardSize);
        setPlayers(firstPlayer, secondPlayer);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.outputProvider = outputProvider;

        this.gameEngine = new TicTacToeGameEngine(playerManager, boardManager, outputProvider);
    }


    public void setPlayers(int firstPlayer, int secondPlayer) {
        PlayerFactory playerFactory = new PlayerFactory();
        this.playerManager = new TicTacToePlayerManager(playerFactory.getPlayer(firstPlayer),
                playerFactory.getPlayer(secondPlayer));
        this.playerManager.passBoardManager(this.boardManager);
        this.playerManager.passPlayerManager();
    }

    public void start() {
        gameEngine.startGame();
        int startingPlayer = gameEngine.setInitialPlayer();
        if(startingPlayer == 0)
            outputProvider.displayMessage("Player 1 turn");
        else if (startingPlayer == 1)
            outputProvider.displayMessage("Player 2 turn");
    }

    public void onBoardClick(int position) {
        // convert click position from 1d to 2d
        Point point = Util.convert1DIndexTo2D(position, boardSize);


        // sets next player move
        int playerMove = gameEngine.onBoardClick(point, firstPlayer, secondPlayer);

        if (playerMove == 0) {
            // draw on board
            // TODO: PRZEKAZYWAC IKONE AKTUALNEGO GRACZA DO NARYSOWANIA
            outputProvider.drawOnBoard(point.x, point.y, R.drawable.board_player_1_thumbnail);
        } else if (playerMove == 1) {
            outputProvider.drawOnBoard(point.x, point.y, R.drawable.board_player_2_thumbnail);
        }
    }


    public void onExitGame() {
        gameEngine.onExitGame();
    }

    public void onPause() {
        gameEngine.onPause();
    }
}
