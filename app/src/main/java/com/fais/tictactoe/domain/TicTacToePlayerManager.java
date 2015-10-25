package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToePlayerManager implements PlayerManager{

    private Player firstPlayer;
    private Player secondPlayer;

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
            getPlayer(Parameters.PLAYER_AI_EASY).setBoardManager(boardManager);
        }
        else if (getPlayer(Parameters.PLAYER_AI_HARD)!=null)
        {
            getPlayer(Parameters.PLAYER_AI_HARD).setBoardManager(boardManager);
        }
        else
        {
            //No AI Players
        }
    }

    @Override
    public void passPlayerManager()
    {
        firstPlayer.setPlayerManager(this);
        secondPlayer.setPlayerManager(this);
    }
}
