package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Player;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerEasy extends Player
{
    private TicTacToeGame game;
    private int boardSize;

    public TicTacToeAIPlayerEasy(TicTacToeGame game)
    {
       this.game = game;
    }

    private void waitMyTurn()
    {

    }
    private void checkMove(int x, int y)
    {
        boardSize = game.getBoardManager().getBoardSize();
    }

    @Override
    public void onTurnReceived() {

    }
}
