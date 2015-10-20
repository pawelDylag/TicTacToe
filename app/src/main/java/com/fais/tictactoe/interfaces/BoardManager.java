package com.fais.tictactoe.interfaces;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface BoardManager {

    // to ma zarzadzac plansza
    // proponuje zrobic osobny model Boardu w ktorym bedzie implementacja samej planszy
    // np. int[][] board

    // true - wtedy kiedy sie uda zapisac,
    // false kiedy sie nie uda
    boolean insertAtCoordinates(int x, int y, int playerType);

    // zwraca typ na danym polu
    int getAtCoordinates(int x, int y);

}
