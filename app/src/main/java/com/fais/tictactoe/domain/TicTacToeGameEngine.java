package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.util.Log;

import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;

import java.util.Random;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeGameEngine implements com.fais.tictactoe.interfaces.GameEngine {

    private PlayerManager playerManager;
    private BoardManager boardManager;
    private int turnNumber = 0;
    private boolean isGameStarted;
    private boolean playerOneTurn = false;
    private boolean playerTwoTurn = false;

    public TicTacToeGameEngine(PlayerManager playerManager, BoardManager boardManager) {
        this.playerManager = playerManager;
        this.boardManager = boardManager;
    }

    @Override
    public void startGame() {
        isGameStarted = true;
        //mainGameLoop();
    }

    @Override
    public void stopGame() {

    }

    @Override
    public int getCurrentTurnNumber() {
        return turnNumber;
    }

    @Override
    public boolean checkMove(int x, int y) {
        if (boardManager.getAtCoordinates(x, y) != 0)
            return false;
        else if (boardManager.getAtCoordinates(x, y) == 0)
            return true;
        else return false;
    }

    @Override
    public boolean checkEndOfGame(int x, int y, int playerType) {
        int n = boardManager.getBoardSize();
        //check col
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(x, i) != playerType)
                break;
            if (i == n - 1) {
                Log.i("winner column", String.valueOf(playerType));
            }
        }

        //check row
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(i, y) != playerType)
                break;
            if (i == n - 1) {
                Log.i("winner row", String.valueOf(playerType));

            }
        }

        //check diag
        if (x == y) {
            //we're on a diagonal
            for (int i = 0; i < n; i++) {
                if (boardManager.getAtCoordinates(i, i) != playerType)
                    break;
                if (i == n - 1) {
                    Log.i("winner diagonal", String.valueOf(playerType));

                }
            }
        }

        //check anti diag (thanks rampion)
        for (int i = 0; i < n; i++) {
            if (boardManager.getAtCoordinates(i, (n - 1) - i) != playerType)
                break;
            if (i == n - 1) {
                Log.i("winner antidiagonal", String.valueOf(playerType));

            }
        }

        //check draw
        if (boardManager.isFull()) {
            Log.i("draw", "draw");

        }
        return true;
    }

    @Override
    public int setInitialPlayer() {
        Random generator = new Random();
        int i = generator.nextInt(2);
        if (i == 0) {
            playerOneTurn = true;
            return 0;
        } else if (i == 1) {
            playerTwoTurn = true;
            return 1;
        }
        return -1;
    }

    @Override
    public int onBoardClick(Point point, int firstPlayer, int secondPlayer) {
        if (checkMove(point.x, point.y)) {
            turnNumber++;
            if (playerOneTurn && !playerTwoTurn) {
                playerOneTurn = false;
                playerTwoTurn = true;
                boardManager.insertAtCoordinates(point.x, point.y, firstPlayer);
                checkEndOfGame(point.x, point.y, firstPlayer);
                return 0;
            } else if (!playerOneTurn && playerTwoTurn) {
                playerTwoTurn = false;
                playerOneTurn = true;
                boardManager.insertAtCoordinates(point.x, point.y, secondPlayer);
                checkEndOfGame(point.x, point.y, secondPlayer);
                return 1;
            }
        }
        return -1;
    }

    @Override
    public void onExitGame() {

    }

    @Override
    public void onPause() {

    }

    private void mainGameLoop() {


    }


}
