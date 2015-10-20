package com.fais.tictactoe.Data;

import android.graphics.Point;

/**
 * Created by paweldylag on 20/10/15.
 */
public abstract class Player {

    private String name;
    private int playerType;

    public String getName() {
        return name;
    }

    public int getPlayerType() {
        return playerType;
    }

}
