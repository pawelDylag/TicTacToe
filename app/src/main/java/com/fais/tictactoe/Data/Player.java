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

    protected int boardDrawableResource;

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

    public int getBoardDrawableResource() {
        return boardDrawableResource;
    }

    public void setBoardDrawableResource(int boardDrawableResource) {
        this.boardDrawableResource = boardDrawableResource;
    }

    public abstract void onTurnReceived();
}
