package com.fais.tictactoe.domain;

import android.os.AsyncTask;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.Player;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerEasy extends Player
{
    private int boardsize;
    private boolean gameStarted = false;
    private boolean madeMove = false;
    public TicTacToeAIPlayerEasy()
    {
        System.out.println("Wywoluje czekanie");
        new waitMyMove().execute();
    }

    private class waitMyMove extends AsyncTask<String, Integer, String>
    {
        boolean myTurn;
        @Override
        protected void onPreExecute()
        {
            myTurn = false;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            System.out.println("Czekam na start");
            while(!isGameStarted)
            {
                System.out.println("isGameStarted: " + isGameStarted);
                // wait for game to start
            }
            if(!gameStarted)
            {
                boardsize = boardManager.getBoardSize();
            }
            gameStarted = true;
            System.out.println("Czekam na ture");
            while(!playerManager.isMyTurn(2))
            {
                System.out.println("Czyja tura "+playerManager.isMyTurn(2));
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("Robie ruch!");
            checkPotentialMoves();
            return "MyMove";
        }

        @Override
        protected void onProgressUpdate(Integer... integers)
        {

        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            new waitMyMove().execute();
        }
    }

    private void checkPotentialMoves()
    {
        int inRow = 0;
        int inDiag = 0;
        for(int x = 0; x<boardsize; x++)
        {
            for(int y = 0; y<boardsize; y++)
            {
                if(boardManager.getAtCoordinates(x,y)==0 )
                {
                    playerManager.AIMoves(x,y);
                    System.out.println("Mam ruch!");
                    madeMove = true;
                    break;
                }
                if(madeMove) break;
            }
            if(madeMove)
            {
                madeMove = false;
                break;
            }
        }
    }
}
