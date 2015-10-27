package com.fais.tictactoe.domain;

import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;

import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.utilities.Util;

/**
 * Created by Shevson on 2015-10-25.
 */
public class TicTacToeAIPlayerHard extends Player {

    private TicTacToeGame game;

    public TicTacToeAIPlayerHard(TicTacToeGame game) {
        this.game = game;
    }

    @Override
    public void onTurnReceived() {
        Log.d("AI", "MY TURN!");
        new MakeMoveDecisionAsyncTask().execute();
    }

    // main computation here
    public class MakeMoveDecisionAsyncTask extends AsyncTask<Void, Void, Void> {

        Point boardClick = new Point(0, 0);

        public MakeMoveDecisionAsyncTask() {
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            int positon = 0;
            boolean free = false;
            while (!free) {
                boardClick = Util.convert1DIndexTo2D(positon, game.getBoardManager().getBoardSize());
                free = game.getBoardManager().getAtCoordinates(boardClick.x, boardClick.y) == 0;
                positon++;
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            game.getGameEngine().onBoardClick(boardClick);
        }

    }
}
