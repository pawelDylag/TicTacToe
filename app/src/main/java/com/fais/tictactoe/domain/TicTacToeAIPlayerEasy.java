package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.fais.tictactoe.Data.Player;

import java.util.Random;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerEasy extends Player
{
    private TicTacToeGame game;
    private boolean firstMove;
    private int howManyOccupied;
    private boolean myfirstmove = false;

    public TicTacToeAIPlayerEasy(TicTacToeGame game)
    {
        this.game = game;
        firstMove = true;
        howManyOccupied = 0;
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
        // Koordynaty do wykonania ruchu
        private int xcoord;
        private int ycoord;
        // INT mojego przeciwnika
        private int myOpponent;
        // Koordynaty jesli trzeba postawic cos pomiedzy do wygrania
        private int betweenX;
        private int betweenY;
        // Zliczanie ile jest "szpar"
        private int betweenMarks;
        // Zliczanie ile jest postawionych znakow w rzedzie/kolumnie/po skosie
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
        protected Void doInBackground(Void... arg0)
        {
            howManyOccupied += 2;
            if(!myfirstmove)
            {
                checkFirstMove();
            }
            if(!madeMove)
            {
                checkPotentialWinningMoves();
            }
            if(!madeMove)
            {
                checkPotentialOpponentMoves();
            }
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

        // Sprawdza, kto pierwszy sie rusza
        private void checkFirstMove()
        {
            boolean opponentFirst = false;
            int firstX = 0,firstY = 0;
            for(int x = 0; x<boardsize; x++)
            {
                for (int y = 0; y < boardsize; y++)
                {
                    if(game.getBoardManager().getAtCoordinates(x,y)!=0)
                    {
                        opponentFirst = true;
                        firstX = x;
                        firstY = y;
                    }
                }
            }
            if(opponentFirst)
            {
                System.out.println("OPPONENT WENT FIRST, I COUNTER!");
                System.out.println("OPPONENT POSITION: "+firstX+","+firstY);
                if((firstX+1)>=boardsize)
                {
                    xcoord = firstX - 1;
                }
                else
                {
                    xcoord = firstX + 1;
                }
                if((firstY+1)>=boardsize)
                {
                    ycoord = firstY - 1;
                }
                else
                {
                    ycoord = firstY + 1;
                }
                System.out.println("X: "+xcoord+" Y: "+ycoord);
                madeMove = true;
            }
            else
            {
                System.out.println("Random MOVE I GO FIRST!");
                Random moveGenerator = new Random();
                xcoord = moveGenerator.nextInt(boardsize-1);
                ycoord = moveGenerator.nextInt(boardsize-1);
                madeMove = true;
            }
            // Pierwszy ruch wykoanny, mamy to z glowy!
            myfirstmove = true;
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
            Random moveGenerator = new Random();
            int x1, y1;
            if(howManyOccupied<((boardsize*boardsize)/2) && firstMove==false)
            {
                while(!madeMove)
                {
                    x1 = moveGenerator.nextInt(boardsize-1);
                    y1 = moveGenerator.nextInt(boardsize-1);
                    if(game.getBoardManager().getAtCoordinates(x1, y1)==0)
                    {
                        xcoord = x1;
                        ycoord = y1;
                        madeMove = true;
                        break;
                    }
                }
            }
            else
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
                            firstMove = false;
                        }
                        if(madeMove) break;
                    }
                    if(madeMove)
                    {
                        break;
                    }
                }
            }
        }

        // Sprawdza mozliwosc wygranej po X-ach
        private void canIWinXRow(int x, int y, int which)
        {
            int a;
            for (a = 0; a < boardsize; a++)
            {
                if (game.getBoardManager().getAtCoordinates(a, y) == which)
                {
                    myInRow++;
                }
                else if(game.getBoardManager().getAtCoordinates(a,y)==0  && betweenMarks<1)
                {
                    betweenX = a;
                    betweenY = y;
                    betweenMarks++;
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a, y)==0)
                {
                    xcoord = a;
                    ycoord = y;
                    madeMove = true;
                    break;
                }
            }
            a--;
            if(betweenMarks>0 && myInRow == winNr-1)
            {
                xcoord = betweenX;
                ycoord = betweenY;
                madeMove = true;
                betweenMarks = 0;
            }
            else
            {
                betweenMarks = 0;
            }
            if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(a, y)==0 && madeMove==false)
            {
                xcoord = a;
                ycoord = y;
                madeMove = true;
            }
            myInRow = 0;
        }

        // Sprawdza mozliwosc wygranej po Y-ach
        private void canIWinYRow(int x, int y, int which)
        {
            int a;
            for (a = 0; a < boardsize; a++)
            {
                if (game.getBoardManager().getAtCoordinates(x, a) == which)
                {
                    myInRow++;
                }
                else if(game.getBoardManager().getAtCoordinates(x,a)==0  && betweenMarks<1)
                {
                    betweenX = x;
                    betweenY = a;
                    betweenMarks++;
                }
                if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a)==0)
                {
                    xcoord = x;
                    ycoord = a;
                    madeMove = true;
                    break;
                }
            }
            a--;
            if(betweenMarks>0 && myInRow == winNr-1)
            {
                xcoord = betweenX;
                ycoord = betweenY;
                madeMove = true;
                betweenMarks = 0;
            }
            else
            {
                betweenMarks = 0;
            }
            if (myInRow == winNr-1 && game.getBoardManager().getAtCoordinates(x, a)==0 && madeMove==false)
            {
                xcoord = x;
                ycoord = a;
                madeMove = true;
            }
            myInRow = 0;
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
                else if((game.getBoardManager().getAtCoordinates(a, b)==0)  && betweenMarks<1)
                {
                    betweenMarks++;
                    betweenX = a;
                    betweenY = b;
                }
                a++;
                b++;
            }
            if(betweenMarks>0 && myInDiag == winNr-1)
            {
                xcoord = betweenX;
                ycoord = betweenY;
                madeMove = true;
                betweenMarks = 0;
            }
            else
            {
                betweenMarks = 0;
            }
            if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a - 1, b - 1)==0 && madeMove==false)
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
                    else if((game.getBoardManager().getAtCoordinates(a, b)==0)  && betweenMarks<1)
                    {
                        betweenMarks++;
                        betweenX = a;
                        betweenY = b;
                    }
                    a++;
                    b--;
                }
                if(betweenMarks>0 && myInDiag == winNr-1)
                {
                    xcoord = betweenX;
                    ycoord = betweenY;
                    madeMove = true;
                    betweenMarks = 0;
                }
                else
                {
                    betweenMarks = 0;
                }
                if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a - 1, b + 1)==0 && madeMove==false)
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
                while (a >= 0 && b < boardsize)
                {
                    if (game.getBoardManager().getAtCoordinates(a, b) == which)
                    {
                        myInDiag++;
                    }
                    else if ((game.getBoardManager().getAtCoordinates(a, b) == 0)  && betweenMarks<1)
                    {
                        betweenMarks++;
                        betweenX = a;
                        betweenY = b;
                    }
                    a--;
                    b++;
                }
                if (betweenMarks>0 && myInDiag == winNr - 1)
                {
                    xcoord = betweenX;
                    ycoord = betweenY;
                    madeMove = true;
                    betweenMarks = 0;
                }
                else
                {
                    betweenMarks = 0;
                }
                if (myInDiag == winNr - 1 && game.getBoardManager().getAtCoordinates(a + 1, b - 1) == 0 && madeMove==false)
                {
                    xcoord = a + 1;
                    ycoord = b - 1;
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
                    else if((game.getBoardManager().getAtCoordinates(a, b)==0) && betweenMarks<1)
                    {
                        betweenMarks++;
                        betweenX = a;
                        betweenY = b;
                    }
                    a--;
                    b--;
                }
                if(betweenMarks>0 && myInDiag == winNr-1)
                {
                    xcoord = betweenX;
                    ycoord = betweenY;
                    madeMove = true;
                    betweenMarks = 0;
                }
                else
                {
                    betweenMarks = 0;
                }
                if(myInDiag == winNr-1 && game.getBoardManager().getAtCoordinates(a+1,b+1)==0 && madeMove==false)
                {
                    xcoord = a+1;
                    ycoord = b+1;
                    madeMove = true;
                }
            }
            myInDiag = 0;
        }
    }
}
