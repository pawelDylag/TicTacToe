package com.fais.tictactoe.domain;

import android.graphics.Point;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.GameEngine;
import com.fais.tictactoe.interfaces.PlayerManager;
import com.fais.tictactoe.utilities.Util;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToePlayerManager implements PlayerManager{

    private Player firstPlayer;
    private Player secondPlayer;
    private GameEngine gameEngine;
    private boolean isGameFinished;
    // Jesli pojedynek vs PC, to player1 = Human, player2 = PC
    private int whosTurn;

    public TicTacToePlayerManager(Player firstPlayer, Player secondPlayer)
    {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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
    public void passBoardManager(BoardManager boardManager)
    {
        if(getPlayer(Parameters.PLAYER_AI_EASY)!=null)
        {
            System.out.println("Jest AI!");
            getPlayer(Parameters.PLAYER_AI_EASY).setBoardManager(boardManager);
        }
        else if (getPlayer(Parameters.PLAYER_AI_HARD)!=null)
        {
            getPlayer(Parameters.PLAYER_AI_HARD).setBoardManager(boardManager);
        }
        else
        {
            System.out.println("Nie ma AI");
            //No AI Players
        }
    }

    @Override
    public void passPlayerManager()
    {
        firstPlayer.setPlayerManager(this);
        secondPlayer.setPlayerManager(this);
    }

    @Override
    public void whoMoves(int playersMove)
    {
        whosTurn = playersMove;
    }

    @Override
    public boolean isMyTurn(int playerNr)
    {
        if(playerNr==whosTurn)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void passGameEngine(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        System.out.println("Przekazuje!");

    }

    @Override
    public void notifyGameFinished(boolean isGameFinished)
    {
        this.isGameFinished = isGameFinished;
        firstPlayer.gameFinished(this.isGameFinished);
        secondPlayer.gameFinished(this.isGameFinished);
    }

    @Override
    public void AIMoves(int x, int y, int size)
    {
        Point point = new Point();
        point.set(x, y);
        gameEngine.getGame().onBoardClick(Util.convert2DIndexTo1D(x,y,size));
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }
}
