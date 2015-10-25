package com.fais.tictactoe.Data;

import com.fais.tictactoe.interfaces.BoardManager;
import com.fais.tictactoe.interfaces.PlayerManager;

/**
 * Created by paweldylag on 24/10/15.
 */
public abstract class Player
{
    protected String name;
    protected int playerType;
    protected BoardManager boardManager;
    protected PlayerManager playerManager;

    public String getName()
    {
        return name;
    }

    public void setPlayerType(int playerType)
    {
        this.playerType = playerType;
    }

    public int getPlayerType()
    {
        return playerType;
    }

    public void setBoardManager(BoardManager boardManager)
    {
        this.boardManager = boardManager;
    }

    public void setPlayerManager(PlayerManager playerManager){ this.playerManager = playerManager; }
}
