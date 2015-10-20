package com.fais.tictactoe.interfaces;

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

    // uruchomienie petli do kolejnej tury
    void nextTurn();

    // sprawdzenie, czy ruch jest poprawny
    boolean checkMove(int x, int y, int playerType);

    // sprawdzenie warunkow wygranej
    boolean checkEndOfGame();

    // ustawia kto zaczyna pierwszy
    int setInitialPlayer();

}
