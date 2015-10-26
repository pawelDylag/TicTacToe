package com.fais.tictactoe.interfaces;

import android.graphics.Point;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface GameEngine {

    // startowanie gry
    void startGame();

    // stopowanie gry
    void stopGame();

    // zwraca numer tury
    int getCurrentTurnNumber();

    // sprawdzenie, czy ruch jest poprawny
    boolean checkMove(int x, int y);

    // sprawdzenie warunkow wygranej
    boolean checkEndOfGame(int x, int y, int playerType);

    // ustawia kto zaczyna pierwszy
    int setInitialPlayer();

    // asynchroniczne wydarzenie - kiedy user kliknie plansze
    int onBoardClick(Point point, int firstPlayer, int secondPlayer);

    // asynchroniczne wydarzenie - kiedy user wyjdzie z gry
    void onExitGame();

    // asynchroniczne wydarzenie - kiedy user/system zastopuje gre
    void onPause();

}
