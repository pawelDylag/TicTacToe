package com.fais.tictactoe.interfaces;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface BoardManager
{
    // ustawia wielkość planszy
    void setBoardSize(int boardSize);

    // zwraca wielkosc planszy
    int getBoardSize();

    // true - wtedy kiedy sie uda zapisac,
    // false kiedy sie nie uda
    boolean insertAtCoordinates(int x, int y, int playerType);

    // zwraca typ na danym polu
    int getAtCoordinates(int x, int y);

    // sprawdza czy plansza jest zapelniona
    boolean isFull();

}
