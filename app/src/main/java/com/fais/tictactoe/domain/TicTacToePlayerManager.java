package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.R;
import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;

import java.util.Random;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToePlayerManager implements PlayerManager{

    private TicTacToeGame game;

    private Player firstPlayer;
    private Player secondPlayer;

    private boolean firstPlayerHasTurn;

    private int turnCounter;

    public TicTacToePlayerManager(TicTacToeGame game, Player firstPlayer, Player secondPlayer)
    {
        this.turnCounter = 0;
        this.game = game;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        // setup board icons
        this.firstPlayer.setBoardDrawableResource(R.drawable.board_player_1_thumbnail);
        this.secondPlayer.setBoardDrawableResource(R.drawable.board_player_2_thumbnail);
        this.setInitialPlayer();
    }

    @Override
    public Player getPlayer(int playerType)
    {
        if(firstPlayer.getPlayerType()==playerType)
        {
            return firstPlayer;
        }
        else if(secondPlayer.getPlayerType()==playerType)
        {
            return secondPlayer;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    /**
     * Sets first player
     *
     * @return
     */
    private void setInitialPlayer() {
        Random generator = new Random();
        int i = generator.nextInt(2);
        if (i == 0) {
            firstPlayerHasTurn = true;
        } else if (i == 1) {
            firstPlayerHasTurn = false;
        }
    }

    /**
     * Gives turn to next player and returns him.
     * @return
     */
    @Override
    public void nextTurn() {
        // change turn flag
        firstPlayerHasTurn = !firstPlayerHasTurn;
        // increment turn counter value
        turnCounter++;
        // notify player about his turn
        currentPlayer().onTurnReceived();
    }

    @Override
    public Player currentPlayer() {
        Player currentPlayer;
        // decide whose turn it is
        if(firstPlayerHasTurn){
            currentPlayer =  firstPlayer;
        } else {
            currentPlayer =  secondPlayer;
        }
        return currentPlayer;
    }
}
