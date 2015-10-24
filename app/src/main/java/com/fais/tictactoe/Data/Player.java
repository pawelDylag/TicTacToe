package com.fais.tictactoe.data;

/**
 * Created by paweldylag on 20/10/15.
 */
public abstract class Player {

    private static final int HUMAN = 0;
    private static final int AI_EASY = 1;
    private static final int AI_MEDIUM = 2;
    private static final int AI_HARD = 3;

    private String name;
    private int playerType;

    public String getName() {
        return name;
    }

    public int getPlayerType() {
        return playerType;
    }

}