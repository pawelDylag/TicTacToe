package com.fais.tictactoe.Data;

/**
 * Created by paweldylag on 24/10/15.
 */
public final class Parameters {

    // intent names
    public static final String INTENT_BOARD_SIZE = "intentBoardSize";
    public static final String INTENT_PLAYER_TYPE = "playerType";

    // player selector
    public static final int PLAYER_HUMAN = 1;
    public static final int PLAYER_SECOND_HUMAN = 2;
    public static final int PLAYER_AI_EASY = 3;
    public static final int PLAYER_AI_HARD = 4;

    // end of game
    public static final int GAME_RESULT_NOT_FINISHED = -1;
    public static final int GAME_RESULT_DRAW = 0;
    public static final int GAME_RESULT_FINISHED = 1;

    // board click results
    public static final int CLICK_RESULT_MOVE_NOT_POSSIBLE = -1;
    public static final int CLICK_RESULT_MOVE_OK = 1;
}
