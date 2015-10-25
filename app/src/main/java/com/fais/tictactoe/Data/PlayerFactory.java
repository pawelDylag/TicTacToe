package com.fais.tictactoe.Data;

import android.support.annotation.Nullable;

/**
 * Created by paweldylag on 25/10/15.
 */
public class PlayerFactory {


    public PlayerFactory() {
    }

    @Nullable
    public Player getPlayer(int playerType){
        switch(playerType) {
            case Parameters.PLAYER_HUMAN:
                // TODO
                break;
            case Parameters.PLAYER_AI_EASY:
                // TODO
                break;
            case Parameters.PLAYER_AI_MEDIUM:
                // TODO
                break;
            case Parameters.PLAYER_AI_HARD:
                // TODO
                break;
            default:
                return null;
        }
        // TODO: usunac
        return null;
    }
}
