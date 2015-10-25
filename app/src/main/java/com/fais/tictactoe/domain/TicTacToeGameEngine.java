package com.fais.tictactoe.domain;

import android.graphics.Point;

import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeGameEngine implements com.fais.tictactoe.interfaces.GameEngine {

    private PlayerManager playerManager;
    private BoardManager boardManager;
    private int turnNumber;
    private boolean isGameStarted;

    public TicTacToeGameEngine(PlayerManager playerManager, BoardManager boardManager) {
        this.playerManager = playerManager;
        this.boardManager = boardManager;
    }

    @Override
    public void startGame() {
        isGameStarted = true;
    }

    @Override
    public void stopGame() {

    }

    @Override
    public int getCurrentTurnNumber() {
        return 0;
    }

    @Override
    public boolean checkMove(int x, int y, int playerType) {
        return false;
    }

    @Override
    public boolean checkEndOfGame() {
        return false;
    }

    @Override
    public int setInitialPlayer() {
        return 0;
    }

    @Override
    public boolean onBoardClick(Point point) {
        // zaimplementowac
        return true;
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
