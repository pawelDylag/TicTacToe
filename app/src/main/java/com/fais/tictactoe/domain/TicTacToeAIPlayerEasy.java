package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.fais.tictactoe.Data.Player;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerEasy extends Player
{
    private TicTacToeGame game;

    public TicTacToeAIPlayerEasy(TicTacToeGame game) {
        this.game = game;
    }

    @Override
    public void onTurnReceived() {
        Log.d("AI", "MY TURN!");
        new MakeMoveDecisionAsyncTask().execute();
    }

    // main computation here
    public class MakeMoveDecisionAsyncTask extends AsyncTask<Void, Void, Void> {

        int winNr;
        private int boardsize;
        private boolean madeMove = false;
        private int xcoord;
        private int ycoord;
        private int myOpponent;
        int myInRow = 0;
        int myInDiag = 0;

        public MakeMoveDecisionAsyncTask() {
            this.winNr = game.getGameEngine().getNumberOfCellsToWin();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            boardsize = game.getBoardManager().getBoardSize();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            checkPotentialWinningMoves();
            if(!madeMove)
            {
                checkMoves();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            game.getGameEngine().onBoardClick(new Point(xcoord, ycoord));
        }

        // Sprawdza ruchy, ktore moga nam dac zwyciestwo
        private void checkPotentialWinningMoves()
        {
            for(int x = 0; x<boardsize; x++)
            {
                for(int y = 0; y<boardsize; y++)
                {
                    if(game.getBoardManager().getAtCoordinates(x, y)==playerType)
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
                    if(game.getBoardManager().getAtCoordinates(x, y)!=playerType && game.getBoardManager().getAtCoordinates(x, y)!=0)
                    {
                        myOpponent = game.getBoardManager().getAtCoordinates(x, y);
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
                    if(game.getBoardManager().getAtCoordinates(x, y)==0)
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
                    if (game.getBoardManager().getAtCoordinates(a, y) == which)
                    {
                        myInRow++;
                    }
                    else
                    {
                        if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a, y)==0)
                        {
                            xcoord = a;
                            ycoord = y;
                            madeMove = true;
                            break;
                        }
                        break;
                    }
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a, y)==0)
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
                    if (game.getBoardManager().getAtCoordinates(a, y) == which)
                    {
                        myInRow++;
                    }
                    else
                    {
                        if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a, y)==0)
                        {
                            xcoord = a;
                            ycoord = y;
                            madeMove = true;
                            break;
                        }
                        break;
                    }
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a + 1, y)==0)
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
                    if (game.getBoardManager().getAtCoordinates(x, a) == which)
                    {
                        myInRow++;
                    }
                    else
                    {
                        if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a)==0)
                        {
                            xcoord = x;
                            ycoord = a;
                            madeMove = true;
                            break;
                        }
                        break;
                    }
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a)==0)
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
                    if (game.getBoardManager().getAtCoordinates(x, a) == which)
                    {
                        myInRow++;
                    }
                    else
                    {
                        if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a)==0)
                        {
                            xcoord = x;
                            ycoord = a;
                            madeMove = true;
                            break;
                        }
                        break;
                    }
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a + 1)==0)
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
                if(game.getBoardManager().getAtCoordinates(a, b)==which)
                {
                    myInDiag++;
                }
                a++;
                b++;
            }
            if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a - 1, b - 1)==0)
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
                    if(game.getBoardManager().getAtCoordinates(a, b)==which)
                    {
                        myInDiag++;
                    }
                    a++;
                    b--;
                }
                if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a - 1, b + 1)==0)
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
                    if(game.getBoardManager().getAtCoordinates(a, b)==which)
                    {
                        myInDiag++;
                    }
                    a--;
                    b++;
                }
                if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a + 1, b - 1)==0)
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
                    if(game.getBoardManager().getAtCoordinates(a, b)==which)
                    {
                        myInDiag++;
                    }
                    a--;
                    b--;
                }
            }
            if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a+1,b+1)==0)
            {
                xcoord = a+1;
                ycoord = b+1;
                madeMove = true;
            }
            myInDiag = 0;
        }
    }
}
