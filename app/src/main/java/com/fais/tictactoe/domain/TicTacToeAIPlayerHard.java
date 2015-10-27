package com.fais.tictactoe.domain;

import android.os.AsyncTask;

import com.fais.tictactoe.Data.Player;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerHard extends Player
{
    private int boardsize;
    private boolean gameStarted = false;
    private boolean madeMove = false;
    private int xcoord;
    private int ycoord;
    private int myOpponent;
    int myInRow = 0;
    int myInDiag = 0;
    public TicTacToeAIPlayerHard()
    {
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
            while(isGameFinished)
            {
                // wait for game to start
            }
            if(!gameStarted)
            {
                boardsize = boardManager.getBoardSize();
                if(boardsize>5)
                {
                    winNr = 5;
                }
                else if(boardsize==3)
                {
                    winNr = boardsize;
                }
                else
                {
                    winNr = boardsize - 1;
                }
            }
            gameStarted = true;
            while(!playerManager.isMyTurn(2))
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("Licze ruch!");
            // TU ZACZYNA MYSLEC
            checkPotentialWinningMoves();
            if(!madeMove)
            {
                checkPotentialOpponentMoves();
            }
            if(!madeMove)
            {
                checkMoves();
            }
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
            playerManager.AIMoves(xcoord, ycoord, boardsize);
            madeMove = false;
            if(!isGameFinished)
            {
                new waitMyMove().execute();
            }
        }
    }

    // Sprawdza ruchy, ktore moga nam dac zwyciestwo
    private void checkPotentialWinningMoves()
    {
        for(int x = 0; x<boardsize; x++)
        {
            for(int y = 0; y<boardsize; y++)
            {
                if(boardManager.getAtCoordinates(x,y)==playerType)
                {
                    System.out.println("Trafilem na swoj na polu: "+x+";"+y);
                    canIWinXRow(x, y, playerType);
                    if(!madeMove)
                    {
                        canIWinYRow(x,y, playerType);
                    }
                    if(!madeMove)
                    {
                        canIWinDiag(x,y, playerType);
                    }
                }
                if(madeMove) break;
            }
            if(madeMove)
            {
                break;
            }
        }
    }

    // Sprawdza ruchy, ktore moga dac przeciwnikowi zwyciestwo
    private void checkPotentialOpponentMoves()
    {
        for(int x = 0; x<boardsize; x++)
        {
            for(int y = 0; y<boardsize; y++)
            {
                if(boardManager.getAtCoordinates(x,y)!=playerType && boardManager.getAtCoordinates(x,y)!=0)
                {
                    myOpponent = boardManager.getAtCoordinates(x,y);
                    System.out.println("Trafilem na przeciwnika na polu: "+x+";"+y);
                    canIWinXRow(x, y, myOpponent);
                    if(!madeMove)
                    {
                        canIWinYRow(x,y,myOpponent);
                    }
                    if(!madeMove)
                    {
                        canIWinDiag(x,y, myOpponent);
                    }
                }
                if(madeMove) break;
            }
            if(madeMove)
            {
                break;
            }
        }
    }

    // Sprawdza dowolne miejsce, gdzie moze zagrac
    private void checkMoves()
    {
        for(int x = 0; x<boardsize; x++)
        {
            for(int y = 0; y<boardsize; y++)
            {
                if(boardManager.getAtCoordinates(x,y)==0)
                {
                    xcoord = x;
                    ycoord = y;
                    madeMove = true;
                    break;
                }
                if(madeMove) break;
            }
            if(madeMove)
            {
                break;
            }
        }
    }

    // Sprawdza mozliwosc wygranej po X-ach
    private void canIWinXRow(int x, int y, int which)
    {
        int a;
        if(boardsize-x>=winNr)
        {
            for (a = x; a < boardsize; a++)
            {
                if (boardManager.getAtCoordinates(a, y) == which)
                {
                    myInRow++;
                }
                else
                {
                    if (myInRow == winNr-1 && boardManager.getAtCoordinates(a,y)==0)
                    {
                        xcoord = a;
                        ycoord = y;
                        madeMove = true;
                        break;
                    }
                    break;
                }
            }
            if (myInRow == winNr-1 && boardManager.getAtCoordinates(a,y)==0)
            {
                xcoord = a;
                ycoord = y;
                madeMove = true;
            }
            myInRow = 0;
        }
        else
        {
            for (a = x; a >= 0; a--)
            {
                if (boardManager.getAtCoordinates(a, y) == which)
                {
                    myInRow++;
                }
                else
                {
                    if (myInRow == winNr-1 && boardManager.getAtCoordinates(a,y)==0)
                    {
                        xcoord = a;
                        ycoord = y;
                        madeMove = true;
                        break;
                    }
                    break;
                }
            }
            if (myInRow == winNr-1 && boardManager.getAtCoordinates(a+1,y)==0)
            {
                xcoord = a;
                ycoord = y;
                madeMove = true;
            }
            myInRow = 0;
        }
    }

    // Sprawdza mozliwosc wygranej po Y-ach
    private void canIWinYRow(int x, int y, int which)
    {
        int a;
        if(boardsize-y>=winNr)
        {
            for (a = y; a < boardsize; a++)
            {
                if (boardManager.getAtCoordinates(x, a) == which)
                {
                    myInRow++;
                }
                else
                {
                    if (myInRow == winNr-1 && boardManager.getAtCoordinates(x,a)==0)
                    {
                        xcoord = x;
                        ycoord = a;
                        madeMove = true;
                        break;
                    }
                    break;
                }
            }
            if (myInRow == winNr-1 && boardManager.getAtCoordinates(x,a)==0)
            {
                xcoord = x;
                ycoord = a;
                madeMove = true;
            }
            myInRow = 0;
        }
        else
        {
            for (a = y; a >= 0; a--)
            {
                if (boardManager.getAtCoordinates(x, a) == which)
                {
                    myInRow++;
                }
                else
                {
                    if (myInRow == winNr-1 && boardManager.getAtCoordinates(x,a)==0)
                    {
                        xcoord = x;
                        ycoord = a;
                        madeMove = true;
                        break;
                    }
                    break;
                }
            }
            if (myInRow == winNr-1 && boardManager.getAtCoordinates(x,a+1)==0)
            {
                xcoord = x;
                ycoord = a;
                madeMove = true;
            }
            myInRow = 0;
        }
    }

    // Sprawdza mozliwosc wygranej po diagonali
    private void canIWinDiag(int x, int y, int which)
    {
        int a,b;
        a = x;
        b = y;

        // Petla a++ b++
        while(a<boardsize && b<boardsize)
        {
            if(boardManager.getAtCoordinates(a,b)==which)
            {
                myInDiag++;
            }
            a++;
            b++;
        }
        if(myInDiag == winNr-1 && boardManager.getAtCoordinates(a-1,b-1)==0)
        {
            xcoord = a-1;
            ycoord = b-1;
            madeMove = true;
        }
        myInDiag = 0; a=x; b=y;

        // Petla a++ b--
        if(!madeMove)
        {
            while(a<boardsize && b>=0)
            {
                if(boardManager.getAtCoordinates(a,b)==which)
                {
                    myInDiag++;
                }
                a++;
                b--;
            }
            if(myInDiag == winNr-1 && boardManager.getAtCoordinates(a-1,b+1)==0)
            {
                xcoord = a-1;
                ycoord = b+1;
                madeMove = true;
            }
        }
        myInDiag = 0; a=x; b=y;

        //Petla a-- b++
        if(!madeMove)
        {
            while(a>=0 && b<boardsize)
            {
                if(boardManager.getAtCoordinates(a,b)==which)
                {
                    myInDiag++;
                }
                a--;
                b++;
            }
            if(myInDiag == winNr-1 && boardManager.getAtCoordinates(a+1,b-1)==0)
            {
                xcoord = a+1;
                ycoord = b-1;
                madeMove = true;
            }
        }
        myInDiag = 0; a=x; b=y;

        // Petla a-- b--
        if(!madeMove)
        {
            while(a>=0 && b>=0)
            {
                if(boardManager.getAtCoordinates(a,b)==which)
                {
                    myInDiag++;
                }
                a--;
                b--;
            }
        }
        if(myInDiag == winNr-1 && boardManager.getAtCoordinates(a+1,b+1)==0)
        {
            xcoord = a+1;
            ycoord = b+1;
            madeMove = true;
        }
        myInDiag = 0;
    }
}
