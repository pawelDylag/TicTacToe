package com.fais.tictactoe.domain;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.R;
import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.OutputProvider;
import com.fais.tictactoe.interfaces.PlayerManager;
import com.fais.tictactoe.presentation.AndroidOutputProvider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeGameEngine implements com.fais.tictactoe.interfaces.GameEngine {

    private PlayerManager playerManager;
    private BoardManager boardManager;
    private OutputProvider outputProvider;
    private int turnNumber = 0;
    private boolean isGameFinished;
    private boolean playerOneTurn = false;
    private boolean playerTwoTurn = false;

    public TicTacToeGameEngine(PlayerManager playerManager, BoardManager boardManager, OutputProvider outputProvider) {
        this.playerManager = playerManager;
        this.boardManager = boardManager;
        this.outputProvider = outputProvider;
    }

    @Override
    public boolean isGameFinished()
    {
        return isGameFinished;
    }
    
    @Override
    public void startGame() {
        isGameFinished = false;
    }

    @Override
    public void stopGame() {
        isGameFinished = true;
    }

    @Override
    public int getCurrentTurnNumber() {
        return turnNumber;
    }

    /**
     * Checks if clicked cell is occupied. If not it let player do his move.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean checkMove(int x, int y) {
        return (boardManager.getAtCoordinates(x, y) == 0);
    }


    /**
     * Checks all game conditions (columns, raw, (anti)diagonals, draw)
     *
     * @param x
     * @param y
     * @param playerType
     * @return
     */
    @Override
    public boolean checkEndOfGame(int x, int y, int playerType) {
        ArrayList<Point> winnerList = new ArrayList<>();
        if (playerType == Parameters.PLAYER_HUMAN)
            outputProvider.displayMessage("Player 2 turn!");
        else
            outputProvider.displayMessage("Player 1 turn!");

        int n = boardManager.getBoardSize();
        //check col
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(x, i) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerList.add(new Point(x, j));
                }
                outputProvider.displayWinnerCells(winnerList);
                displayWinner(playerType);
                return Parameters.GAME_FINISHED;
            }
        }
        //check row
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(i, y) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerList.add(new Point(j, y));
                }
                outputProvider.displayWinnerCells(winnerList);
                displayWinner(playerType);
                return Parameters.GAME_FINISHED;
            }
        }
        //check diag
        if (x == y) {
            //we're on a diagonal
            for (int i = 0; i < n; i++) {
                if (boardManager.getAtCoordinates(i, i) != playerType)
                    break;
                if (i == n - 1) {
                    for (int j = 0; j < n; j++) {
                        winnerList.add(new Point(j, j));
                    }
                    outputProvider.displayWinnerCells(winnerList);
                    displayWinner(playerType);
                    return Parameters.GAME_FINISHED;
                }
            }
        }

        //check anti diag
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(i, (n - 1) - i) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerList.add(new Point(j, (n - 1) - j));
                }
                outputProvider.displayWinnerCells(winnerList);
                displayWinner(playerType);
                return Parameters.GAME_FINISHED;
            }
        }
        //check draw
        if (boardManager.isFull()) {
            outputProvider.displayMessage("Draw!");
            return Parameters.GAME_FINISHED;
        }
        return !Parameters.GAME_FINISHED;
    }

    /**
     * Sets first player
     *
     * @return
     */
    @Override
    public int setInitialPlayer() {
        Random generator = new Random();
        int i = generator.nextInt(2);
        if (i == 0) {
            playerOneTurn = true;
            return Parameters.FIRST_PLAYER;
        } else if (i == 1) {
            playerTwoTurn = true;
            return Parameters.SECOND_PLAYER;
        }
        return Parameters.ERROR_RESULT;
    }

    /**
     * Return number defines what to draw on a board in TicTacToeGame class
     *
     * @param point        Point clicked on board
     * @param firstPlayer  id
     * @param secondPlayer id
     * @return
     */
    @Override
    public int onBoardClick(Point point, int firstPlayer, int secondPlayer) {
        if (!isGameFinished) {
            if (checkMove(point.x, point.y)) {
                turnNumber++;
                if (playerOneTurn && !playerTwoTurn) {
                    playerOneTurn = false;
                    playerTwoTurn = true;
                    boardManager.insertAtCoordinates(point.x, point.y, firstPlayer);
                    isGameFinished = checkEndOfGame(point.x, point.y, firstPlayer);
                    return Parameters.FIRST_PLAYER;
                } else if (!playerOneTurn && playerTwoTurn) {
                    playerTwoTurn = false;
                    playerOneTurn = true;
                    boardManager.insertAtCoordinates(point.x, point.y, secondPlayer);
                    isGameFinished = checkEndOfGame(point.x, point.y, secondPlayer);
                    return Parameters.SECOND_PLAYER;
                }
            }
        }
        return Parameters.ERROR_RESULT;
    }

    @Override
    public void onExitGame() {

    }

    @Override
    public void onPause() {

    }

    public void displayWinner(int playerType) {
        if (playerType == Parameters.PLAYER_HUMAN)
            outputProvider.displayMessage("Player 1 wins!");
        else
            outputProvider.displayMessage("Player 2 wins!");
    }


}
