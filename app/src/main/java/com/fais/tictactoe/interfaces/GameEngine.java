package com.fais.tictactoe.interfaces;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface GameEngine {

    // startowanie gry
    void startGame();

    // stopowanie gry
    void stopGame();

    // sprawdzenie, czy ruch jest poprawny
    boolean checkMoveIsPossible(int x, int y);

    // sprawdzenie warunkow wygranej
    int checkEndOfGame(int x, int y, int playerType);

    // asynchroniczne wydarzenie - kiedy user kliknie plansze
    int onBoardClick(Point point);

    // asynchroniczne wydarzenie - kiedy user wyjdzie z gry
    void onExitGame();

    // asynchroniczne wydarzenie - kiedy user/system zastopuje gre
    void onPause();

    int getNumberOfCellsToWin();
}
