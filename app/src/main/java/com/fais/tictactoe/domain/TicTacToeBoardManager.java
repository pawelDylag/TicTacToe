package com.fais.tictactoe.domain;

import com.fais.tictactoe.interfaces.BoardManager;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeBoardManager implements BoardManager {

    @Override
    public boolean insertAtCoordinates(int x, int y, int playerType) {
        return false;
    }

    @Override
    public int getAtCoordinates(int x, int y) {
        return 0;
    }
}
