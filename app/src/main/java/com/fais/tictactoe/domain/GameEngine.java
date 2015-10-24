package com.fais.tictactoe.domain;

import android.graphics.Point;

/**
 * Created by paweldylag on 20/10/15.
 */
public class GameEngine implements com.fais.tictactoe.interfaces.GameEngine {

    @Override
    public void startGame() {

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
    public void onBoardClick(Point point) {

    }

    @Override
    public void onExitGame() {

    }

    @Override
    public void onPause() {

    }
}
