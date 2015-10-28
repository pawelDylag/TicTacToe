package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.util.Log;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.Player;

import java.util.ArrayList;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeGameEngine implements com.fais.tictactoe.interfaces.GameEngine {


    private static final String TAG = TicTacToeGameEngine.class.getSimpleName();

    /**
     * Main Game module object
     */
    private TicTacToeGame game;

    /**
     * FLag indicating whether game is in progress or not
     */
    private boolean isGameInProgress;

    /**
     * If finished game - this field contains winning combination
     */
    private ArrayList<Point> winnerPoints = new ArrayList<>();

    /**
     * How many cells player needs in row etc to win
     */
    private int numberOfCellsToWin;

    public TicTacToeGameEngine(TicTacToeGame game) {
        this.game = game;
        numberOfCellsToWin = getWinConditions(game.getBoardManager().getBoardSize());
    }

    @Override
    public void startGame() {
        isGameInProgress = true;
        game.getPlayerManager().currentPlayer().onTurnReceived();
    }

    @Override
    public void stopGame() {
        isGameInProgress = false;
    }

    /**
     * Checks if clicked cell is occupied. If not it let player do his move.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean checkMoveIsPossible(int x, int y) {
        return (game.getBoardManager().getAtCoordinates(x, y) == 0);
    }


    /**
     * Checks all game conditions (columns, raw, (anti)diagonals, draw)
     *
     * @param x                  - last cell clicked x
     * @param y                  - last cell clicked y
     * @param numberOfCellsToWin - parameter indicating end of game condition
     * @param playerType         - last cell clicked player type
     * @return
     */
    @Override
    public int checkEndOfGame(int x, int y, int numberOfCellsToWin, int playerType) {

        Log.d("TicTacToeGameEngine", "checkEndOfGame() : for playerType = " + playerType);
        int n = game.getBoardManager().getBoardSize();

        if (checkColumn(x, playerType, n))
            return Parameters.GAME_RESULT_FINISHED;
        else if (checkRow(y, playerType, n))
            return Parameters.GAME_RESULT_FINISHED;
        else if (checkDiagonal(x, y, playerType, n))
            return Parameters.GAME_RESULT_FINISHED;
        else if (checkAntiDiagonal(x, y, playerType, n))
            return Parameters.GAME_RESULT_FINISHED;
        else if (game.getBoardManager().isFull())
            return Parameters.GAME_RESULT_DRAW;


        return Parameters.GAME_RESULT_NOT_FINISHED;
    }

    /**
     * Return number defines what to draw on a board in TicTacToeGame class
     *
     * @param point Point clicked on board
     * @return
     */
    @Override
    public int onBoardClick(Point point) {
        Log.d(TAG, "onBoardClick()");
        int result = Parameters.CLICK_RESULT_MOVE_NOT_POSSIBLE;
        // check if game is in progress
        if (isGameInProgress) {
            // get player whose turn is in progress
            Player currentPlayer = game.getPlayerManager().currentPlayer();
            // check if this move is possible
            if (checkMoveIsPossible(point.x, point.y)) {
                // insert new point to game logic
                game.getBoardManager().insertAtCoordinates(point.x, point.y, currentPlayer.getPlayerType());
                // if move is possible, notify output to draw player icon
                game.getOutputProvider().drawOnBoard(point.x, point.y, currentPlayer.getBoardDrawableResource());
                // check if this move was winning move.
                int gameResult = checkEndOfGame(point.x, point.y, numberOfCellsToWin, currentPlayer.getPlayerType());
                if (gameResult == Parameters.GAME_RESULT_FINISHED) {
                    game.getOutputProvider().displayWinnerCells(winnerPoints);
                    for (int i = 0; i < winnerPoints.size(); i++) {
                        Log.i("winner", "winner points" + String.valueOf(winnerPoints.get(i)));
                    }
                    isGameInProgress = false;
                    result = Parameters.CLICK_RESULT_MOVE_OK;
                    Log.d(TAG, "onBoardClick() : GAME FINISHED");
                } else if (gameResult == Parameters.GAME_RESULT_DRAW) {
                    winnerPoints.clear();
                    game.getOutputProvider().displayWinnerCells(winnerPoints);
                    game.getOutputProvider().displayMessage("Draw!");
                    isGameInProgress = false;
                    result = Parameters.CLICK_RESULT_MOVE_OK;
                    Log.d(TAG, "onBoardClick() : DRAW");
                } else {
                    // launch next turn
                    game.getPlayerManager().nextTurn();
                    result = Parameters.CLICK_RESULT_MOVE_OK;
                    Log.d(TAG, "onBoardClick() : NEXT TURN");
                }
            }
        }
        return result;
    }

    @Override
    public void onExitGame() {

    }

    @Override
    public void onPause() {

    }

    private int getWinConditions(int boardSize) {

        if (boardSize == 3 || boardSize == 4)
            return 3;
        else if (boardSize == 5)
            return 4;
        else return 5;
    }

    public int getNumberOfCellsToWin() {
        return this.numberOfCellsToWin;
    }

    private boolean checkColumn(int x, int playerType, int boardSize) {
        int counter = 0;

        for (int i = 0; i < boardSize; i++) {
            if (game.getBoardManager().getAtCoordinates(x, i) == playerType) {
                counter++;
                winnerPoints.add(new Point(x, i));
                if (counter == numberOfCellsToWin)
                    return true;
            } else {
                counter = 0;
                winnerPoints.clear();
            }
        }
        return false;
    }

    private boolean checkRow(int y, int playerType, int boardSize) {
        int counter = 0;

        for (int i = 0; i < boardSize; i++) {
            if (game.getBoardManager().getAtCoordinates(i, y) == playerType) {
                counter++;
                winnerPoints.add(new Point(i, y));

                if (counter == numberOfCellsToWin)
                    return true;
            } else {
                counter = 0;
                winnerPoints.clear();
            }
        }
        return false;
    }

    private boolean checkDiagonal(int x, int y, int playerType, int boardSize) {
        int counter = 0;

        int k = x;
        int m = y;

        while (k > 0 && m > 0) {
            k--;
            m--;
        }

        for (int i = k; i < boardSize; i++) {
            if (game.getBoardManager().getAtCoordinates(i, m) == playerType) {
                counter++;
                winnerPoints.add(new Point(i, m));
                if (counter == numberOfCellsToWin)
                    return true;
            } else {
                counter = 0;
                winnerPoints.clear();
            }
            if (m < boardSize - 1)
                m++;
        }
        return false;
    }

    private boolean checkAntiDiagonal(int x, int y, int playerType, int boardSize) {
        int counter = 0;

        int k = x;
        int m = y;

        while (k > 0 && m < boardSize - 1) {
            k--;
            m++;
        }

        for (int i = k; i < boardSize; i++) {
            if (game.getBoardManager().getAtCoordinates(i, m) == playerType) {
                counter++;
                winnerPoints.add(new Point(i, m));
                if (counter == numberOfCellsToWin)
                    return true;
            } else {
                counter = 0;
                winnerPoints.clear();
            }
            if (m > 0)
                m--;
        }
        return false;
    }
}
