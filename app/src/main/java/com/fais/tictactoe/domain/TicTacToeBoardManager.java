package com.fais.tictactoe.domain;

import com.fais.tictactoe.interfaces.BoardManager;

import java.util.Arrays;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToeBoardManager implements BoardManager {

    private int boardSize;
    private int[][] board;

    public TicTacToeBoardManager(int boardSize) {
        setBoardSize(boardSize);
    }

    @Override
    public boolean insertAtCoordinates(int x, int y, int playerType) {
        board[x][y] = playerType;
        return true;
    }

    @Override
    public int getAtCoordinates(int x, int y) {
        return board[x][y];
    }

    @Override
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
        }
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
