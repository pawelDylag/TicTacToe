package com.fais.tictactoe.domain;

import com.fais.tictactoe.interfaces.BoardManager;

import java.util.Arrays;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeBoardManager implements BoardManager
{
    private int boardsize;
    private int [][] board;

    @Override
    public boolean insertAtCoordinates(int x, int y, int playerType) {
        return false;
    }

    @Override
    public int getAtCoordinates(int x, int y) {
        return 0;
    }

    @Override
    public void setBoardsize(int boardsize)
    {
        this.boardsize = boardsize;
        board = new int[boardsize][boardsize];
        Arrays.fill(board, 0);
    }

    @Override
    public int getBoardsize()
    {
        return boardsize;
    }
}
