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
     * @param x
     * @param y
     * @param numberOfCellsToWin
     * @param playerType
     * @return
     */
    @Override
    public int checkEndOfGame(int x, int y, int numberOfCellsToWin, int playerType) {

        Log.d("TicTacToeGameEngine", "checkEndOfGame() : for playerType = " + playerType);
        int n = game.getBoardManager().getBoardSize();

        //check col
        for (int i = 0; i < n; i++) {
            if (game.getBoardManager().getAtCoordinates(x, i) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerPoints.add(new Point(x, j));
                }
                return Parameters.GAME_RESULT_FINISHED;
            }
        }
        //check row
        for (int i = 0; i < n; i++) {
            if (game.getBoardManager().getAtCoordinates(i, y) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerPoints.add(new Point(j, y));
                }
                return Parameters.GAME_RESULT_FINISHED;
            }
        }
        //check diag
        if (x == y) {
            //we're on a diagonal
            for (int i = 0; i < n; i++) {
                if (game.getBoardManager().getAtCoordinates(i, i) != playerType)
                    break;
                if (i == n - 1) {
                    for (int j = 0; j < n; j++) {
                        winnerPoints.add(new Point(j, j));
                    }
                    return Parameters.GAME_RESULT_FINISHED;
                }
            }
        }

        //check anti diag
        for (int i = 0; i < n; i++) {
            if (game.getBoardManager().getAtCoordinates(i, (n - 1) - i) != playerType)
                break;
            if (i == n - 1) {
                for (int j = 0; j < n; j++) {
                    winnerPoints.add(new Point(j, (n - 1) - j));
                }
                return Parameters.GAME_RESULT_FINISHED;
            }
        }
        //check draw
        if (game.getBoardManager().isFull()) {
            return Parameters.GAME_RESULT_DRAW;
        }

        return Parameters.GAME_RESULT_NOT_FINISHED;
    }

    /**
     * Return number defines what to draw on a board in TicTacToeGame class
     *
     * @param point        Point clicked on board
     * @return
     */
    @Override
    public int onBoardClick(Point point) {
        Log.d(TAG, "onBoardClick()");
        int result = Parameters.CLICK_RESULT_MOVE_NOT_POSSIBLE;
        // check if game is in progress
        if(isGameInProgress) {
            // get player whose turn is in progress
            Player currentPlayer = game.getPlayerManager().currentPlayer();
            // check if this move is possible
            if (checkMoveIsPossible(point.x, point.y)) {
                // insert new point to game logic
                game.getBoardManager().insertAtCoordinates(point.x, point.y, currentPlayer.getPlayerType());
                // if move is possible, notify output to draw player icon
                game.getOutputProvider().drawOnBoard(point.x, point.y, currentPlayer.getBoardDrawableResource());
                // check if this move was winning move.
                int gameResult = checkEndOfGame(point.x, point.y, numberOfCellsToWin,  currentPlayer.getPlayerType());
                if (gameResult == Parameters.GAME_RESULT_FINISHED) {
                    game.getOutputProvider().displayWinnerCells(winnerPoints);
                    isGameInProgress = false;
                    result = Parameters.CLICK_RESULT_MOVE_OK;
                    Log.d(TAG, "onBoardClick() : GAME FINISHED");
                } else if (gameResult == Parameters.GAME_RESULT_DRAW) {
                    game.getOutputProvider().displayWinnerCells(winnerPoints);
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
        if (boardSize <= 4) {
            return 3;
        } else if (boardSize > 4 && boardSize <= 6 ) {
            return 4;
        } else return 5;
    }

    public int getNumberOfCellsToWin() {
        return this.numberOfCellsToWin;
    }


}
