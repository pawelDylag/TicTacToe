package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Player;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerEasy extends Player
{
    private int boardSize;
    public TicTacToeAIPlayerEasy()
    {
        waitMyTurn();
    }

    private void waitMyTurn()
    {

    }
    private void checkMove(int x, int y)
    {
        boardSize = boardManager.getBoardSize();
    }
}
